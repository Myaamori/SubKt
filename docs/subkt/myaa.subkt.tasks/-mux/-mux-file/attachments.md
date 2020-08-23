[subkt](../../../index.md) / [myaa.subkt.tasks](../../index.md) / [Mux](../index.md) / [MuxFile](index.md) / [attachments](./attachments.md)

# attachments

`val attachments: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Mux.Attachment`](../-attachment/index.md)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.8/src/main/kotlin/myaa/subkt/tasks/muxtask.kt#L360)

The attachments present in this file.

**Getter**

The attachments present in this file.

`fun attachments(action: `[`Mux.Attachment`](../-attachment/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.8/src/main/kotlin/myaa/subkt/tasks/muxtask.kt#L438)

Configures the attachments in this file.

``` kotlin
from("video.mkv") {
    attachments {
        // don't include any attachments from the original file
        include(false)
    }
}
```

### Parameters

`action` - A closure operating on an [Attachment](../-attachment/index.md) instance.