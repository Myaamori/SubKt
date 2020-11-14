[subkt](../index.md) / [myaa.subkt.tasks.utils](index.md) / [getMkvInfo](./get-mkv-info.md)

# getMkvInfo

`fun getMkvInfo(file: `[`File`](https://docs.oracle.com/javase/9/docs/api/java/io/File.html)`, mkvmerge: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "mkvmerge"): `[`MkvInfo`](-mkv-info/index.md) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.13/src/main/kotlin/myaa/subkt/tasks/utils/mkvmerge.kt#L142)

Reads metadata such as track and codec information from a Matroska-compatible
file using `mkvmerge`.

### Parameters

`file` - The file to read metadata from.

`mkvmerge` - The path to the `mkvmerge` binary, if not present in your PATH.

### Exceptions

`RuntimeException` - If `mkvmerge` failed.

**Return**
An [MkvInfo](-mkv-info/index.md) instance containing the information read from the file.

