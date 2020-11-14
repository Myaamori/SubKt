[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Merge](index.md) / [fromMergeTemplate](./from-merge-template.md)

# fromMergeTemplate

`fun fromMergeTemplate(file: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.13/src/main/kotlin/myaa/subkt/tasks/asstasks.kt#L249)

Merges files as defined in a template file following
[Merge Scripts](https://github.com/TypesettingTools/Myaamori-Aegisub-Scripts/#merge-scripts)
syntax.

**Note**: Currently, lines other than import definitions are ignored, and will
not be present in the merged file.

### Parameters

`file` - The path to the merge template file.