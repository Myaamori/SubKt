package myaa.subkt.tasks.utils

import io.ktor.utils.io.charsets.*
import myaa.subkt.ass.ASSFile
import myaa.subkt.tasks.ErrorMode
import org.apache.fontbox.ttf.*
import java.io.File
import java.io.IOException
import java.nio.charset.Charset
import kotlin.math.abs

private data class Arg(val arg: String?)

data class State(val font: String, val italic: Boolean, val weight: Int,
                 val drawing: Boolean, val wrapStyle: Int)

private val tagPattern = Regex("""\\\s*([^(\\]+)(?<!\s)\s*(?:\(\s*([^)]+)(?<!\s)\s*)?""")
private val intPattern = Regex("""^[+-]?\d+""")
private val linePattern = Regex("""(?:(?<!\\)\{(?<tags>[^}]*)\}?)?(?<text>.*?(?=$|(?<!\\)\{))""")

private fun parseIntArg(s: String) = intPattern.find(s)?.value?.toInt() ?: 0

private fun parseTags(s: String, initState: State, lineStyle: State, styles: Map<String, State>): State =
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
                    arg == null -> lineStyle.font
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
                    null -> lineStyle.weight
                    else -> transformed
                })
            } ?: getTag("i", "iclip")?.let { (arg) ->
                val slant = arg?.let { parseIntArg(it).takeIf { i -> i == 0 || i == 1 } }
                state.copy(italic = when (slant) {
                    null -> lineStyle.italic
                    else -> slant == 1
                })
            } ?: getTag("p", "pos", "pbo")?.let { (arg) ->
                val scale = arg?.let { parseIntArg(it) } ?: 0
                state.copy(drawing = scale != 0)
            } ?: getTag("r")?.let { (arg) ->
                val style = when (arg) {
                    null -> lineStyle
                    else -> styles[arg] ?: run {
                        println("Warning: Unknown style $arg as argument to \\r; " +
                                "defaulting to line style")
                        lineStyle
                    }
                }
                state.copy(font = style.font, italic = style.italic, weight = style.weight)
            } ?: getTag("t")?.let { (arg) ->
                arg?.let {
                    parseTags(it, state, lineStyle, styles)
                }
            } ?: getTag("q")?.let { (arg) ->
                val q = arg?.let { parseIntArg(it) }
                val wrapStyle = when (q) {
                    in (0..3) -> q
                    else -> null
                }
                state.copy(wrapStyle = when (wrapStyle) {
                    null -> lineStyle.wrapStyle
                    else -> wrapStyle
                })
            } ?: state
        }

private val escapeRegex = Regex("""(\\*)\\(.)""")
private fun parseText(s: String, wrapStyle: Int) =
        escapeRegex.replace(s.replace('\t', ' ')) { match ->
            val (slashes, c) = match.destructured
            val replace = if (c == "N" || (c == "n" && wrapStyle == 2)) {
                // newlines are manually handled by libass
                ""
            } else if (c == "n") {
                " "
            } else if (c == "h") {
                // nbsp is mapped to normal space when rendering in libass
                " "
            } else if (c == "{") {
                "{"
            } else if (c == "}") {
                "}"
            } else {
                "\\$c"
            }
            slashes + replace
        }

@OptIn(ExperimentalStdlibApi::class)
private fun parseLine(line: String, lineStyle: State, styles: Map<String, State>): Sequence<Pair<State, String>> {
    return linePattern.findAll(line).scan(lineStyle to "") { (state, _), m ->
        val newState = m.groups["tags"]?.let { (value) ->
            parseTags(value, state, lineStyle, styles)
        } ?: state

        val text = m.groups["text"]?.value?.let { parseText(it, newState.wrapStyle) } ?: ""
        newState to text
    }.filterNot { it.second.isEmpty() }
}

private class Font(val fontFile: File, val font: TrueTypeFont) {
    companion object {
        private val encoder = Charset.forName("MacRoman").newEncoder()
    }

    val weight = font.oS2Windows.weightClass
    val italic = font.oS2Windows.fsSelection and 0b1 != 0
    val slant = if (italic) 110 else 0
    val width = 100
    val isPostScript = font is OpenTypeFont && font.isPostScript

    val names = font.naming.nameRecords.filter {
        it.platformId == NameRecord.PLATFORM_WINDOWS &&
                it.platformEncodingId in listOf(NameRecord.ENCODING_WINDOWS_SYMBOL,
                NameRecord.ENCODING_WINDOWS_UNICODE_BMP)
    }
    val families = names.filter { it.nameId == NameRecord.NAME_FONT_FAMILY_NAME }.map { it.string }
    val fullNames = names.filter { it.nameId == NameRecord.NAME_FULL_FONT_NAME }.map { it.string }
    val postScriptName = font.name

    val exactNames = (if (isPostScript) listOf(postScriptName) else fullNames)
            .filterNot { full -> families.any { it.equals(full, ignoreCase = true) } }

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

private data class FontMatch(val font: Font?, val exactMatch: Boolean)

private class FontCollection(fontFiles: Iterable<File>) {
    val fonts = fontFiles.flatMap { file ->
        try {
            listOf(Font(file, OTFParser().parse(file)))
        } catch (e: IOException) {
            try {
                val faces = mutableListOf<Font>()
                TrueTypeCollection(file).processAllFonts {
                    faces.add(Font(file, it))
                }
                faces
            } catch (e: IOException) { emptyList<Font>() }
        } catch (e: Exception) {
            println("warnng: error parsing font $file: ${e.message}")
            emptyList<Font>()
        }
    }

    val byFullName = fonts.flatMap { font -> font.exactNames.map { it.toLowerCase() to font } }.toMap()

    val byFamilyName = fonts
            .flatMap { font -> font.families.map { it.toLowerCase() to font } }
            .groupBy({ it.first }, { it.second })

    private val cache = mutableMapOf<State, FontMatch>()

    private fun similarity(state: State, font: Font): Int {
        val slant = if (state.italic) 100 else 0
        return abs(state.weight - font.weight) + abs(slant - font.slant)
    }

    private fun matchFont(state: State) =
            when (val exactMatch = byFullName[state.font]) {
                is Font -> FontMatch(exactMatch, true)
                else -> when (val family = byFamilyName[state.font]) {
                    null -> FontMatch(null, false)
                    else -> family.minBy { similarity(state, it) }?.let {
                        FontMatch(it, false)
                    } ?: FontMatch(null, false)
                }
            }

    fun match(state: State): FontMatch {
        val s = state.copy(font = state.font.toLowerCase(), drawing = false)

        return when (val f = cache[s]) {
            null -> {
                val font = matchFont(s)
                cache[s] = font
                font
            }
            else -> f
        }
    }
}

class FontReport(private val fontFiles: Set<File>) {
    private val missingFonts = sortedMapOf<String, MutableSet<Int>>()
    private val fauxItalic = sortedMapOf<String, MutableSet<Int>>()
    private val fauxBold = sortedMapOf<String, MutableMap<Pair<Int, Int>, MutableSet<Int>>>()
    private val italicMismatch = sortedMapOf<String, MutableSet<Int>>()
    private val weightMismatch = sortedMapOf<String, MutableMap<Pair<Int, Int>, MutableSet<Int>>>()
    private val missingGlyphs = sortedMapOf<String, MutableSet<Char>>()
    private val missingGlyphLines = sortedMapOf<String, MutableSet<Int>>()
    private val usedFonts = mutableSetOf<File>()

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

    fun usedFont(font: File) {
        usedFonts.add(font)
    }

    fun limitLines(lines: Collection<Int>, limit: Int = 10) =
            if (lines.size <= limit) lines.joinToString(" ")
            else lines.asSequence().take(limit).joinToString(" ") + " [...]"

    fun printReport(
            onMissingFonts: ErrorMode, onFaux: ErrorMode,
            onStyleMismatch: ErrorMode, onMissingGlyphs: ErrorMode
    ) {
        var fail = false

        if (onMissingFonts != ErrorMode.IGNORE) {
            missingFonts.forEach { (font, lines) ->
                println("warning: font '$font' not found on line(s): " +
                        limitLines(lines))

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
                            "on line(s): ${limitLines(lines)}")
                }

                if (onFaux == ErrorMode.FAIL) {
                    fail = true
                }
            }

            fauxItalic.forEach { (font, lines) ->
                println("warning: faux italic used for font $font on line(s): ${limitLines(lines)}")

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
                            "on line(s): ${limitLines(lines)}")
                }

                if (onStyleMismatch == ErrorMode.FAIL) {
                    fail = true
                }
            }

            italicMismatch.forEach { (font, lines) ->
                println("requested non-italic but got italic for font $font " +
                        "on line(s): ${limitLines(lines)}")

                if (onStyleMismatch == ErrorMode.FAIL) {
                    fail = true
                }
            }
        }

        if (onMissingGlyphs != ErrorMode.IGNORE) {
            missingGlyphLines.forEach { (font, lines) ->
                val glyphs = missingGlyphs[font]?.joinToString("")
                println("warning: font $font missing glyphs $glyphs " +
                        "on line(s): ${limitLines(lines)}")

                if (onMissingGlyphs == ErrorMode.FAIL) {
                    fail = true
                }
            }
        }

        if (fail) {
            error("one or more fatal font-related issues encountered")
        }
    }

    fun unusedFonts() = fontFiles - usedFonts
}

fun parseLines(assFile: ASSFile): Sequence<Sequence<Pair<State, String>>> {
    val styles = assFile.styles.lines.associate {
        val font = when {
            it.font.startsWith("@") -> it.font.substring(1)
            else -> it.font
        }
        it.name to State(
                font, it.italic, if (it.bold) 700 else 400, false,
                assFile.scriptInfo.wrapStyle?.ordinal ?: 0)
    }

    return assFile.events.lines.asSequence().withIndex().filter { (_, line) -> !line.comment }.map { (i, line) ->
        val lineNum = i + 1
        val lineStyle = styles[line.style] ?: run {
            println("Warning: Unknown style ${line.style} on line $lineNum; assuming default style")
            State("Arial", false, 400, false, 0)
        }

        parseLine(line.text, lineStyle, styles)
    }
}

fun verifyFonts(assFile: ASSFile, fontFiles: Iterable<File>): FontReport {
    val fonts = FontCollection(fontFiles)
    val report = FontReport(fonts.fonts.map { it.fontFile }.toSet())

    parseLines(assFile).withIndex().forEach { (i, line) ->
        val lineNum = i + 1
        line.forEach { (state, text) ->
            val (font, exactMatch) = fonts.match(state)
            when (font) {
                null -> report.missingFont(lineNum, state.font)
                else -> {
                    report.usedFont(font.fontFile)

                    if (state.italic && !font.italic) {
                        report.fauxItalic(lineNum, state.font)
                    }

                    if (state.weight >= font.weight + 150) {
                        report.fauxBold(lineNum, state.weight, font.weight, state.font)
                    }

                    if (!state.italic && font.italic && !exactMatch) {
                        report.italicMismatch(lineNum, state.font)
                    }

                    if (state.weight <= font.weight - 150 && !exactMatch) {
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
