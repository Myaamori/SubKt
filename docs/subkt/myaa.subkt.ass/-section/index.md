[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [Section](./index.md)

# Section

`abstract class Section : `[`Serializable`](https://docs.oracle.com/javase/9/docs/api/java/io/Serializable.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.7/src/main/kotlin/myaa/subkt/ass/parser.kt#L584)

Represents a section in an ASS file, textually represented as a section name
in square brackets followed by zero or more lines in a `Type: Value` format.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Section(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)`<br>Represents a section in an ASS file, textually represented as a section name in square brackets followed by zero or more lines in a `Type: Value` format. |

### Properties

| Name | Summary |
|---|---|
| [name](name.md) | `val name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The name of the section, to be serialized as [[name](name.md)]. |

### Functions

| Name | Summary |
|---|---|
| [parse](parse.md) | `abstract fun parse(data: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`KeyValLine`](../-key-val-line/index.md)`>, extraData: `[`ExtraData`](../-extra-data.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Deserializes a list of lines in [KeyValLine](../-key-val-line/index.md) format and adds them to this section. |
| [serialize](serialize.md) | `fun serialize(includeExtraData: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true): `[`Sequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>Returns a string sequence representing the serialization (textual representation) of this section, *without* newlines. |
| [serializeContents](serialize-contents.md) | `abstract fun serializeContents(includeExtraData: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Sequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>Returns a string sequence representing the serialization of the contents of this section, without the section name. |

### Inheritors

| Name | Summary |
|---|---|
| [FormatSection](../-format-section/index.md) | `abstract class FormatSection<T : `[`MapLine`](../-map-line/index.md)`<`[`T`](../-format-section/index.md#T)`>> : `[`Section`](./index.md)<br>Represents a section containing a sequence of lines, starting with a `Format` line specifying the order of the fields on each line. |
| [KeyValSection](../-key-val-section/index.md) | `open class KeyValSection : `[`Section`](./index.md)<br>Represents a generic key-value section such as [ScriptInfoSection](../-script-info-section/index.md) and [AegisubProjectGarbageSection](../-aegisub-project-garbage-section/index.md), where each line is given in in a `Key: Value` format, and each key is unique. |
