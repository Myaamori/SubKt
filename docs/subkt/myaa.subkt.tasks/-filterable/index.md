[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Filterable](./index.md)

# Filterable

`interface Filterable`

Represents an item that can be optionally discarded.

### Properties

| Name | Summary |
|---|---|
| [id](id.md) | `abstract val id: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)<br>The ID of the item. |
| [include](include.md) | `abstract val include: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>`<br>Whether to include the item. |

### Inheritors

| Name | Summary |
|---|---|
| [Attachment](../-mux/-attachment/index.md) | `inner class Attachment : `[`Filterable`](./index.md)<br>Represents an attachment present in a [MuxFile](../-mux/-mux-file/index.md). |
| [Track](../-mux/-track/index.md) | `inner class Track : `[`Filterable`](./index.md)<br>Represents a single track read from the source file. |
