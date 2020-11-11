[subkt](../index.md) / [myaa.subkt.tasks.utils](./index.md)

## Package myaa.subkt.tasks.utils

### Types

| Name | Summary |
|---|---|
| [FontReport](-font-report/index.md) | `class FontReport` |
| [MkvAttachment](-mkv-attachment/index.md) | `data class MkvAttachment : `[`Serializable`](https://docs.oracle.com/javase/9/docs/api/java/io/Serializable.html) |
| [MkvAttachmentProperty](-mkv-attachment-property/index.md) | `data class MkvAttachmentProperty : `[`Serializable`](https://docs.oracle.com/javase/9/docs/api/java/io/Serializable.html) |
| [MkvChapter](-mkv-chapter/index.md) | `data class MkvChapter : `[`Serializable`](https://docs.oracle.com/javase/9/docs/api/java/io/Serializable.html) |
| [MkvContainer](-mkv-container/index.md) | `data class MkvContainer : `[`Serializable`](https://docs.oracle.com/javase/9/docs/api/java/io/Serializable.html) |
| [MkvContainerProperties](-mkv-container-properties/index.md) | `data class MkvContainerProperties : `[`Serializable`](https://docs.oracle.com/javase/9/docs/api/java/io/Serializable.html) |
| [MkvContainerPropertiesProgram](-mkv-container-properties-program/index.md) | `data class MkvContainerPropertiesProgram : `[`Serializable`](https://docs.oracle.com/javase/9/docs/api/java/io/Serializable.html) |
| [MkvGlobalTag](-mkv-global-tag/index.md) | `data class MkvGlobalTag : `[`Serializable`](https://docs.oracle.com/javase/9/docs/api/java/io/Serializable.html) |
| [MkvInfo](-mkv-info/index.md) | `data class MkvInfo : `[`Serializable`](https://docs.oracle.com/javase/9/docs/api/java/io/Serializable.html) |
| [MkvTrack](-mkv-track/index.md) | `data class MkvTrack : `[`Serializable`](https://docs.oracle.com/javase/9/docs/api/java/io/Serializable.html) |
| [MkvTrackProperties](-mkv-track-properties/index.md) | `data class MkvTrackProperties : `[`Serializable`](https://docs.oracle.com/javase/9/docs/api/java/io/Serializable.html) |
| [MkvTrackTag](-mkv-track-tag/index.md) | `data class MkvTrackTag : `[`Serializable`](https://docs.oracle.com/javase/9/docs/api/java/io/Serializable.html) |
| [State](-state/index.md) | `data class State` |

### Functions

| Name | Summary |
|---|---|
| [getMkvInfo](get-mkv-info.md) | `fun getMkvInfo(file: `[`File`](https://docs.oracle.com/javase/9/docs/api/java/io/File.html)`, mkvmerge: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "mkvmerge"): `[`MkvInfo`](-mkv-info/index.md)<br>Reads metadata such as track and codec information from a Matroska-compatible file using `mkvmerge`. |
| [parseLines](parse-lines.md) | `fun parseLines(assFile: `[`ASSFile`](../myaa.subkt.ass/-a-s-s-file/index.md)`): `[`Sequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)`<`[`Sequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)`<`[`Pair`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)`<`[`State`](-state/index.md)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>>` |
| [verifyFonts](verify-fonts.md) | `fun verifyFonts(assFile: `[`ASSFile`](../myaa.subkt.ass/-a-s-s-file/index.md)`, fontFiles: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`File`](https://docs.oracle.com/javase/9/docs/api/java/io/File.html)`>): `[`FontReport`](-font-report/index.md) |
