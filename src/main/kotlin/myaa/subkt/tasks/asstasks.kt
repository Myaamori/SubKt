package myaa.subkt.tasks

import myaa.subkt.ass.ASSFile
import myaa.subkt.ass.EventLine
import myaa.subkt.ass.EventLineAccessor
import myaa.subkt.ass.ScriptInfoSection
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.*
import org.gradle.kotlin.dsl.listProperty
import org.gradle.kotlin.dsl.property
import java.nio.charset.StandardCharsets
import java.time.Duration

/**
 * Represents a task that outputs an ASS file.
 */
abstract class ASSTask : PropertyTask() {
    /**
     * The location to save the ASS file.
     * Defaults to an automatically generated file in the build directory.
     */
    @get:OutputFiles
    val out = outputFile("ass")

    /**
     * Whether to include the Aegisub Extradata section.
     */
    @get:Input
    val includeExtraData = project.objects.property<Boolean>().convention(true)

    /**
     * Whether to include the Aegisub Project Garbage section.
     */
    @get:Input
    val includeProjectGarbage = project.objects.property<Boolean>().convention(true)

    /**
     * Constructs the ASS file to save. Must be implemented by subtypes.
     */
    protected abstract fun buildAss(): ASSFile

    override fun run() {
        val ass = buildAss()

        out.singleFile.bufferedWriter(StandardCharsets.UTF_8).use { writer ->
            ass.serialize(includeProjectGarbage = includeProjectGarbage.get(),
                    includeExtraData = includeExtraData.get()).forEach {
                writer.write(it)
            }
        }
    }
}

private val commentPattern = Regex("""\s*\{(.*)\}\s*""")
private fun getMaybeComment(line: EventLine, accessor: EventLineAccessor<String>) =
        accessor.takeIf { it == EventLineAccessor.TEXT }?.let {
            commentPattern.matchEntire(line.text)?.groupValues?.get(1)
        } ?: line.get(accessor)

/**
 * Used in some tasks to specify the failure mode.
 */
enum class ErrorMode {
    WARN,
    FAIL
}

private fun warn(message: String, mode: ErrorMode) {
    when (mode) {
        ErrorMode.WARN -> println("Warning: $message")
        ErrorMode.FAIL -> error(message)
    }
}

/**
 * Task to merge multiple ASS files into one. A predefined task instance can be
 * accessed through [Subs.merge].
 *
 * @sample myaa.subkt.tasks.samples.mergeSample
 */
open class Merge : ASSTask() {
    /**
     * Defines how the files associated with this specification should be merged.
     */
    inner class MergeSpecification {
        /**
         * Increments the layer of all event lines by the specified amount.
         */
        @get:Input
        val incrementLayer = defaultProperty(0)

        /**
         * Shifts the start and end times of all event lines by the specified [Duration].
         */
        @get:Input
        val shiftBy = defaultProperty(Duration.ZERO)

        /**
         * Shifts the lines of the source file so that the start time of the sync line,
         * as specified by [syncLine] and [syncField], lines up with the specified [Duration].
         */
        @get:Input
        @get:Optional
        val syncTo = project.objects.property<Duration>()

        /**
         * An [EventLineAccessor] specifying what field to match the value specified by
         * [syncLine] to in order to identify the sync line.
         * Defaults to the effect field.
         */
        @get:Input
        val syncField = defaultProperty<EventLineAccessor<String>>(EventLineAccessor.EFFECT)

        /**
         * The value that identifies the sync line used by [syncTo]. The sync line
         * must have the specified value in the field specified by [syncField].
         * Defaults to `sync`.
         */
        @get:Input
        val syncLine = defaultProperty("sync")
    }

    /**
     * Represents a pair of files and corresponding merge specification.
     */
    data class FileSpecPair(
            /**
             * Files to merge.
             */
            @get:InputFiles
            val files: Any,
            /**
             * Merge specification for the files in [FileSpecPair.files].
             */
            @get:Nested
            val spec: MergeSpecification
    )

    private val _sources = project.objects.listProperty<FileSpecPair>()

    /**
     * The files to be merged, added via [from] or [fromMergeTemplate].
     */
    @get:Nested
    val sources: Provider<List<FileSpecPair>> = _sources

    /**
     * A [ScriptInfoSection] instance for overriding the script info read from the
     * merged files. A [scriptInfo] method is available for easier modification.
     */
    @get:Input
    val scriptInfo = ScriptInfoSection("")

    /**
     * Fields for which differing values are considered a conflict.
     * Defaults to PlayResX, PlayResY, YCbCr Matrix, ScaledBorderAndShadow and WrapStyle.
     */
    @get:Input
    val conflictingScriptInfo = project.objects.listProperty<String>()
            .convention(listOf(
                    "PlayResX", "PlayResY", "YCbCr Matrix",
                    "ScaledBorderAndShadow", "WrapStyle"))

    /**
     * What to do when encountering conflicting values for fields specified in
     * [conflictingScriptInfo].
     */
    @get:Input
    val onScriptInfoConflict = defaultProperty(ErrorMode.WARN)

    /**
     * What to do when encountering styles with identical names but conflicting definitions.
     */
    @get:Input
    val onStyleConflict = defaultProperty(ErrorMode.WARN)

    /**
     * If true, prepends every style name with an identifier unique to each file,
     * to make style conflicts impossible.
     */
    @get:Input
    val namespaceStyles = defaultProperty(false)

    /**
     * Adds files to merge.
     *
     * @sample myaa.subkt.tasks.samples.mergeFromSample
     *
     * @param files The files to merge.
     * @param action A closure operating on a [MergeSpecification] instance
     * for customizing how the given files should be merged.
     */
    fun from(vararg files: Any, action: MergeSpecification.() -> Unit = {}) {
        _sources.add(FileSpecPair(files, MergeSpecification().apply(action)))
    }

    /**
     * Merges files as defined in a template file following
     * [Merge Scripts](https://github.com/TypesettingTools/Myaamori-Aegisub-Scripts/#merge-scripts)
     * syntax.
     *
     * **Note**: Currently, lines other than import definitions are ignored, and will
     * not be present in the merged file.
     *
     * @param file The path to the merge template file.
     */
    fun fromMergeTemplate(file: Any) {
        _sources.addAll(project.provider {
            val template = project.files(file).singleFile
            ASSFile(template).events.lines.mapNotNull { line ->
                if (line.effect in setOf("import", "import-shifted")) {
                    val spec = MergeSpecification().apply {
                        incrementLayer(line.layer)
                        if (line.effect == "import-shifted") {
                            syncTo(line.start)
                        }
                    }
                    val merged = template.resolveSibling(line.text)

                    FileSpecPair(merged, spec)
                } else {
                    null
                }
            }
        })
    }

    /**
     * Allows you to override values in the Script Info section as needed.
     *
     * @sample myaa.subkt.tasks.samples.mergeScriptInfoSample
     * @param action A closure operating on a [ScriptInfoSection] instance.
     */
    fun scriptInfo(action: ScriptInfoSection.() -> Unit) {
        scriptInfo.action()
    }

    override fun buildAss(): ASSFile {
        val files = _sources.get().flatMap { (files, spec) ->
            project.files(files).map { file -> ASSFile(file).also { ass ->
                ass.events.lines.forEach { line ->
                    line.layer += spec.incrementLayer.get()
                    line.start += spec.shiftBy.get()
                    line.end += spec.shiftBy.get()
                }

                spec.syncTo.orNull?.let { syncTo ->
                    val referenceLine = ass.events.lines.find {
                        getMaybeComment(it, spec.syncField.get()) == spec.syncLine.get()
                    } ?: error("Could not find sync line in ${file.name}")

                    val delta = syncTo - referenceLine.start

                    ass.events.lines.forEach {
                        it.start += delta
                        it.end += delta
                    }
                }
            } }
        }

        val conflictingInfoSet = conflictingScriptInfo.get().toSet()
        val outAss = files.foldIndexed(ASSFile()) { i, acc, assFile ->
            val existingStyles = acc.styles.lines.associate {
                it.name to acc.styles.serializeLine(it)
            }

            if (namespaceStyles.get()) {
                assFile.styles.lines.forEach {
                    it.name = "$i\$${it.name}"
                }
                assFile.events.lines.forEach {
                    it.style = "$i\$${it.style}"
                }
            }

            acc.scriptInfo.values.putAll(assFile.scriptInfo.values.mapNotNull { (k, v) ->
                (k to v).takeIf { k !in acc.scriptInfo.values } ?: run {
                    if (v != acc.scriptInfo.values[k] && k in conflictingInfoSet) {
                        warn("conflicting $k value from ${assFile.file?.name}: $v " +
                                "(was: ${acc.scriptInfo.values[k]})", onScriptInfoConflict.get())
                    }
                    null
                }
            })
            acc.projectGarbage.values.putAll(assFile.projectGarbage.values)
            acc.styles.lines.addAll(assFile.styles.lines.mapNotNull { styleLine ->
                when (val existing = existingStyles[styleLine.name]) {
                    null -> styleLine
                    else -> {
                        if (existing != assFile.styles.serializeLine(styleLine)) {
                            warn("style already exists: ${styleLine.name}; " +
                                    "conflicting style from ${assFile.file?.name} ignored",
                                    onStyleConflict.get())
                        }
                        null
                    }
                }
            })
            acc.events.lines.addAll(assFile.events.lines)
            acc
        }

        outAss.scriptInfo.values.putAll(scriptInfo.values)
        return outAss
    }
}

/**
 * Generates a chapter file from an ASS file in the same way as Significance.
 * The provided ASS file will be searched for lines where the field
 * specified by [field] contains the value specified by [chapterMarker],
 * and for each such line a chapter will be generated using the start time
 * as the time, and the value of the field specified by [chapterName]
 * as the chapter name.
 * A predefined task instance can be accessed through [Subs.chapters].
 *
 * Example usage:
 *
 * ```
 * chapters {
 *     from("chapter_file.ass")
 *     chapterName(EventLineAccessor.EFFECT)
 *     chapterMarker("chapter")
 *     generateIntro(false)
 *     out("chapters.txt")
 * }
 * ```
 */
open class Chapters : PropertyTask() {
    /**
     * Specifies the source ASS file to generate chapters from.
     */
    @get:InputFiles
    val from = project.objects.fileCollection()

    /**
     * The field identifying a chapter line. Defaults to actor.
     */
    @get:Input
    val field = defaultProperty<EventLineAccessor<String>>(EventLineAccessor.ACTOR)

    /**
     * The field defining the name of the chapter. Defaults to the text field.
     * If set to the text field, and the line consists of a comment, the chapter name
     * will be the string inside the comment, i.e. the surrounding braces will be striped.
     */
    @get:Input
    val chapterName = defaultProperty<EventLineAccessor<String>>(EventLineAccessor.TEXT)

    /**
     * If the field specified by [field] contains this value, it is considered to be
     * a chapter line. Defaults to `chptr`.
     */
    @get:Input
    val chapterMarker = defaultProperty("chptr")

    /**
     * Whether to generate an introductory chapter at 00:00 if no such chapter exists already.
     * Defaults to true.
     */
    @get:Input
    val generateIntro = defaultProperty(true)

    /**
     * The name of the automatically generated introductory chapter if [generateIntro] is true.
     */
    @get:Input
    val introChapter = defaultProperty("Intro")

    /**
     * The location to save the chapter file.
     * Defaults to an automatically generated file in the build directory.
     */
    @get:OutputFiles
    val out = outputFile("txt")

    override fun run() {
        val ass = ASSFile(from.singleFile)
        val chapterLines = ass.events.lines
                .filter { getMaybeComment(it, field.get()) == chapterMarker.get() }
                .map { getMaybeComment(it, chapterName.get()) to it.start }
        val withIntro = chapterLines.takeIf {
            !generateIntro.get() || it.find { (_, start) -> start.isZero } != null
        } ?: listOf(Pair(introChapter.get(), Duration.ZERO)) + chapterLines

        val chapters = withIntro.withIndex().joinToString("\n\n") { (i, chapter) ->
            val (name, start) = chapter
            val time = with(start) {
                "%02d:%02d:%02d.%03d".format(
                        toHoursPart(), toMinutesPart(),
                        toSecondsPart(), toMillisPart()
                )
            }

            val num = "%02d".format(i + 1)
            "CHAPTER$num=$time\nCHAPTER${num}NAME=$name"
        }

        out.singleFile.writeText(chapters)
    }
}

/**
 * Task to enable/disable honorifics and the like using the same syntax as
 * [Daiz's autoswapper](https://github.com/Daiz/AegisubMacros#autoswapperlua---autoswapper).
 *
 * On the lines whose style name matches [styles], e.g. `Karen{**-chan}` will turn
 * into `Karen{*}-chan{*}`, and {*}Miu{*Nee-nee} will become {*}Nee-nee{*Miu}.
 * Further, any line where the effect line contains the string specified by [lineMarker]
 * will be commented if it is not a comment already, and will otherwise be uncommented.
 *
 * A predefined task instance can be accessed through [Subs.swap].
 *
 * Example usage:
 *
 * ```
 * ```
 */
open class Swap : ASSTask() {
    /**
     * Specifies the source ASS file.
     */
    @get:InputFiles
    val from = project.objects.fileCollection()

    /**
     * Lines with an effect field matching this string will be toggled between
     * being commented and uncommented. Defaults to `***`.
     * [styles] has no bearing on this operation.
     */
    @get:Input
    val lineMarker = project.objects.property<String>().convention("***")

    /**
     * The delimiter used in expressions like `Karen{**-chan}`. Defaults to `*`.
     */
    @get:Input
    val delimiter = project.objects.property<String>().convention("*")

    // patterns borrowed from https://github.com/TypesettingTools/CoffeeFlux-Aegisub-Scripts/blob/3e9efc865cf719589d55a49b6981e98e0ef6fc6b/macros/Flux.DialogSwapper.moon#L130
    private val patterns = delimiter.map { delim ->
        val esc = Regex.escape(delim)
        listOf(
                Regex("""\{$esc\}([^{]*)\{$esc([^}]*)\}""")
                        to "{$esc}$2{$esc$1}",
                Regex("""\{$esc$esc([^}]+)\}""")
                        to "{$esc}$1{$esc}",
                Regex("""\{$esc\}\{$esc""") to "{$esc$esc"
        )
    }

    /**
     * Only lines with a style name matching the specified pattern will have their contents swapped.
     * Defaults to `Regex("Main|Default")`, meaning all styles that contain
     * either `Main` or `Default` somewhere in the name.
     *
     * Can be a [Regex] or a list of style names.
     */
    @get:Input
    val styles = defaultProperty<Any>(Regex("Main|Default"))

    override fun buildAss(): ASSFile {
        val ass = ASSFile(from.singleFile)
        val styleMatcher = styles.get()
        ass.events.lines.forEach { line ->
            if (line.effect == lineMarker.get()) {
                line.comment = !line.comment
            }

            val styleMatches = when (styleMatcher) {
                is Regex -> styleMatcher in line.style
                is Iterable<*> -> line.style in styleMatcher
                else -> throw IllegalArgumentException(
                        "expected Regex or Iterable, found ${styleMatcher::class} for styles")
            }

            if (styleMatches) {
                patterns.get().forEach { (pattern, replacement) ->
                    line.text = pattern.replace(line.text, replacement)
                }
            }
        }

        return ass
    }
}


/**
 * Convenience property that upon use automatically instantiates and returns a
 * [TaskGroup] of type [Merge] with the name `merge`.
 */
val Subs.merge
    get() = task<Merge>("merge")

/**
 * Convenience property that upon use automatically instantiates and returns a
 * [TaskGroup] of type [Swap] with the name `swap`.
 */
val Subs.swap
    get() = task<Swap>("swap")

/**
 * Convenience property that upon use automatically instantiates and returns a
 * [TaskGroup] of type [Chapters] with the name `chapters`.
 */
val Subs.chapters
    get() = task<Chapters>("chapters")
