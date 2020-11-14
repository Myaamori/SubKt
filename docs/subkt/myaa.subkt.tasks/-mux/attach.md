[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Mux](index.md) / [attach](./attach.md)

# attach

`fun attach(vararg dirs: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, action: `[`ConfigurableFileTree`](https://docs.gradle.org/current/javadoc/org/gradle/api/file/ConfigurableFileTree.html)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` = {}): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ConfigurableFileTree`](https://docs.gradle.org/current/javadoc/org/gradle/api/file/ConfigurableFileTree.html)`>>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.13/src/main/kotlin/myaa/subkt/tasks/muxtask.kt#L740)

Attach one or more files to the output file.

``` kotlin
attach("fonts") {
    includeExtensions("otf", "ttf") // case insensitive
    include("*.ttf", "*.otf") // case sensitive
}
```

### Parameters

`dirs` - A directory, collection of directories, or a single file

`action` - A closure operating on a [ConfigurableAttachment](#) instance,
allowing you to filter what files to include.