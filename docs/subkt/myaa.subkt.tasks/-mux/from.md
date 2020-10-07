[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Mux](index.md) / [from](./from.md)

# from

`fun from(vararg files: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, action: `[`Mux.MuxFile`](-mux-file/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` = {}): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Mux.MuxFile`](-mux-file/index.md)`>>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.10/src/main/kotlin/myaa/subkt/tasks/muxtask.kt#L726)

Adds files to mux.

``` kotlin
from("premux.mkv") {
    includeChapters(false)

    tracks {

    }

    attachments {
        // remove all attachments from the source file
        include(false)
    }
}
```

### Parameters

`files` - The files to mux.

`action` - A closure operating on a [MuxFile](-mux-file/index.md) instance
for customizing the output.