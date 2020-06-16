[subkt](../index.md) / [myaa.subkt.tasks.mkvmerge](./index.md)

## Package myaa.subkt.tasks.mkvmerge

### Types

| Name | Summary |
|---|---|
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

### Functions

| Name | Summary |
|---|---|
| [getMkvInfo](get-mkv-info.md) | `fun getMkvInfo(file: `[`File`](https://docs.oracle.com/javase/9/docs/api/java/io/File.html)`, mkvmerge: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "mkvmerge"): `[`MkvInfo`](-mkv-info/index.md)<br>Reads metadata such as track and codec information from a Matroska-compatible file using `mkvmerge`. |
