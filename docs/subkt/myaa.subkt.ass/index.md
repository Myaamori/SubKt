[subkt](../index.md) / [myaa.subkt.ass](./index.md)

## Package myaa.subkt.ass

Facilities for parsing ASS files.
The main point of interest is [ASSFile](-a-s-s-file/index.md), which implements the actual parsing of raw ASS files.
Also provided are extensions for converting [java.awt.Color](https://docs.oracle.com/javase/9/docs/api/java/awt/Color.html), [java.time.Duration](https://docs.oracle.com/javase/9/docs/api/java/time/Duration.html) and [kotlin.Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) to and from ASS format.

### Types

| Name | Summary |
|---|---|
| [AegisubProjectGarbageSection](-aegisub-project-garbage-section/index.md) | `class AegisubProjectGarbageSection : `[`KeyValSection`](-key-val-section/index.md)<br>Represents an Aegisub project garbage section. |
| [ASSFile](-a-s-s-file/index.md) | `class ASSFile`<br>Represents an ASS file. If provided, will parse the given file. |
| [EventLine](-event-line/index.md) | `class EventLine : `[`MapLine`](-map-line/index.md)`<`[`EventLine`](-event-line/index.md)`>`<br>A line in an [EventSection](-event-section/index.md). Its associated accessor is [EventLineAccessor](-event-line-accessor/index.md). |
| [EventLineAccessor](-event-line-accessor/index.md) | `sealed class EventLineAccessor<T> : `[`LineAccessor`](-line-accessor/index.md)`<`[`T`](-event-line-accessor/index.md#T)`, `[`EventLine`](-event-line/index.md)`>`<br>A type-safe accessor for [EventLine](-event-line/index.md). |
| [EventSection](-event-section/index.md) | `class EventSection : `[`FormatSection`](-format-section/index.md)`<`[`EventLine`](-event-line/index.md)`>`<br>Represents an events section, containing a list of [EventLine](-event-line/index.md) lines. |
| [FormatSection](-format-section/index.md) | `abstract class FormatSection<T : `[`MapLine`](-map-line/index.md)`<`[`T`](-format-section/index.md#T)`>> : `[`Section`](-section/index.md)<br>Represents a section containing a sequence of lines, starting with a `Format` line specifying the order of the fields on each line. |
| [KeyValLine](-key-val-line/index.md) | `class KeyValLine : `[`Line`](-line/index.md)<br>Corresponds to a raw ASS line represented textually as `Type: Value`, with the value unparsed. |
| [KeyValSection](-key-val-section/index.md) | `open class KeyValSection : `[`Section`](-section/index.md)<br>Represents a generic key-value section such as [ScriptInfoSection](-script-info-section/index.md) and [AegisubProjectGarbageSection](-aegisub-project-garbage-section/index.md), where each line is given in in a `Key: Value` format, and each key is unique. |
| [Line](-line/index.md) | `abstract class Line : `[`Serializable`](https://docs.oracle.com/javase/9/docs/api/java/io/Serializable.html)<br>A basic line with a type. |
| [LineAccessor](-line-accessor/index.md) | `sealed class LineAccessor<T, L : `[`MapLine`](-map-line/index.md)`<`[`L`](-line-accessor/index.md#L)`>> : `[`Serializable`](https://docs.oracle.com/javase/9/docs/api/java/io/Serializable.html)<br>Provides type-safe parametric access into a [MapLine](-map-line/index.md). |
| [MapLine](-map-line/index.md) | `abstract class MapLine<L : `[`MapLine`](-map-line/index.md)`<`[`L`](-map-line/index.md#L)`>> : `[`Line`](-line/index.md)`, `[`MutableMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>`<br>A line in a [FormatSection](-format-section/index.md). The fields contained in this object can be accessed in three ways: |
| [ScriptInfoSection](-script-info-section/index.md) | `class ScriptInfoSection : `[`KeyValSection`](-key-val-section/index.md)<br>Represents a script info section. |
| [Section](-section/index.md) | `abstract class Section : `[`Serializable`](https://docs.oracle.com/javase/9/docs/api/java/io/Serializable.html)<br>Represents a section in an ASS file, textually represented as a section name in square brackets followed by zero or more lines in a `Type: Value` format. |
| [StyleLine](-style-line/index.md) | `class StyleLine : `[`MapLine`](-map-line/index.md)`<`[`StyleLine`](-style-line/index.md)`>`<br>A line in a [StyleSection](-style-section/index.md). Its associated accessor is [StyleLineAccessor](-style-line-accessor/index.md). |
| [StyleLineAccessor](-style-line-accessor/index.md) | `sealed class StyleLineAccessor<T> : `[`LineAccessor`](-line-accessor/index.md)`<`[`T`](-style-line-accessor/index.md#T)`, `[`StyleLine`](-style-line/index.md)`>`<br>A type-safe accessor for [StyleLine](-style-line/index.md). |
| [StyleSection](-style-section/index.md) | `class StyleSection : `[`FormatSection`](-format-section/index.md)`<`[`StyleLine`](-style-line/index.md)`>`<br>Represents a styles section, containing a list of [StyleLine](-style-line/index.md) lines. |

### Annotations

| Name | Summary |
|---|---|
| [KeyValField](-key-val-field/index.md) | `annotation class KeyValField`<br>Annotates a property in a [KeyValSection](-key-val-section/index.md) with the corresponding ASS key. |
| [LineField](-line-field/index.md) | `annotation class LineField`<br>Annotates a property in a [MapLine](-map-line/index.md) with the name of the corresponding ASS field. |

### Type Aliases

| Name | Summary |
|---|---|
| [ExtraData](-extra-data.md) | `typealias ExtraData = `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, `[`Pair`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>` |

### Extensions for External Classes

| Name | Summary |
|---|---|
| [java.awt.Color](java.awt.-color/index.md) |  |
| [java.time.Duration](java.time.-duration/index.md) |  |
| [kotlin.Boolean](kotlin.-boolean/index.md) |  |
| [kotlin.String](kotlin.-string/index.md) |  |

### Functions

| Name | Summary |
|---|---|
| [deserializeAss](deserialize-ass.md) | `fun <T : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`> deserializeAss(s: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, klass: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<`[`T`](deserialize-ass.md#T)`>): `[`T`](deserialize-ass.md#T)`?`<br>Return the given ASS string deserialized to the given type [T](deserialize-ass.md#T), or `null` if the string could not be deserialized. |
| [serializeAss](serialize-ass.md) | `fun serializeAss(v: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Serializes the given value as a string in ASS format. |
