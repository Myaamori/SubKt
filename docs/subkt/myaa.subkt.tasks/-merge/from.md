[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Merge](index.md) / [from](./from.md)

# from

`fun from(vararg files: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, action: `[`Merge.MergeSpecification`](-merge-specification/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` = {}): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Adds files to merge.

``` kotlin
from("file1.ass", "file2.ass") {
    shiftBy(Duration.ofSeconds(10))
}
```

### Parameters

`files` - The files to merge.

`action` - A closure operating on a [MergeSpecification](-merge-specification/index.md) instance
for customizing how the given files should be merged.