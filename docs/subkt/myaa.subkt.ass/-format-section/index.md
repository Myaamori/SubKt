[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [FormatSection](./index.md)

# FormatSection

`abstract class FormatSection<T : `[`MapLine`](../-map-line/index.md)`<`[`T`](index.md#T)`>> : `[`Section`](../-section/index.md) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.13/src/main/kotlin/myaa/subkt/ass/parser.kt#L988)

Represents a section containing a sequence of lines, starting with a `Format` line
specifying the order of the fields on each line.

### Parameters

`name` - The name of the section.

`lineClass` - A class object used for instantiating new lines of type [T](index.md#T).

`T` - The type of line allowed in this section.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FormatSection(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, lineClass: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<`[`T`](index.md#T)`>)`<br>Represents a section containing a sequence of lines, starting with a `Format` line specifying the order of the fields on each line. |

### Properties

| Name | Summary |
|---|---|
| [format](format.md) | `abstract var format: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>The order of the fields in the textual representation of each line. |
| [lineClass](line-class.md) | `val lineClass: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<`[`T`](index.md#T)`>`<br>A class object used for instantiating new lines of type [T](index.md#T). |
| [lines](lines.md) | `val lines: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`T`](index.md#T)`>`<br>The lines contained in this section. |
| [lineTypes](line-types.md) | `abstract val lineTypes: `[`Set`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>The types of lines allowed in this section, e.g. `Dialogue` and `Comment` for [EventSection](../-event-section/index.md). |

### Inherited Properties

| Name | Summary |
|---|---|
| [name](../-section/name.md) | `val name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The name of the section, to be serialized as [[name](../-section/name.md)]. |

### Functions

| Name | Summary |
|---|---|
| [formatString](format-string.md) | `fun formatString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Returns a textual representation of the format line that starts the section. |
| [parse](parse.md) | `open fun parse(data: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`KeyValLine`](../-key-val-line/index.md)`>, extraData: `[`ExtraData`](../-extra-data.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Deserializes a list of lines in [KeyValLine](../-key-val-line/index.md) format and adds them to this section. |
| [parseLine](parse-line.md) | `fun parseLine(line: `[`KeyValLine`](../-key-val-line/index.md)`, extraData: `[`ExtraData`](../-extra-data.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Parses a single line from a [KeyValLine](../-key-val-line/index.md) and adds it to this section. |
| [serializeContents](serialize-contents.md) | `open fun serializeContents(includeExtraData: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Sequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>Returns a string sequence representing the serialization of the contents of this section, without the section name. |
| [serializeLine](serialize-line.md) | `fun serializeLine(line: `[`T`](index.md#T)`, process: (`[`MutableMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` = { }): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Serializes a single line according to the field order given by [format](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/format.html) in this section. |

### Inherited Functions

| Name | Summary |
|---|---|
| [serialize](../-section/serialize.md) | `fun serialize(includeExtraData: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true): `[`Sequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>Returns a string sequence representing the serialization (textual representation) of this section, *without* newlines. |

### Inheritors

| Name | Summary |
|---|---|
| [EventSection](../-event-section/index.md) | `class EventSection : `[`FormatSection`](./index.md)`<`[`EventLine`](../-event-line/index.md)`>`<br>Represents an events section, containing a list of [EventLine](../-event-line/index.md) lines. |
| [StyleSection](../-style-section/index.md) | `class StyleSection : `[`FormatSection`](./index.md)`<`[`StyleLine`](../-style-line/index.md)`>`<br>Represents a styles section, containing a list of [StyleLine](../-style-line/index.md) lines. |
