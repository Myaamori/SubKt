[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [ASSFile](./index.md)

# ASSFile

`class ASSFile` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.7/src/main/kotlin/myaa/subkt/ass/parser.kt#L82)

Represents an ASS file. If provided, will parse the given file.

Example usage:

```
val assFile = ASSFile(File("my_script.ass"))
println("Script resolution: ${assFile.scriptInfo.playResX}x${assFile.scriptInfo.playResY}")

// increase the layer of every event line by 10
assFile.events.lines.forEach { line ->
    line.layer += 10
}
```

### Parameters

`file` - The file to parse.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ASSFile(file: `[`File`](https://docs.oracle.com/javase/9/docs/api/java/io/File.html)`? = null)`<br>Represents an ASS file. If provided, will parse the given file. |

### Properties

| Name | Summary |
|---|---|
| [events](events.md) | `val events: `[`EventSection`](../-event-section/index.md)<br>The [EventSection](../-event-section/index.md) of the file. |
| [file](file.md) | `val file: `[`File`](https://docs.oracle.com/javase/9/docs/api/java/io/File.html)`?`<br>The file to parse. |
| [projectGarbage](project-garbage.md) | `val projectGarbage: `[`AegisubProjectGarbageSection`](../-aegisub-project-garbage-section/index.md)<br>The [AegisubProjectGarbageSection](../-aegisub-project-garbage-section/index.md) of the file. |
| [scriptInfo](script-info.md) | `val scriptInfo: `[`ScriptInfoSection`](../-script-info-section/index.md)<br>The [ScriptInfoSection](../-script-info-section/index.md) of the file. |
| [sections](sections.md) | `val sections: `[`MutableMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`Section`](../-section/index.md)`>`<br>A map containing the sections of this ASS file, with the names of the sections as they appear in the file as the keys. |
| [styles](styles.md) | `val styles: `[`StyleSection`](../-style-section/index.md)<br>The [StyleSection](../-style-section/index.md) of the file. |

### Functions

| Name | Summary |
|---|---|
| [parse](parse.md) | `fun parse(file: `[`File`](https://docs.oracle.com/javase/9/docs/api/java/io/File.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Parse the given ASS file and add the sections and their containing lines to this instance. |
| [serialize](serialize.md) | `fun serialize(includeScriptInfo: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true, includeProjectGarbage: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true, includeStyles: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true, includeEvents: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true, includeExtraData: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true): `[`Sequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>Returns a sequence of serialized lines, each ending with a newline. The sequence starts with the UTF-8 BOM, which should always be written first to the ASS file. |

### Companion Object Properties

| Name | Summary |
|---|---|
| [eventsHeader](events-header.md) | `const val eventsHeader: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [extraDataHeader](extra-data-header.md) | `const val extraDataHeader: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [linePattern](line-pattern.md) | `val linePattern: `[`Regex`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-regex/index.html) |
| [projectGarbageHeader](project-garbage-header.md) | `const val projectGarbageHeader: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [scriptInfoHeader](script-info-header.md) | `const val scriptInfoHeader: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [sectionPattern](section-pattern.md) | `val sectionPattern: `[`Regex`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-regex/index.html) |
| [stylesHeader](styles-header.md) | `const val stylesHeader: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
