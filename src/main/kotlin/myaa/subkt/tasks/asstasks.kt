package myaa.subkt.tasks

import com.google.gson.*
import myaa.subkt.ass.ASSFile
import myaa.subkt.ass.EventLine
import myaa.subkt.ass.EventLineAccessor
import myaa.subkt.ass.ScriptInfoSection
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.*
import org.gradle.kotlin.dsl.listProperty
import org.gradle.kotlin.dsl.property
import java.awt.Color
import java.lang.reflect.Type
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.time.ZonedDateTime

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
     * Whether to remove comments from the output file.
     */
    @get:Input
    val removeComments = project.objects.property<Boolean>().convention(false)

    /**
     * Constructs the ASS file to save. Must be implemented by subtypes.
     */
    protected abstract fun buildAss(): ASSFile

    override fun run() {
        val ass = buildAss()

        if (removeComments.get()) {
            ass.events.lines.removeAll { it.comment }
        }

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
    IGNORE,
    WARN,
    FAIL
}

/**
 * Task to merge multiple ASS files into one. A predefined task instance can be
 * accessed through [Subs.merge].
 *
 * @sample myaa.subkt.tasks.samples.mergeSample
 */
open class Merge : ASSTask() {
    data class LineSpecification(
            @get:Input val field: EventLineAccessor<String>,
            @get:Input val value: String
    )

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

        private val _syncSourceLine =
                defaultProperty(LineSpecification(EventLineAccessor.EFFECT, "sync"))

        @get:Nested
        @get:Optional
        val syncSourceLine: Provider<LineSpecification> = _syncSourceLine

        /**
         * Specifies a line in the included file (e.g. a song file) to serve as
         * a reference for shifting all lines in this file. All lines
         * will be shifted so that the start time of the specified source line
         * matches the time specified by [syncTargetTime] or the start time
         * of the line specified by [syncTargetLine].
         *
         * @param fieldValue The value which identifies the source sync line
         * @param fieldName The field in which to look for [fieldValue]
         */
        fun syncSourceLine(
                fieldValue: String,
                fieldName: EventLineAccessor<String> = EventLineAccessor.EFFECT
        ) {
            _syncSourceLine.set(LineSpecification(fieldName, fieldValue))
        }

        private val _syncTargetLine = project.objects.property<LineSpecification>()

        @get:Nested
        @get:Optional
        val syncTargetLine: Provider<LineSpecification> = _syncTargetLine

        /**
         * Specifies a target line which the line specified by [syncSourceLine]
         * should be shifted to. The target line may be present anywhere in the merged file.
         *
         * @param fieldValue The value which identifies the target sync line
         * @param fieldName The field in which to look for [fieldValue]
         */
        fun syncTargetLine(
                fieldValue: String,
                fieldName: EventLineAccessor<String> = EventLineAccessor.EFFECT
        ) {
            _syncTargetLine.set(LineSpecification(fieldName, fieldValue))
        }

        /**
         * Specifies a target time which the line specified by [syncSourceLine]
         * should be shifted to.
         */
        @get:Input
        @get:Optional
        val syncTargetTime = project.objects.property<Duration>()
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
                            syncTargetTime(line.start)
                        }
                    }
                    val merged = (template.parent + "/" + line.text).replace('\\', '/')

                    FileSpecPair(project.globPath(merged), spec)
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

    private fun warn(message: String, mode: ErrorMode) {
        when (mode) {
            ErrorMode.WARN -> logger.warn(message)
            ErrorMode.FAIL -> error(message)
            ErrorMode.IGNORE -> {}
        }
    }

    override fun buildAss(): ASSFile {
        val targetLineSpecs = mutableListOf<LineSpecification>()

        // read ass files, preliminary processing, collect target sync line specifications
        val files = _sources.get().flatMap { (files, spec) ->
            project.files(files).map { file ->
                val ass = ASSFile(file)

                ass.events.lines.forEach { line ->
                    line.layer += spec.incrementLayer.get()
                    line.start += spec.shiftBy.get()
                    line.end += spec.shiftBy.get()
                }

                spec.syncTargetLine.orNull?.let {
                    targetLineSpecs.add(it)
                }

                ass to spec
            }
        }

        // find lines corresponding to target sync line specifications
        val targetLines = files.flatMap { (ass, _) -> ass.events.lines }.mapNotNull { line ->
            targetLineSpecs.find { getMaybeComment(line, it.field) == it.value }?.let { it to line }
        }.groupBy({ it.first }, { it.second }).mapValues { (spec, lines) ->
            when {
                lines.size > 1 -> error("duplicate target sync lines with value ${spec.value} " +
                        "in field ${spec.field}")
                else -> lines[0]
            }
        }

        // shift lines based on start time of target lines or explicit target times
        files.forEach { (ass, spec) ->
            val targetTime = spec.syncTargetTime.orNull
                    ?: spec.syncTargetLine.orNull?.let { targetSpec ->
                        targetLines[targetSpec]?.start ?: error(
                            "could not find target sync line with value ${targetSpec.value} " +
                                    "in field ${targetSpec.field} in merged file")
                    }

            if (targetTime != null) {
                val sourceSpec = spec.syncSourceLine.get()
                val sourceLine = ass.events.lines.find {
                    getMaybeComment(it, sourceSpec.field) == sourceSpec.value
                } ?: error("could not find source sync line with value ${sourceSpec.value}" +
                        "in field ${sourceSpec.field} in file ${ass.file?.name}")
                val sourceTime = sourceLine.start

                val delta = targetTime - sourceTime
                ass.events.lines.forEach {
                    it.start += delta
                    it.end += delta
                }
            }
        }

        val conflictingInfoSet = conflictingScriptInfo.get().toSet()
        val outAss = files.map { (ass, _) -> ass }.foldIndexed(ASSFile()) { i, acc, assFile ->
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
        val _field = field.get()
        val _marker = chapterMarker.get()
        val chapterLines = ass.events.lines
                .filter { getMaybeComment(it, _field) == _marker }
                .map { getMaybeComment(it, chapterName.get()) to it.start }

        if (chapterLines.isEmpty()) {
            error("no chapter definitions found; " +
                    "are you using the right chapter marker ($_marker) " +
                    "and field (${_field.field})?")
        }

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
                        to "{$delim}$2{$delim$1}",
                Regex("""\{$esc$esc([^}]+)\}""")
                        to "{$delim}$1{$delim}",
                Regex("""\{$esc\}\{$esc""") to "{$delim$delim"
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
 * Generic task for modifying ASS files. See [ASSFile] and related classes
 * for more information. The modified file is written to a new file.
 *
 * @sample myaa.subkt.tasks.samples.assSample
 */
open class ASS : ASSTask() {
    /**
     * Specifies the source ASS file.
     */
    @get:InputFiles
    val from = project.objects.fileCollection()

    private var _ass: (ASSFile.() -> Unit)? = null

    /**
     * Modify the ASS file specified using [from].
     *
     * @param f A closure operating on an [ASSFile] instance.
     */
    fun ass(f: ASSFile.() -> Unit) {
        _ass = f
    }

    override fun buildAss(): ASSFile {
        val ass = ASSFile(from.singleFile)
        _ass?.let { f ->
            ass.f()
        }
        return ass
    }
}



object ASSColorSerializer : JsonSerializer<Color> {
    override fun serialize(src: Color, typeOfSrc: Type,
                           context: JsonSerializationContext): JsonElement =
            context.serialize(listOf(src.red, src.green, src.blue, src.alpha))
}


/**
 * Task for running Aegisub automations on a script using
 * [Aegisub CLI](https://github.com/Myaamori/aegisub-cli).
 *
 * The `aegisub-cli` binary needs to be in your PATH,
 * or specified using [aegisubCli].
 *
 * @sample myaa.subkt.tasks.samples.automationSample
 */
open class Automation : DefaultTask(), SubTask {
    /**
     * Name of Aegisub CLI binary, or path to it if it is not in your PATH.
     */
    @get:Input
    val aegisubCli = defaultProperty("aegisub-cli")

    /**
     * Specifies the source ASS file.
     */
    @get:InputFiles
    val from = project.objects.fileCollection()

    /**
     * Filename of automation script to run.
     *
     * May be a relative path, absolute path, or a simple filename.
     * If a simple filename is provided, Aegisub CLI will search
     * for the script in the usual autoload locations.
     */
    @get:Input
    @get:Optional
    val script = project.objects.property<String>()

    /**
     * Name of macro to run, as defined by the script specified with [script].
     */
    @get:Input
    val macro = project.objects.property<String>()

    /**
     * Video file to load.
     *
     * Required for e.g. `aegisub.frame_from_ms` if [timecodes] is not specified.
     */
    @get:InputFiles
    val video = project.objects.fileCollection()

    /**
     * Keyframes file to load.
     */
    @get:InputFiles
    val keyframes = project.objects.fileCollection()

    /**
     * Timecodes file to load.
     *
     * Required for e.g. `aegisub.frame_from_ms` if [video] is not specified.
     */
    @get:InputFiles
    val timecodes = project.objects.fileCollection()

    enum class LogLevel {
        EXCEPTION,
        ASSERT,
        WARNING,
        INFO,
        DEBUG
    }

    /**
     * What Aegisub CLI should print to standard out.
     *
     * By default Aegisub CLI will print all output.
     */
    @get:Internal
    val loglevel = project.objects.property<LogLevel>()

    data class Dialog(
            @get:Input
            val button: Int,
            @get:Input
            val values: Map<String, Any>
    )

    private val _dialog = project.objects.listProperty<Dialog>()

    @get:Nested
    val dialog: Provider<List<Dialog>> = _dialog

    /**
     * Values to supply to an `aegisub.dialog.display` call.
     *
     * @param values Field name/value pairs
     * @param button The index of the button to push
     */
    fun dialog(vararg values: Pair<String, Any>, button: Int) {
        _dialog.add(Dialog(button, values.toMap()))
    }

    /**
     * File names to provide to an `aegisub.dialog.open` or `aegisub.dialog.save` call.
     */
    @get:Input
    val fileDialog = project.objects.listProperty<String>()

    data class Selection(
            @get:Input
            val selected: List<Int>?,
            @get:Input
            @get:Optional
            val active: Int?
    )

    private val _selection = project.objects.property<Selection>()

    @get:Nested
    @get:Optional
    val selection: Provider<Selection> = _selection

    inner class Selector {
        internal var _select: Iterable<EventLine>? = null
            private set
        internal var _active: EventLine? = null
            private set
        fun select(lines: Iterable<EventLine>) {
            _select = lines
        }
        fun active(line: EventLine) {
            _active = line
        }
    }

    /**
     * Specify what lines to mark as selected and active.
     *
     * The supplied function should call the [Selector.select] and [Selector.active]
     * functions to mark lines as selected/active.
     *
     * @param f A function operating on a [Selector] object that takes an [ASSFile] object.
     */
    fun selectLines(f: Selector.(ASSFile) -> Unit) {
        _selection.set(project.provider {
            val ass = ASSFile(from.singleFile)
            ass.events.lines.withIndex().forEach { (i, line) ->
                line.extraData["__index"] = i.toString()
            }
            val sel = Selector().apply { f(ass) }
            val selectedIndices = sel._select?.mapNotNull {
                it.extraData["__index"]?.toInt()
            }
            val activeIndex = sel._active?.let { it.extraData["__index"]?.toInt() }

            Selection(selectedIndices, activeIndex)
        })
    }

    /**
     * The location to save the ASS file.
     * Defaults to an automatically generated file in the build directory.
     */
    @get:OutputFiles
    val out = outputFile("ass")

    private fun toRange(indices: List<Int>): List<IntRange> {
        val ranges = mutableListOf<IntRange>()
        var curStart = -2
        var curEnd = -2
        indices.forEach { i ->
            if (i == curEnd + 1) {
                curEnd = i
            } else {
                if (curStart >= 0) {
                    ranges.add(curStart..curEnd)
                }
                curStart = i
                curEnd = i
            }
        }
        if (curStart >= 0) {
            ranges.add(curStart..curEnd)
        }
        return ranges
    }

    @TaskAction
    fun run() {
        val args = mutableListOf<String>()
        args.add(aegisubCli.get())

        _selection.orNull?.let { selection ->
            selection.selected?.let { selected ->
                val ranges = toRange(selected).joinToString(",") {
                    if (it.first == it.last) "${it.first}"
                    else "${it.first}-${it.last}"
                }

                args.add("--selected-lines")
                args.add(ranges)
            }

            selection.active?.let {
                args.add("--active-line")
                args.add(it.toString())
            }
        }

        video.files.takeIf { it.size > 0 }?.let {
            val videoFile = it.single()
            args.add("--video")
            args.add(videoFile.absolutePath)
        }

        timecodes.files.takeIf { it.size > 0 }?.let {
            val timecodesFile = it.single()
            args.add("--timecodes")
            args.add(timecodesFile.absolutePath)
        }

        keyframes.files.takeIf { it.size > 0 }?.let {
            val keyframesFile = it.single()
            args.add("--keyframes")
            args.add(keyframesFile.absolutePath)
        }

        script.orNull?.let {
            args.add("--automation")
            args.add(it)
        }

        fileDialog.get().forEach {
            args.add("--file")
            args.add(it)
        }

        val gson = GsonBuilder()
                .registerTypeHierarchyAdapter(Provider::class.java, ProviderSerializer)
                .registerTypeAdapter(Color::class.java, ASSColorSerializer)
                .create()

        _dialog.get().forEach {
            args.add("--dialog")
            args.add(gson.toJson(it))
        }

        loglevel.orNull?.let { level ->
            args.add("--loglevel")
            args.add(level.ordinal.toString())
        }

        args.add(from.singleFile.absolutePath)
        args.add(out.singleFile.absolutePath)
        args.add(macro.get())

        println(args)

        project.exec { spec ->
            spec.commandLine(args)
        }
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
