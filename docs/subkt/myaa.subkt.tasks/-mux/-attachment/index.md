[subkt](../../../index.md) / [myaa.subkt.tasks](../../index.md) / [Mux](../index.md) / [Attachment](./index.md)

# Attachment

`inner class Attachment : `[`Filterable`](../../-filterable/index.md) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.12/src/main/kotlin/myaa/subkt/tasks/muxtask.kt#L55)

Represents an attachment present in a [MuxFile](../-mux-file/index.md).

### Types

| Name | Summary |
|---|---|
| [AttachmentInfo](-attachment-info/index.md) | `inner class AttachmentInfo`<br>Stores information extracted from the attachment. |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Attachment(sourceAttachment: `[`MkvAttachment`](../../../myaa.subkt.tasks.utils/-mkv-attachment/index.md)`)`<br>Represents an attachment present in a [MuxFile](../-mux-file/index.md). |

### Properties

| Name | Summary |
|---|---|
| [attachment](attachment.md) | `val attachment: `[`Mux.Attachment.AttachmentInfo`](-attachment-info/index.md)<br>An [AttachmentInfo](-attachment-info/index.md) instance providing information about the attachment. |
| [id](id.md) | `val id: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)<br>The ID of this attachment. |
| [include](include.md) | `val include: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>`<br>Whether to include this attachment in the output file. Defaults to true. |
| [sourceAttachment](source-attachment.md) | `val sourceAttachment: `[`MkvAttachment`](../../../myaa.subkt.tasks.utils/-mkv-attachment/index.md)<br>Raw attachment info from mkvmerge. |

### Functions

| Name | Summary |
|---|---|
| [toString](to-string.md) | `fun toString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
