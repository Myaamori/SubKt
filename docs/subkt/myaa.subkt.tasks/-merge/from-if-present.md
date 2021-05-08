[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Merge](index.md) / [fromIfPresent](./from-if-present.md)

# fromIfPresent

`fun fromIfPresent(vararg files: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, ignoreMissingFiles: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, action: `[`Merge.MergeSpecification`](-merge-specification/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` = {}): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.19/src/main/kotlin/myaa/subkt/tasks/asstasks.kt#L268)

Adds files to merge, unless the given provider (property) isn't defined.
Will still result in an error if the property is defined
but the file it points to is missing on the file system,
unless [ignoreMissingFiles](from-if-present.md#myaa.subkt.tasks.Merge$fromIfPresent(kotlin.Array((kotlin.Any)), kotlin.Boolean, kotlin.Function1((myaa.subkt.tasks.Merge.MergeSpecification, kotlin.Unit)))/ignoreMissingFiles) is `true`.

``` kotlin
// property file: {01,03}.OP=OP_${episode}.ass
// silently ignores the input for episodes other than 01 and 03
// (for which the property isn't defined); fails for episodes
// 01 and 03 if e.g. OP_01.ass doesn't exist.
fromIfPresent(get("OP"))

// doesn't fail even if e.g. OP_01.ass doesn't exist.
fromIfPresent(get("OP"), ignoreMissingFiles = true)
```

### Parameters

`files` - The files to merge.

`ignoreMissingFiles` - Whether to silently ignore files missing
from the file system.

`action` - A closure operating on a [MergeSpecification](-merge-specification/index.md) instance
for customizing how the given files should be merged.