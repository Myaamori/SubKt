[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [KeyValSection](./index.md)

# KeyValSection

`open class KeyValSection : `[`Section`](../-section/index.md) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.4/src/main/kotlin/myaa/subkt/ass/parser.kt#L628)

Represents a generic key-value section such as [ScriptInfoSection](../-script-info-section/index.md) and
[AegisubProjectGarbageSection](../-aegisub-project-garbage-section/index.md), where each line is given in in a
`Key: Value` format, and each key is unique.

### Types

| Name | Summary |
|---|---|
| [KeyValDelegate](-key-val-delegate/index.md) | `inner class KeyValDelegate<T> : `[`Serializable`](https://docs.oracle.com/javase/9/docs/api/java/io/Serializable.html) |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `KeyValSection(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)`<br>Represents a generic key-value section such as [ScriptInfoSection](../-script-info-section/index.md) and [AegisubProjectGarbageSection](../-aegisub-project-garbage-section/index.md), where each line is given in in a `Key: Value` format, and each key is unique. |

### Properties

| Name | Summary |
|---|---|
| [values](values.md) | `val values: `[`MutableMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>`<br>The values of this section. |

### Inherited Properties

| Name | Summary |
|---|---|
| [name](../-section/name.md) | `val name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The name of the section, to be serialized as [[name](../-section/name.md)]. |

### Functions

| Name | Summary |
|---|---|
| [parse](parse.md) | `open fun parse(data: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`KeyValLine`](../-key-val-line/index.md)`>, extraData: `[`ExtraData`](../-extra-data.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Deserializes a list of lines in [KeyValLine](../-key-val-line/index.md) format and adds them to this section. |
| [serializeContents](serialize-contents.md) | `open fun serializeContents(includeExtraData: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Sequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>Returns a string sequence representing the serialization of the contents of this section, without the section name. |

### Inherited Functions

| Name | Summary |
|---|---|
| [serialize](../-section/serialize.md) | `fun serialize(includeExtraData: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true): `[`Sequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>Returns a string sequence representing the serialization (textual representation) of this section, *without* newlines. |

### Inheritors

| Name | Summary |
|---|---|
| [AegisubProjectGarbageSection](../-aegisub-project-garbage-section/index.md) | `class AegisubProjectGarbageSection : `[`KeyValSection`](./index.md)<br>Represents an Aegisub project garbage section. |
| [ScriptInfoSection](../-script-info-section/index.md) | `class ScriptInfoSection : `[`KeyValSection`](./index.md)<br>Represents a script info section. |
