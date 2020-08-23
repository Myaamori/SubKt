[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [AegisubProjectGarbageSection](./index.md)

# AegisubProjectGarbageSection

`class AegisubProjectGarbageSection : `[`KeyValSection`](../-key-val-section/index.md) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.8/src/main/kotlin/myaa/subkt/ass/parser.kt#L736)

Represents an Aegisub project garbage section.

Example usage:

```
val projectGarbageSection = assFile.projectGarbage
println(projectGarbageSection.videoFile)
println(projectGarbageSection.values["Video File"]) // equivalent to the above
```

### Parameters

`name` - The name of the section, usually `Aegisub Project Garbage`.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `AegisubProjectGarbageSection(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)`<br>Represents an Aegisub project garbage section. |

### Properties

| Name | Summary |
|---|---|
| [activeLine](active-line.md) | `val activeLine: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`?` |
| [audioFile](audio-file.md) | `var audioFile: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [automationScripts](automation-scripts.md) | `var automationScripts: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [exportEncoding](export-encoding.md) | `var exportEncoding: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [exportFilters](export-filters.md) | `var exportFilters: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [keyframesFile](keyframes-file.md) | `var keyframesFile: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [lastStyleStorage](last-style-storage.md) | `var lastStyleStorage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [scrollPosition](scroll-position.md) | `var scrollPosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`?` |
| [timecodesFile](timecodes-file.md) | `var timecodesFile: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [videoARMode](video-a-r-mode.md) | `var videoARMode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`?` |
| [videoARValue](video-a-r-value.md) | `var videoARValue: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`?` |
| [videoFile](video-file.md) | `var videoFile: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [videoPosition](video-position.md) | `var videoPosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`?` |
| [videoZoomPercent](video-zoom-percent.md) | `var videoZoomPercent: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`?` |

### Inherited Properties

| Name | Summary |
|---|---|
| [values](../-key-val-section/values.md) | `val values: `[`MutableMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>`<br>The values of this section. |

### Inherited Functions

| Name | Summary |
|---|---|
| [parse](../-key-val-section/parse.md) | `open fun parse(data: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`KeyValLine`](../-key-val-line/index.md)`>, extraData: `[`ExtraData`](../-extra-data.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Deserializes a list of lines in [KeyValLine](../-key-val-line/index.md) format and adds them to this section. |
| [serializeContents](../-key-val-section/serialize-contents.md) | `open fun serializeContents(includeExtraData: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Sequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>Returns a string sequence representing the serialization of the contents of this section, without the section name. |
