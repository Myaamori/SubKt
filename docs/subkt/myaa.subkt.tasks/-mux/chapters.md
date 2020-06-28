[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Mux](index.md) / [chapters](./chapters.md)

# chapters

`fun chapters(file: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, action: `[`Mux.Chapter`](-chapter/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` = {}): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`Mux.Chapter`](-chapter/index.md)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.4/src/main/kotlin/myaa/subkt/tasks/muxtask.kt#L647)

Adds a chapter file.

``` kotlin
chapters("chapters.xml") {
    lang("eng")
}
```

### Parameters

`file` - The file containing the chapters.

`action` - A closure operating on a [Chapter](-chapter/index.md) instance.