[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [ScriptInfoSection](./index.md)

# ScriptInfoSection

`class ScriptInfoSection : `[`KeyValSection`](../-key-val-section/index.md) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.9/src/main/kotlin/myaa/subkt/ass/parser.kt#L694)

Represents a script info section.

Example usage:

```
val scriptInfoSection = assFile.scriptInfo
scriptInfoSection.playResX = 1920
scriptInfoSection.values["PlayResX"] = 1920 // equivalent to the above
```

### Parameters

`name` - The name of the section, usually `Script Info`.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ScriptInfoSection(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)`<br>Represents a script info section. |

### Properties

| Name | Summary |
|---|---|
| [colorMatrix](color-matrix.md) | `var colorMatrix: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [editing](editing.md) | `var editing: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [originalScript](original-script.md) | `var originalScript: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [playResX](play-res-x.md) | `var playResX: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`?` |
| [playResY](play-res-y.md) | `var playResY: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`?` |
| [scaledBorderAndShadow](scaled-border-and-shadow.md) | `var scaledBorderAndShadow: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [scriptType](script-type.md) | `var scriptType: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [synchPoint](synch-point.md) | `var synchPoint: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [timing](timing.md) | `var timing: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [title](title.md) | `var title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [translation](translation.md) | `var translation: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [updatedBy](updated-by.md) | `var updatedBy: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [updateDetails](update-details.md) | `var updateDetails: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [wrapStyle](wrap-style.md) | `var wrapStyle: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`?` |

### Inherited Properties

| Name | Summary |
|---|---|
| [values](../-key-val-section/values.md) | `val values: `[`MutableMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>`<br>The values of this section. |

### Inherited Functions

| Name | Summary |
|---|---|
| [parse](../-key-val-section/parse.md) | `open fun parse(data: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`KeyValLine`](../-key-val-line/index.md)`>, extraData: `[`ExtraData`](../-extra-data.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Deserializes a list of lines in [KeyValLine](../-key-val-line/index.md) format and adds them to this section. |
| [serializeContents](../-key-val-section/serialize-contents.md) | `open fun serializeContents(includeExtraData: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Sequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>Returns a string sequence representing the serialization of the contents of this section, without the section name. |
