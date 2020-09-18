package myaa.subkt.ass

import java.awt.Color
import java.io.File
import java.io.Serializable
import java.lang.Exception
import java.lang.UnsupportedOperationException
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.time.temporal.ChronoUnit
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty
import kotlin.reflect.full.*

private class Node<T> private constructor(private val node: NodeElem<T>?) : Iterable<T> {
    private class NodeElem<T>(val head: T, val tail: NodeElem<T>?)

    constructor(vararg elems: T) : this(
            elems.foldRight<T, NodeElem<T>?>(null) { elem, acc ->
                NodeElem(elem, acc)
            }
    )

    fun asSequence() = generateSequence(node) { it.tail }.map { it.head }

    fun cons(elem: T) =
            Node(NodeElem(elem, node))

    override fun iterator() = asSequence().iterator()

    override fun toString() = toList().toString()
}

private operator fun <T> T.plus(other: Node<T>) = other.cons(this)

private fun <T> Iterable<T>.group(isSeparator: (T) -> Boolean): List<Pair<T?, List<T>>> =
        groupRec(null, iterator(), Node(), Node(), isSeparator)

private fun <T> Iterator<T>.nextOrNull() =
        if (this.hasNext()) this.next() else null

private tailrec fun <T> groupRec(
        currentSection: T?,
        list: Iterator<T>,
        sections: Node<Pair<T?, List<T>>>,
        acc: Node<T>,
        isSeparator: (T) -> Boolean
): List<Pair<T?, List<T>>> {
    val head = list.nextOrNull()
    return when {
        head == null -> ((currentSection to acc.reversed()) + sections).reversed()
        isSeparator(head) -> {
            val secs = (currentSection to acc.reversed()) + sections
            groupRec(head, list, secs, Node(), isSeparator)
        }
        else -> groupRec(currentSection, list, sections, head + acc, isSeparator)
    }
}

typealias ExtraData = Map<Int, Pair<String, String>>

/**
 * Represents an ASS file. If provided, will parse the given file.
 *
 * Example usage:
 *
 * ```
 * val assFile = ASSFile(File("my_script.ass"))
 * println("Script resolution: ${assFile.scriptInfo.playResX}x${assFile.scriptInfo.playResY}")
 *
 * // increase the layer of every event line by 10
 * assFile.events.lines.forEach { line ->
 *     line.layer += 10
 * }
 * ```
 *
 * @param file The file to parse.
 */
class ASSFile(val file: File? = null) {
    companion object {
        val sectionPattern = Regex("""\[([^\]]+)\]""")
        val linePattern = Regex("""([^:]+): (.*)""")

        const val scriptInfoHeader = "Script Info"
        const val projectGarbageHeader = "Aegisub Project Garbage"
        const val stylesHeader = "V4+ Styles"
        const val eventsHeader = "Events"
        const val extraDataHeader = "Aegisub Extradata"
    }

    /**
     * A map containing the sections of this ASS file, with the names of
     * the sections as they appear in the file as the keys.
     */
    val sections = mutableMapOf(
            scriptInfoHeader to ScriptInfoSection(scriptInfoHeader),
            projectGarbageHeader to AegisubProjectGarbageSection(projectGarbageHeader),
            stylesHeader to StyleSection(stylesHeader),
            eventsHeader to EventSection(eventsHeader)
    )

    /**
     * The [ScriptInfoSection] of the file.
     */
    val scriptInfo
        get() = sections[scriptInfoHeader] as ScriptInfoSection

    /**
     * The [AegisubProjectGarbageSection] of the file.
     */
    val projectGarbage
        get() = sections[projectGarbageHeader] as AegisubProjectGarbageSection

    /**
     * The [StyleSection] of the file.
     */
    val styles
        get() = sections[stylesHeader] as StyleSection

    /**
     * The [EventSection] of the file.
     */
    val events
        get() = sections[eventsHeader] as EventSection

    init {
        if (file != null)
            parse(file)
    }

    /**
     * Parse the given ASS file and add the sections and their containing lines to this instance.
     *
     * @param file The file to parse.
     */
    fun parse(file: File) {
        val sectionMap = file.bufferedReader(StandardCharsets.UTF_8).lineSequence().withIndex()
                // check and remove bom, trim lines, remove empty lines and comments
                .mapNotNull { (i, l) ->
                    val line = l.takeUnless { i == 0 }
                            ?: l.takeIf { it.isEmpty() || it[0] != '\uFEFF' }?.also {
                                println("Warning: ASS file $file does not start with a UTF-8 BOM")
                            } ?: l.substring(1)

                    line.trimStart().takeUnless { it.isEmpty() || it[0] == ';' }
                }
                // group into sections (String -> List<String>)
                .asIterable().group { sectionPattern.matches(it) }
                // remove null sections, rudimentary line parsing
                .mapNotNull { (section, lines) ->
                    if (section == null) {
                        lines.forEach {
                            println("Warning: found line outside section: $it")
                        }
                        null
                    } else {
                        section.drop(1).dropLast(1) to lines.mapNotNull { line ->
                            linePattern.matchEntire(line)?.destructured?.let { (key, value) ->
                                KeyValLine(key, value)
                            } ?: run {
                                println("Warning: could not parse line: $line")
                                null
                            }
                        }
                    }
                }
                // merge identically named sections (if any)
                .groupBy({ it.first }, { it.second }).mapValues { it.value.flatten() }

        val extraData: ExtraData = sectionMap.getOrElse(extraDataHeader) { listOf() }
                .mapNotNull {
                    if (it.type != "Data") {
                        println("Warning: unexpected line type in $extraDataHeader section: $it")
                        null
                    } else {
                        try {
                            val (id, key, value) = it.value.split(",", limit = 3)
                            id.toInt() to Pair(key, value)
                        } catch (e: Exception) {
                            println("Warning: malformed data line: $it")
                            null
                        }
                    }
                }.toMap()

        sectionMap.forEach { (k, v) ->
            if (k != extraDataHeader) {
                // TODO: KeyValSection kills unknown sections
                sections.computeIfAbsent(k) { KeyValSection(k) }.parse(v, extraData)
            }
        }
    }

    /**
     * Returns a sequence of serialized lines, each ending with a newline.
     * The sequence starts with the UTF-8 BOM, which should always be written first
     * to the ASS file.
     *
     * The output must be saved using UTF-8 as the encoding.
     *
     * @param includeScriptInfo Whether to include the Script Info section.
     * @param includeProjectGarbage Whether to include the Aegisub Project Garbage section.
     * Has no effect if the section is empty, in which case the section will not
     * be generated either way.
     * @param includeStyles Whether to include the V4+ Styles section.
     * @param includeEvents Whether to include the Events section.
     * @param includeExtraData Whether to include the Aegisub Extradata section.
     * Has no effect if [includeEvents] is false, in which case the section will not
     * be generated either way.
     */
    fun serialize(
            includeScriptInfo: Boolean = true,
            includeProjectGarbage: Boolean = true,
            includeStyles: Boolean = true,
            includeEvents: Boolean = true,
            includeExtraData: Boolean = true
    ) = sequenceOf("\uFEFF") + sequence {
        if (includeScriptInfo) {
            yieldAll(scriptInfo.serialize())
        }

        if (includeProjectGarbage && projectGarbage.values.isNotEmpty()) {
            yieldAll(projectGarbage.serialize())
        }

        if (includeStyles) {
            yieldAll(styles.serialize())
        }

        if (includeEvents) {
            yieldAll(events.serialize(includeExtraData))
        }
    }.map { "$it\n" }
}

/**
 * A basic line with a type.
 *
 * @property type The type of the line.
 */
abstract class Line(open val type: String) : Serializable

/**
 * Corresponds to a raw ASS line represented textually as `Type: Value`,
 * with the value unparsed.
 *
 * @property value The value of this line.
 */
class KeyValLine(type: String, val value: String) : Line(type) {
    override fun toString() = "$type: $value"
}

/**
 * Annotates a property in a [MapLine] with the name of the corresponding ASS field.
 */
annotation class LineField(val field: String)

/**
 * Provides type-safe parametric access into a [MapLine].
 *
 * See [MapLine] for a more in-depth description.
 */
sealed class LineAccessor<T, L : MapLine<L>>(val field: String) : Serializable

/**
 * A line in a [FormatSection]. The fields contained in this object can be
 * accessed in three ways:
 * 1. Statically, using the properties defined in subtypes;
 * 2. Unsafely as a map, using the ASS field names as keys;
 * 3. Using type-safe accessors.
 *
 * The following three assignments are equivalent:
 *
 * ```
 * eventLine.layer = 10
 * eventLine.put(EventLineAccessor.LAYER, 10)
 * eventLine["Layer"] = 10 // no compiler error if assigning wrong type
 * ```
 *
 * Further, consider these three lines:
 *
 * ```
 * val layer = eventLine.layer // inferred type: Int
 * val layer = eventLine.get(EventLineAccessor.LAYER) // inferred type: Int
 * val layer = eventLine["Layer"] // inferred type: Any?; must be manually cast to Int
 * ```
 *
 * Accessors provide a way for functions to define a type-safe contract while
 * still allowing the user to specify what field to read from or write to.
 * E.g. a function that only operates on [EventLine] fields of type [String]
 * can be defined as follows:
 *
 * ```
 * fun doThing(line: EventLine, accessor: EventLineAccessor<T>) { /* ... */ }
 * ```
 *
 * This is particularly useful for DSLs.
 */
abstract class MapLine<L : MapLine<L>>(type: String) : Line(type), MutableMap<String, Any> {
    private val fieldToParam = this::class.declaredMemberProperties
            .filterIsInstance<KMutableProperty1<out MapLine<L>, *>>()
            .associateBy { it.name }.let { props ->
                this::class.primaryConstructor?.let { constructor ->
                    constructor.parameters.mapNotNull { param ->
                        param.findAnnotation<LineField>()?.let { ann ->
                            props[param.name!!]?.let { prop ->
                                ann.field to prop
                            }
                        }
                    }
                }
            }?.toMap().orEmpty()

    override val size: Int
        get() = fieldToParam.size

    override fun containsKey(key: String) = key in fieldToParam

    override fun containsValue(value: Any) =
            fieldToParam.keys.any { this[it] == value }

    override fun get(key: String): Any? = fieldToParam[key]?.getter?.call(this)

    /**
     * Get a field in a type-safe way using a [LineAccessor].
     */
    fun <T> get(key: LineAccessor<T, L>): T = this[key.field] as T

    override fun isEmpty() = fieldToParam.isEmpty()

    private abstract inner class MutableListIterator<T> : MutableIterator<T> {
        private val iter = fieldToParam.iterator()

        override fun hasNext() = iter.hasNext()

        override fun next(): T = map(iter.next().key)

        override fun remove() = throw UnsupportedOperationException()

        abstract fun map(fieldName: String): T
    }

    override val entries: MutableSet<MutableMap.MutableEntry<String, Any>>
        get() = object : AbstractMutableSet<MutableMap.MutableEntry<String, Any>>() {
            override val size: Int
                get() = fieldToParam.size

            override fun add(element: MutableMap.MutableEntry<String, Any>) =
                    throw UnsupportedOperationException()

            override fun iterator() = object : MapLine<L>.MutableListIterator<MutableMap.MutableEntry<String, Any>>() {
                override fun map(fieldName: String) = object : MutableMap.MutableEntry<String, Any> {
                    override val key: String
                        get() = fieldName
                    override val value: Any
                        get() = this@MapLine[key]!!

                    override fun setValue(newValue: Any) = this@MapLine.put(key, newValue)!!
                }
            }
        }

    override val keys: MutableSet<String>
        get() = object : AbstractMutableSet<String>() {
            override val size: Int
                get() = this@MapLine.size

            override fun add(element: String) = throw UnsupportedOperationException()

            override fun iterator() = object : MapLine<L>.MutableListIterator<String>() {
                override fun map(fieldName: String) = fieldName
            }
        }

    override val values: MutableCollection<Any>
        get() = object : AbstractMutableCollection<Any>() {
            override val size: Int
                get() = this@MapLine.size

            override fun add(element: Any) = throw UnsupportedOperationException()

            override fun iterator() = object : MapLine<L>.MutableListIterator<Any>() {
                override fun map(fieldName: String) = this@MapLine[fieldName]!!
            }
        }

    override fun clear() = throw UnsupportedOperationException()

    override fun put(key: String, value: Any): Any? {
        val old = this[key]
        fieldToParam[key]?.setter?.call(this, value)
        return old
    }

    /**
     * Write to a field in a type-safe way using a [LineAccessor].
     */
    fun <T : Any> put(key: LineAccessor<T, L>, value: T): T? = put(key.field, value) as T?

    /**
     * Writes the given value to the field given by the key, automatically
     * deserializing the value if needed.
     *
     * @throws IllegalArgumentException If [value] could not be deserialized to
     * the appropriate type.
     */
    fun putString(key: String, value: String) =
            fieldToParam[key]?.let { prop ->
                val targetType = prop.returnType.classifier!! as KClass<*>
                val deserialized = deserializeAss(value, targetType)
                        ?: throw IllegalArgumentException(
                                "could not deserialize to $targetType: $value")
                put(key, deserialized)
            }

    override fun putAll(from: Map<out String, Any>) = from.forEach { (k, v) -> put(k, v) }

    override fun remove(key: String) = throw UnsupportedOperationException()
}


/**
 * A type-safe accessor for [EventLine].
 *
 * See [MapLine] for a more in-depth description.
 */
sealed class EventLineAccessor<T>(field: String) : LineAccessor<T, EventLine>(field) {
    object LAYER : EventLineAccessor<Int>("Layer")
    object START : EventLineAccessor<Duration>("Start")
    object END : EventLineAccessor<Duration>("End")
    object STYLE : EventLineAccessor<String>("Style")
    object ACTOR : EventLineAccessor<String>("Name")
    object MARGIN_L : EventLineAccessor<Int>("MarginL")
    object MARGIN_R : EventLineAccessor<Int>("MarginR")
    object MARGIN_V : EventLineAccessor<Int>("MarginV")
    object EFFECT : EventLineAccessor<String>("Effect")
    object TEXT : EventLineAccessor<String>("Text")

    override fun toString() = field
}

/**
 * A line in an [EventSection].
 * Its associated accessor is [EventLineAccessor].
 *
 * See [MapLine] for a more in-depth description.
 */
class EventLine(
        @LineField("Layer")
        var layer: Int = 0,
        @LineField("Start")
        var start: Duration = Duration.ZERO,
        @LineField("End")
        var end: Duration = Duration.ZERO,
        @LineField("Style")
        var style: String = "Default",
        @LineField("Name")
        var actor: String = "",
        @LineField("MarginL")
        var marginL: Int = 0,
        @LineField("MarginR")
        var marginR: Int = 0,
        @LineField("MarginV")
        var marginV: Int = 0,
        @LineField("Effect")
        var effect: String = "",
        @LineField("Text")
        text: String = "",
        comment: Boolean? = null,
        type: String = "Dialogue",
        extraData: ExtraData? = null
) : MapLine<EventLine>(type) {
    companion object {
        val extraDataPattern = Regex("""(\{(?:=\d+)+\})(.*)""")
    }

    val extraData = mutableMapOf<String, String>()
    var text = extraDataPattern.matchEntire(text)?.let { match ->
        val (entries, mainText) = match.destructured
        val extradataEntries = entries.drop(2).dropLast(1).split("=")
                .mapNotNull { extraData?.get(it.toInt()) }
                .map { (key, value) -> key to value }
        this.extraData.putAll(extradataEntries)
        mainText
    } ?: text
    var comment = comment ?: type == "Comment"
    override val type
        get() = if (comment) "Comment" else "Dialogue"
}

/**
 * A type-safe accessor for [StyleLine].
 *
 * See [MapLine] for a more in-depth description.
 */
sealed class StyleLineAccessor<T>(field: String) : LineAccessor<T, StyleLine>(field) {
    object NAME : StyleLineAccessor<String>("Name")
    object FONT_NAME : StyleLineAccessor<String>("Fontname")
    object PRIMARY_COLOR : StyleLineAccessor<Color>("PrimaryColour")
    object SECONDARY_COLOR : StyleLineAccessor<Color>("SecondaryColour")
    object OUTLINE_COLOR : StyleLineAccessor<Color>("OutlineColour")
    object SHADOW_COLOR : StyleLineAccessor<Color>("BackColour")
    object BOLD : StyleLineAccessor<Boolean>("Bold")
    object ITALIC : StyleLineAccessor<Boolean>("Italic")
    object UNDERLINE : StyleLineAccessor<Boolean>("Underline")
    object STRIKEOUT : StyleLineAccessor<Boolean>("StrikeOut")
    object SCALE_X : StyleLineAccessor<Int>("ScaleX")
    object SCALE_Y : StyleLineAccessor<Int>("ScaleY")
    object SPACING : StyleLineAccessor<Double>("Spacing")
    object ROTATION : StyleLineAccessor<Int>("Angle")
    object BORDER_STYLE : StyleLineAccessor<Int>("BorderStyle")
    object OUTLINE : StyleLineAccessor<Double>("Outline")
    object SHADOW : StyleLineAccessor<Double>("Shadow")
    object ALIGNMENT : StyleLineAccessor<Int>("Alignment")
    object MARGIN_L : StyleLineAccessor<Int>("MarginL")
    object MARGIN_R : StyleLineAccessor<Int>("MarginR")
    object MARGIN_V : StyleLineAccessor<Int>("MarginV")
    object ENCODING : StyleLineAccessor<Int>("Encoding")
}

/**
 * A line in a [StyleSection].
 * Its associated accessor is [StyleLineAccessor].
 *
 * See [MapLine] for a more in-depth description.
 */
class StyleLine(
        @LineField("Name")
        var name: String = "Default",
        @LineField("Fontname")
        var font: String = "Arial",
        @LineField("Fontsize")
        var fontSize: Int = 20,
        @LineField("PrimaryColour")
        var primaryColor: Color = Color.WHITE,
        @LineField("SecondaryColour")
        var secondaryColor: Color = Color.RED,
        @LineField("OutlineColour")
        var outlineColor: Color = Color.BLACK,
        @LineField("BackColour")
        var shadowColor: Color = Color.BLACK,
        @LineField("Bold")
        var bold: Boolean = false,
        @LineField("Italic")
        var italic: Boolean = false,
        @LineField("Underline")
        var underline: Boolean = false,
        @LineField("StrikeOut")
        var strikeout: Boolean = false,
        @LineField("ScaleX")
        var scaleX: Int = 100,
        @LineField("ScaleY")
        var scaleY: Int = 100,
        @LineField("Spacing")
        var spacing: Double = 0.0,
        @LineField("Angle")
        var rotation: Int = 0,
        @LineField("BorderStyle")
        var borderStyle: Int = 1,
        @LineField("Outline")
        var outline: Double = 0.0,
        @LineField("Shadow")
        var shadow: Double = 0.0,
        @LineField("Alignment")
        var alignment: Int = 2,
        @LineField("MarginL")
        var marginL: Int = 10,
        @LineField("MarginR")
        var marginR: Int = 10,
        @LineField("MarginV")
        var marginV: Int = 10,
        @LineField("Encoding")
        var encoding: Int = 1,
        type: String = "Style"
) : MapLine<StyleLine>(type)

/**
 * Represents a section in an ASS file, textually represented as a section name
 * in square brackets followed by zero or more lines in a `Type: Value` format.
 *
 * @property name The name of the section, to be serialized as [[name]].
 */
abstract class Section(val name: String) : Serializable {
    /**
     * Deserializes a list of lines in [KeyValLine] format and adds
     * them to this section.
     *
     * @param extraData The extradata read from the input file.
     * Only affects [EventSection].
     */
    abstract fun parse(data: List<KeyValLine>, extraData: ExtraData)

    /**
     * Returns a string sequence representing the serialization of the
     * contents of this section, without the section name.
     *
     * @param includeExtraData Whether to include an extradata section.
     * Only applicable to [EventSection].
     */
    abstract fun serializeContents(includeExtraData: Boolean): Sequence<String>

    /**
     * Returns a string sequence representing the serialization (textual representation)
     * of this section, *without* newlines.
     *
     * @param includeExtraData Whether to include an extradata section.
     * Only applicable to [EventSection].
     */
    fun serialize(includeExtraData: Boolean = true) = sequence {
        yield("[$name]")
        yieldAll(serializeContents(includeExtraData))
        yield("")
    }
}

/**
 * Annotates a property in a [KeyValSection] with the corresponding ASS key.
 *
 * @property key The name of the corresponding key in ASS.
 */
annotation class KeyValField(val key: String)

/**
 * Represents a generic key-value section such as [ScriptInfoSection] and
 * [AegisubProjectGarbageSection], where each line is given in in a
 * `Key: Value` format, and each key is unique.
 */
open class KeyValSection(name: String) : Section(name) {
    /**
     * The values of this section.
     */
    val values = mutableMapOf<String, Any>()

    @Transient private val types = this::class.declaredMemberProperties
            .mapNotNull {
                it.findAnnotation<KeyValField>()?.let { ann ->
                    ann.key to it.returnType.classifier!!
                }
            }.toMap()

    protected inner class KeyValDelegate<T> : Serializable {
        operator fun getValue(thisRef: KeyValSection, property: KProperty<*>) =
                values[property.findAnnotation<KeyValField>()!!.key] as T

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
            val field = property.findAnnotation<KeyValField>()!!.key
            if (value != null) {
                values[field] = value as Any
            } else {
                values.remove(field)
            }
        }
    }

    override fun parse(data: List<KeyValLine>, extraData: ExtraData) {
        data.forEach { line ->
            when (types[line.type]) {
                null -> line.value
                String::class -> line.value
                Int::class -> line.value.toIntOrNull()
                Double::class -> line.value.toDoubleOrNull()
                else -> line.value
            }?.also {
                values[line.type] = it
            } ?: run {
                println("Warning: couldn't parse value of ${line.type}: ${line.value}")
            }
        }
    }

    override fun serializeContents(includeExtraData: Boolean) = sequence {
        values.forEach { (k, v) ->
            yield("$k: $v")
        }
    }
}

/**
 * Represents a script info section.
 *
 * Example usage:
 *
 * ```
 * val scriptInfoSection = assFile.scriptInfo
 * scriptInfoSection.playResX = 1920
 * scriptInfoSection.values["PlayResX"] = 1920 // equivalent to the above
 * ```
 *
 * @param name The name of the section, usually `Script Info`.
 */
class ScriptInfoSection(name: String) : KeyValSection(name) {
    @KeyValField("Title")
    var title: String? by KeyValDelegate()
    @KeyValField("Original Script")
    var originalScript: String? by KeyValDelegate()
    @KeyValField("Original Translation")
    var translation: String? by KeyValDelegate()
    @KeyValField("Original Editing")
    var editing: String? by KeyValDelegate()
    @KeyValField("Original Timing")
    var timing: String? by KeyValDelegate()
    @KeyValField("Synch Point")
    var synchPoint: String? by KeyValDelegate()
    @KeyValField("Script Updated By")
    var updatedBy: String? by KeyValDelegate()
    @KeyValField("Update Details")
    var updateDetails: String? by KeyValDelegate()
    @KeyValField("ScriptType")
    var scriptType: String? by KeyValDelegate()
    @KeyValField("WrapStyle")
    var wrapStyle: Int? by KeyValDelegate()
    @KeyValField("ScaledBorderAndShadow")
    var scaledBorderAndShadow: String? by KeyValDelegate()
    @KeyValField("YCbCr Matrix")
    var colorMatrix: String? by KeyValDelegate()
    @KeyValField("PlayResX")
    var playResX: Int? by KeyValDelegate()
    @KeyValField("PlayResY")
    var playResY: Int? by KeyValDelegate()
}

/**
 * Represents an Aegisub project garbage section.
 *
 * Example usage:
 *
 * ```
 * val projectGarbageSection = assFile.projectGarbage
 * println(projectGarbageSection.videoFile)
 * println(projectGarbageSection.values["Video File"]) // equivalent to the above
 * ```
 *
 * @param name The name of the section, usually `Aegisub Project Garbage`.
 */
class AegisubProjectGarbageSection(name: String) : KeyValSection(name) {
    @KeyValField("Automation Scripts")
    var automationScripts: String? by KeyValDelegate()
    @KeyValField("Export Filters")
    var exportFilters: String? by KeyValDelegate()
    @KeyValField("Export Encoding")
    var exportEncoding: String? by KeyValDelegate()
    @KeyValField("Last Style Storage")
    var lastStyleStorage: String? by KeyValDelegate()
    @KeyValField("Audio File")
    var audioFile: String? by KeyValDelegate()
    @KeyValField("Video File")
    var videoFile: String? by KeyValDelegate()
    @KeyValField("Timecodes File")
    var timecodesFile: String? by KeyValDelegate()
    @KeyValField("Keyframes File")
    var keyframesFile: String? by KeyValDelegate()
    @KeyValField("Video AR Mode")
    var videoARMode: Int? by KeyValDelegate()
    @KeyValField("Video AR Value")
    var videoARValue: Double? by KeyValDelegate()
    @KeyValField("Video Zoom Percent")
    var videoZoomPercent: Double? by KeyValDelegate()
    @KeyValField("Scroll Position")
    var scrollPosition: Int? by KeyValDelegate()
    @KeyValField("Active Line")
    val activeLine: Int? by KeyValDelegate()
    @KeyValField("Video Position")
    var videoPosition: Int? by KeyValDelegate()
}

/**
 * Converts this [Duration] into a time as represented in ASS.
 * See [String.assTime].
 */
fun Duration.toAss() =
        "%d:%02d:%02d.%02d".format(
                toHoursPart(), toMinutesPart(), toSecondsPart(), toMillisPart() / 10
        )

private val timePattern = Regex("""(\d+):(\d{2}):(\d{2})\.(\d{1,6})""")

/**
 * Gets this string as a [Duration], parsing it as an ASS time.
 * The format should follow `H:MM:SS.CC` where H, MM, SS and CC
 * represent the hours, minutes, seconds and centiseconds respectively.
 *
 * @throws IllegalArgumentException If not a valid ASS time.
 */
val String.assTime
    get() = timePattern.matchEntire(this)?.let { match ->
        val (h, m, sec, dec) = match.groupValues.drop(1)
        val us = "%-6s".format(dec).replace(' ', '0')
        val ms = ((h.toLong() * 60 + m.toLong()) * 60 + sec.toLong()) * 1000000 + us.toLong()
        Duration.of(ms, ChronoUnit.MICROS)
    } ?: throw IllegalArgumentException("not a valid time: $this")


/**
 * Converts this color into a color as represented in ASS.
 * See [String.assColor].
 */
fun Color.toAss(includeAlpha: Boolean = true) =
        "&H" + listOf(blue, green, red).let { if (includeAlpha) it + alpha else it }
                .joinToString("") { "%02X".format(it) }

/**
 * Gets this string as a [Color], parsing it as an ASS color.
 * The format should follow `&HBBGGRRAA` where BB, GG, RR and AA
 * are hex values representing the blue, green, red and alpha
 * components respectively.
 *
 * @throws IllegalArgumentException If not a valid ASS color.
 */
val String.assColor
    get() = this.takeIf { it.startsWith("&H") }?.let { s ->
            try {
                val (b, g, r, a) = s.drop(2).chunked(2)
                        .map { it.toInt(16) }
                Color(r, g, b, a)
            } catch (e: Exception) { null }
        } ?: throw IllegalArgumentException("not a valid color: $this")

/**
 * Converts this boolean into a boolean as represented in ASS.
 * See [String.assBoolean].
 */
fun Boolean.toAss() = if (this) "-1" else "0"

/**
 * Gets this string as a [Boolean], parsing it as an ASS boolean.
 * `true` if `-1` and `false` if `0`.
 *
 * @throws IllegalArgumentException If not a valid ASS boolean.
 */
val String.assBoolean
    get() = when (this) {
            "-1" -> true
            "0" -> false
            else -> throw IllegalArgumentException("not a valid boolean: $this")
        }

/**
 * Return the given ASS string deserialized to the given type [T], or `null`
 * if the string could not be deserialized.
 *
 * Supports [String], [Int], [Double], [Boolean], [Color] and [Duration].
 *
 * @param s The string to deserialize.
 * @param klass The class object of the type to deserialize to.
 */
fun <T : Any> deserializeAss(s: String, klass: KClass<T>): T? =
        try {
            when (klass) {
                String::class -> s
                Int::class -> s.toIntOrNull()
                Double::class -> s.toDoubleOrNull()
                Boolean::class -> s.assBoolean
                Color::class -> s.assColor
                Duration::class -> s.assTime
                else -> null
            } as T?
        } catch (e: IllegalArgumentException) { null }

/**
 * Serializes the given value as a string in ASS format.
 *
 * Supports [String], [Int], [Double], [Boolean], [Color] and [Duration].
 *
 * @param v The value to serialize.
 * @throws IllegalArgumentException If the type of the input is unsupported.
 */
fun serializeAss(v: Any): String =
        when (v) {
            is String -> v
            is Int -> v.toString()
            is Double ->
                if (ceil(v) == floor(v)) v.toInt().toString() else v.toString()
            is Boolean -> v.toAss()
            is Color -> v.toAss(true)
            is Duration -> v.toAss()
            else -> throw IllegalArgumentException("Can't deserialize $v")
        }

/**
 * Represents a section containing a sequence of lines, starting with a `Format` line
 * specifying the order of the fields on each line.
 *
 * @param name The name of the section.
 * @param lineClass A class object used for instantiating new lines of type [T].
 * @param T The type of line allowed in this section.
 */
abstract class FormatSection<T : MapLine<T>>(name: String, val lineClass: KClass<T>) : Section(name) {
    /**
     * The order of the fields in the textual representation of each line.
     */
    abstract var format: List<String>
        protected set

    /**
     * The types of lines allowed in this section, e.g. `Dialogue` and `Comment` for
     * [EventSection].
     */
    abstract val lineTypes: Set<String>

    /**
     * The lines contained in this section.
     */
    val lines = mutableListOf<T>()

    override fun parse(data: List<KeyValLine>, extraData: ExtraData) {
        val lines = if (data[0].type != "Format") {
            println("Warning: expected format line in $name section")
            data
        } else {
            format = data[0].value.split(", ")
            data.drop(1)
        }

        lines.forEach {
            if (it.type !in lineTypes) {
                println("Warning: unexpected line type in section $name: ${it.type}")
            } else {
                try {
                    parseLine(it, extraData)
                } catch (e: IllegalArgumentException) {
                    println("Warning: ${e.message}")
                }
            }
        }
    }

    /**
     * Parses a single line from a [KeyValLine] and adds it to this section.
     *
     * @param line The line to parse.
     * @param extraData A map containing the Aegisub extradata read from the input file.
     */
    fun parseLine(line: KeyValLine, extraData: ExtraData) {
        val fields = line.value.split(",", limit=format.size)
        if (fields.size != format.size) {
            println("Warning: too few fields in section $name: $line")
            return
        }

        val constructor = lineClass.primaryConstructor!!
        val fieldToType = constructor.parameters.mapNotNull { param ->
            param.findAnnotation<LineField>()?.let { it.field to Pair(param, param.type) }
        }.toMap()

        val paramToValue = format.zip(fields).mapNotNull { (fieldName, value) ->
            fieldToType[fieldName]?.let { (param, paramType) ->
                val klass = paramType.classifier!! as KClass<*>
                val parsedValue = deserializeAss(value, klass) ?: run {
                    println("Warning: could not parse as $paramType: $value")
                    return@parseLine
                }

                param to parsedValue
            }
        }.toMap(mutableMapOf())

        constructor.findParameterByName("type")
                ?.let { paramToValue[it] = line.type }
        constructor.findParameterByName("extraData")
                ?.let { paramToValue[it] = extraData }

        lines.add(constructor.callBy(paramToValue))
    }

    /**
     * Returns a textual representation of the format line that starts the section.
     */
    fun formatString() = "Format: ${format.joinToString(", ")}"

    override fun serializeContents(includeExtraData: Boolean) = sequence {
        yield(formatString())
        lines.forEach {
            yield(serializeLine(it))
        }
    }

    /**
     * Serializes a single line according to the field order given by [format] in this section.
     *
     * @param line The line object to serialize.
     * @param process An optional callback allowing the caller to modify the line before it
     * gets serialized. Takes a mutable map where the keys are the ASS fields as named in [format].
     * Changes to the map will be reflected in the serialization.
     */
    fun serializeLine(
            line: T,
            process: (MutableMap<String, Any>) -> Unit = { }
    ): String {
        val paramNameToField = lineClass.primaryConstructor!!.parameters.mapNotNull { param ->
            param.findAnnotation<LineField>()?.let { param.name!! to it.field }
        }.toMap()

        val fieldToValue = lineClass.declaredMemberProperties.mapNotNull { prop ->
            paramNameToField[prop.name]?.let {
                it to prop.get(line)!!
            }
        }.toMap(mutableMapOf())
        process(fieldToValue)

        val fieldToSerialized = fieldToValue.mapValues { (_, v) ->
            serializeAss(v)
        }

        val ordered = format.joinToString(",") { fieldToSerialized[it] ?: "" }
        return "${line.type}: $ordered"
    }
}

/**
 * Represents an events section, containing a list of [EventLine] lines.
 *
 * @param name The name of the section, usually `Events`.
 */
class EventSection(name: String) : FormatSection<EventLine>(name, EventLine::class) {
    override var format = listOf(
            "Layer", "Start", "End", "Style", "Name",
            "MarginL", "MarginR", "MarginV", "Effect", "Text"
    )
    override val lineTypes = setOf("Dialogue", "Comment")

    override fun serializeContents(includeExtraData: Boolean) = sequence {
        yield(formatString())
        val extraData = mutableMapOf<Pair<String, String>, Int>()
        lines.forEach { line ->
            if (includeExtraData) {
                yield(serializeLine(line) { fieldToValue ->
                    val extraKeys = line.extraData.map { (k, v) ->
                        extraData.computeIfAbsent(k to v) { extraData.size }
                    }.joinToString("") { "=$it" }

                    if (extraKeys.isNotEmpty()) {
                        fieldToValue["Text"] = "{$extraKeys}${line.text}"
                    }
                })
            } else {
                yield(serializeLine(line))
            }
        }

        if (extraData.isNotEmpty()) {
            yield("")
            yield("[${ASSFile.extraDataHeader}]")
            extraData.forEach { (k, id) ->
                val (key, value) = k
                yield("Data: $id,$key,$value")
            }
        }
    }
}

/**
 * Represents a styles section, containing a list of [StyleLine] lines.
 *
 * @param name The name of the section, usually `V4+ Styles`.
 */
class StyleSection(name: String) : FormatSection<StyleLine>(name, StyleLine::class) {
    override var format = listOf(
            "Name", "Fontname", "Fontsize", "PrimaryColour", "SecondaryColour",
            "OutlineColour", "BackColour", "Bold", "Italic", "Underline", "StrikeOut",
            "ScaleX", "ScaleY", "Spacing", "Angle", "BorderStyle", "Outline", "Shadow",
            "Alignment", "MarginL", "MarginR", "MarginV", "Encoding"
    )
    override val lineTypes = setOf("Style")
}
