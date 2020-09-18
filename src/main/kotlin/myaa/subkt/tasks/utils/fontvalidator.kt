package myaa.subkt.tasks.utils

import io.ktor.utils.io.charsets.*
import myaa.subkt.ass.ASSFile
import myaa.subkt.ass.StyleLine
import myaa.subkt.tasks.ErrorMode
import org.apache.fontbox.ttf.*
import java.io.File
import java.nio.charset.Charset
import kotlin.math.abs

private data class Arg(val arg: String?)

private data class State(val font: String, val italic: Boolean, val weight: Int, val drawing: Boolean)

private val tagPattern = Regex("""\\\s*([^(\\]+)(?<!\s)\s*(?:\(\s*([^)]+)(?<!\s)\s*)?""")
private val intPattern = Regex("""^[+-]?\d+""")
private val linePattern = Regex("""(?:\{(?<tags>[^}]*)\}?)?(?<text>[^{]*)""")

private fun parseIntArg(s: String) = intPattern.find(s)?.value?.toInt() ?: 0

private fun parseTags(s: String, initState: State, style: State): State =
        tagPattern.findAll(s).fold(initState) { state, m ->
            val value = m.groups[1]!!.value
            fun getTag(tag: String, vararg exclude: String): Arg? =
                    if (value.startsWith(tag) and exclude.none { value.startsWith(it) }) {
                        val arg = m.groups[2]?.value
                                ?: value.substring(tag.length).trimStart().takeIf { it.isNotEmpty() }
                        Arg(arg)
                    } else {
                        null
                    }

            getTag("fn")?.let { (arg) ->
                state.copy(font = when {
                    arg == null -> style.font
                    arg.startsWith("@") -> arg.substring(1)
                    else -> arg
                })
            } ?: getTag("b", "blur", "be", "bord")?.let { (arg) ->
                val weight = arg?.let { parseIntArg(it) }
                val transformed = when (weight) {
                    0 -> 400
                    1, -1 -> 700
                    in (100..900) -> weight
                    else -> null
                }

                state.copy(weight = when (transformed) {
                    null -> style.weight
                    else -> transformed
                })
            } ?: getTag("i", "iclip")?.let { (arg) ->
                val slant = arg?.let { parseIntArg(it).takeIf { i -> i == 0 || i == 1 } }
                state.copy(italic = when (slant) {
                    null -> style.italic
                    else -> slant == 1
                })
            } ?: getTag("p", "pos", "pbo")?.let { (arg) ->
                val scale = arg?.let { parseIntArg(it) } ?: 0
                state.copy(drawing = scale != 0)
            } ?: getTag("t")?.let { (arg) ->
                arg?.let {
                    parseTags(it, state, style)
                }
            } ?: state
        }

@OptIn(ExperimentalStdlibApi::class)
private fun parseLine(line: String, style: StyleLine): Sequence<Pair<State, String>> {
    val initialState = State(style.font, style.italic, if (style.bold) 700 else 400, false)
    return linePattern.findAll(line).scan(initialState to "") { (state, _), m ->
        val newState = m.groups["tags"]?.let { (value) ->
            parseTags(value, state, initialState)
        } ?: state

        newState to (m.groups["text"]?.value ?: "")
    }.filterNot { it.second.isEmpty() }
}

private class Font(val fontFile: File) {
    companion object {
        private val encoder = Charset.forName("MacRoman").newEncoder()
    }

    private val font = OTFParser().parse(fontFile)
    val weight = font.oS2Windows.weightClass
    val italic = font.oS2Windows.fsSelection and 0b1 != 0
    val slant = if (italic) 110 else 0
    val width = 100
    val isPostScript = font.isPostScript

    val names = font.naming.nameRecords.filter {
        it.platformId == NameRecord.PLATFORM_WINDOWS &&
                it.platformEncodingId in listOf(NameRecord.ENCODING_WINDOWS_SYMBOL,
                NameRecord.ENCODING_WINDOWS_UNICODE_BMP)
    }
    val families = names.filter { it.nameId == NameRecord.NAME_FONT_FAMILY_NAME }.map { it.string }
    val fullNames = names.filter { it.nameId == NameRecord.NAME_FULL_FONT_NAME }.map { it.string }
    val postScriptName = font.name

    val exactNames = if (font.isPostScript) listOf(postScriptName) else fullNames

    init {
        val italicMac = font.header.macStyle and HeaderTable.MAC_STYLE_ITALIC != 0
        if (italic != italicMac) {
            println("warning: Font $fontFile has mismatching italic information. " +
                    "Please contact the SubKt maintainer and/or a libass maintainer.")
        }
    }

    fun missingGlyphs(s: String) =
            font.cmap.getSubtable(
                    CmapTable.PLATFORM_WINDOWS,
                    CmapTable.ENCODING_WIN_UNICODE_BMP)?.let { unicodeTable ->
                val extended = font.cmap.getSubtable(CmapTable.PLATFORM_WINDOWS,
                        CmapTable.ENCODING_WIN_UNICODE_FULL)
                s.filter {
                    val codepoint = it.toInt()
                    val glyphId = unicodeTable.getGlyphId(codepoint)
                    val extendedId = extended?.getGlyphId(codepoint) ?: 0
                    glyphId == 0 && extendedId == 0
                }
            } ?: font.cmap.getSubtable(
                    CmapTable.PLATFORM_WINDOWS,
                    CmapTable.ENCODING_WIN_SYMBOL)?.let { symbolTable ->
                s.filter {
                    !encoder.canEncode(it) || run {
                        val codepoint = encoder.encodeToByteArray(it.toString())[0].toInt() and 0xff
                        symbolTable.getGlyphId(0xf000 + codepoint) == 0
                    }
                }
            }

    override fun toString() =
            "$postScriptName(weight=$weight, italic=$italic)"
}

private sealed class FontMatch {
    object NoFont : FontMatch()
    class HasFont(val font: Font) : FontMatch()
}

private class FontCollection(fontFiles: List<File>) {
    val fonts = fontFiles.mapNotNull { file ->
        try {
            Font(file)
        } catch (e: Exception) { null }
    }

    val byFullName = fonts.flatMap { font -> font.exactNames.map { it to font } }.toMap()

    val byFamilyName = fonts
            .flatMap { font -> font.families.map { it to font } }
            .groupBy({ it.first }, { it.second })

    private val cache = mutableMapOf<State, FontMatch>()

    private fun similarity(state: State, font: Font): Int {
        val slant = if (state.italic) 100 else 0
        return abs(state.weight - font.weight) + abs(slant - font.slant)
    }

    private fun matchFont(state: State) =
            when (val exactMatch = byFullName[state.font]) {
                is Font -> exactMatch
                else -> when (val family = byFamilyName[state.font]) {
                    null -> null
                    else -> family.minBy { similarity(state, it) }
                }
            }

    fun match(state: State): Font? {
        val s = state.copy(drawing = false)

        return when (val f = cache[s]) {
            is FontMatch.HasFont -> f.font
            FontMatch.NoFont -> null
            null -> {
                val font = matchFont(s)
                cache[s] = when (font) {
                    null -> FontMatch.NoFont
                    else -> FontMatch.HasFont(font)
                }
                font
            }
        }
    }
}

class FontReport {
    private val missingFonts = sortedMapOf<String, MutableSet<Int>>()
    private val fauxItalic = sortedMapOf<String, MutableSet<Int>>()
    private val fauxBold = sortedMapOf<String, MutableMap<Pair<Int, Int>, MutableSet<Int>>>()
    private val italicMismatch = sortedMapOf<String, MutableSet<Int>>()
    private val weightMismatch = sortedMapOf<String, MutableMap<Pair<Int, Int>, MutableSet<Int>>>()
    private val missingGlyphs = sortedMapOf<String, MutableSet<Char>>()
    private val missingGlyphLines = sortedMapOf<String, MutableSet<Int>>()

    fun missingFont(line: Int, font: String) {
        missingFonts.computeIfAbsent(font) { sortedSetOf() }.add(line)
    }

    fun fauxItalic(line: Int, font: String) {
        fauxItalic.computeIfAbsent(font) { sortedSetOf() }.add(line)
    }

    fun fauxBold(line: Int, requestedWeight: Int, actualWeight: Int, font: String) {
        fauxBold.computeIfAbsent(font) { mutableMapOf() }
                .computeIfAbsent(requestedWeight to actualWeight) { sortedSetOf() }
                .add(line)
    }

    fun italicMismatch(line: Int, font: String) {
        italicMismatch.computeIfAbsent(font) { sortedSetOf() }.add(line)
    }

    fun weightMismatch(line: Int, requestedWeight: Int, actualWeight: Int, font: String) {
        weightMismatch.computeIfAbsent(font) { mutableMapOf() }
                .computeIfAbsent(requestedWeight to actualWeight) { sortedSetOf() }
                .add(line)
    }

    fun missingGlyphs(line: Int, glyphs: String, font: String) {
        missingGlyphLines.computeIfAbsent(font) { sortedSetOf() }.add(line)
        missingGlyphs.computeIfAbsent(font) { mutableSetOf() }.addAll(glyphs.toList())
    }

    fun printReport(
            onMissingFonts: ErrorMode, onFaux: ErrorMode,
            onStyleMismatch: ErrorMode, onMissingGlyphs: ErrorMode
    ) {
        var fail = false

        if (onMissingFonts != ErrorMode.IGNORE) {
            missingFonts.forEach { (font, lines) ->
                println("warning: font '$font' not found on line(s): " +
                        lines.joinToString(" "))

                if (onMissingFonts == ErrorMode.FAIL) {
                    fail = true
                }
            }
        }

        if (onFaux != ErrorMode.IGNORE) {
            fauxBold.forEach { (font, weightMap) ->
                weightMap.forEach { (weight, lines) ->
                    val (requested, actual) = weight
                    println("warning: faux bold used for font $font (requested weight $requested, got $actual) " +
                            "on line(s): ${lines.joinToString(" ")}")
                }

                if (onFaux == ErrorMode.FAIL) {
                    fail = true
                }
            }

            fauxItalic.forEach { (font, lines) ->
                println("warning: faux italic used for font $font on line(s): ${lines.joinToString(" ")}")

                if (onFaux == ErrorMode.FAIL) {
                    fail = true
                }
            }
        }

        if (onStyleMismatch != ErrorMode.IGNORE) {
            weightMismatch.forEach { (font, weightMap) ->
                weightMap.forEach { (weight, lines) ->
                    val (requested, actual) = weight
                    println("warning: requested weight $requested but got $actual for font $font " +
                            "on line(s): ${lines.joinToString(" ")}")
                }

                if (onStyleMismatch == ErrorMode.FAIL) {
                    fail = true
                }
            }

            italicMismatch.forEach { (font, lines) ->
                println("requested non-italic but got italic for font $font " +
                        "on line(s): ${lines.joinToString(" ")}")

                if (onStyleMismatch == ErrorMode.FAIL) {
                    fail = true
                }
            }
        }

        if (onMissingGlyphs != ErrorMode.IGNORE) {
            missingGlyphLines.forEach { (font, lines) ->
                val glyphs = missingGlyphs[font]?.joinToString("")
                println("warning: font $font missing glyphs $glyphs " +
                        "on line(s): ${lines.joinToString(" ")}")

                if (onMissingGlyphs == ErrorMode.FAIL) {
                    fail = true
                }
            }
        }

        if (fail) {
            error("one or more fatal font-related issues encountered")
        }
    }
}

fun verifyFonts(assFile: ASSFile, fontFiles: List<File>): FontReport {
    val styles = assFile.styles.lines.associateBy { it.name }
    val fonts = FontCollection(fontFiles)
    val report = FontReport()


    assFile.events.lines.withIndex().filter { (_, line) -> !line.comment }.forEach { (i, line) ->
        val lineNum = i + 1
        val style = styles[line.style] ?: run {
            println("Warning: Unknown style ${line.style} on line $lineNum; assuming default style")
            StyleLine()
        }

        parseLine(line.text, style).forEach { (state, text) ->
            when (val font = fonts.match(state)) {
                null -> report.missingFont(lineNum, state.font)
                else -> {
                    if (state.italic && !font.italic) {
                        report.fauxItalic(lineNum, state.font)
                    }

                    if (state.weight >= font.weight + 150) {
                        report.fauxBold(lineNum, state.weight, font.weight, state.font)
                    }

                    if (!state.italic && font.italic) {
                        report.italicMismatch(lineNum, state.font)
                    }

                    if (state.weight <= font.weight - 150) {
                        report.weightMismatch(lineNum, state.weight, font.weight, state.font)
                    }

                    if (!state.drawing) {
                        val missing = font.missingGlyphs(text)
                        if (missing == null) {
                            println("warning: could not read glyphs from $font")
                        } else if (missing.isNotEmpty()) {
                            report.missingGlyphs(lineNum, missing, state.font)
                        }
                    }
                }
            }
        }
    }

    return report
}
