[subkt](../../../index.md) / [myaa.subkt.tasks](../../index.md) / [Merge](../index.md) / [MergeSpecification](index.md) / [syncTargetLine](./sync-target-line.md)

# syncTargetLine

`val syncTargetLine: `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`Merge.LineSpecification`](../-line-specification/index.md)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.19/src/main/kotlin/myaa/subkt/tasks/asstasks.kt#L148)`fun syncTargetLine(fieldValue: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, fieldName: `[`EventLineAccessor`](../../../myaa.subkt.ass/-event-line-accessor/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`> = EventLineAccessor.EFFECT): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.19/src/main/kotlin/myaa/subkt/tasks/asstasks.kt#L157)

Specifies a target line which the line specified by [syncSourceLine](sync-source-line.md)
should be shifted to. The target line may be present anywhere in the merged file.

### Parameters

`fieldValue` - The value which identifies the target sync line

`fieldName` - The field in which to look for [fieldValue](sync-target-line.md#myaa.subkt.tasks.Merge.MergeSpecification$syncTargetLine(kotlin.String, myaa.subkt.ass.EventLineAccessor((kotlin.String)))/fieldValue)