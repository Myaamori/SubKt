[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Mux](index.md) / [attach](./attach.md)

# attach

`fun attach(dir: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, action: `[`Mux.ConfigurableAttachment`](-configurable-attachment/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` = {}): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`Mux.ConfigurableAttachment`](-configurable-attachment/index.md)`>`

Attach one or more files to the output file.

``` kotlin
attach("fonts") {
    includeExtensions("otf", "ttf") // case insensitive
    include("*.ttf", "*.otf") // case sensitive
}
```

### Parameters

`dir` - A directory or a single file

`action` - A closure operating on a [ConfigurableAttachment](-configurable-attachment/index.md) instance,
allowing you to filter what files to include.