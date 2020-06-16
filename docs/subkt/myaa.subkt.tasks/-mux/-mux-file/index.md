[subkt](../../../index.md) / [myaa.subkt.tasks](../../index.md) / [Mux](../index.md) / [MuxFile](./index.md)

# MuxFile

`inner class MuxFile`

Represents a file to mux, added using [from](../from.md).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MuxFile(file: `[`File`](https://docs.oracle.com/javase/9/docs/api/java/io/File.html)`)`<br>Represents a file to mux, added using [from](../from.md). |

### Properties

| Name | Summary |
|---|---|
| [attachments](attachments.md) | `val attachments: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Mux.Attachment`](../-attachment/index.md)`>`<br>The attachments present in this file. |
| [chapters](chapters.md) | `val chapters: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`?`<br>The number of chapter entries present in this file. |
| [file](file.md) | `val file: `[`File`](https://docs.oracle.com/javase/9/docs/api/java/io/File.html)<br>The source file. |
| [fileOptions](file-options.md) | `val fileOptions: `[`ListProperty`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/ListProperty.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>Any additional file-specific mkvmerge options you wish to include. |
| [includeChapters](include-chapters.md) | `val includeChapters: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>`<br>Whether to keep the chapters present in this file, if any. Defaults to true. |
| [info](info.md) | `val info: `[`MkvInfo`](../../../myaa.subkt.tasks.mkvmerge/-mkv-info/index.md)<br>Raw file info from mkvmerge. |
| [tracks](tracks.md) | `val tracks: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Mux.Track`](../-track/index.md)`>`<br>The tracks present in this file. |

### Functions

| Name | Summary |
|---|---|
| [attachments](attachments.md) | `fun attachments(action: `[`Mux.Attachment`](../-attachment/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Configures the attachments in this file. |
| [toString](to-string.md) | `fun toString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [tracks](tracks.md) | `fun tracks(action: `[`Mux.Track`](../-track/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Configures the tracks in this file. |
