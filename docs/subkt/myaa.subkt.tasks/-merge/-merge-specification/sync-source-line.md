[subkt](../../../index.md) / [myaa.subkt.tasks](../../index.md) / [Merge](../index.md) / [MergeSpecification](index.md) / [syncSourceLine](./sync-source-line.md)

# syncSourceLine

`val syncSourceLine: `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`Merge.LineSpecification`](../-line-specification/index.md)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.11/src/main/kotlin/myaa/subkt/tasks/asstasks.kt#L117)`fun syncSourceLine(fieldValue: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, fieldName: `[`EventLineAccessor`](../../../myaa.subkt.ass/-event-line-accessor/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`> = EventLineAccessor.EFFECT): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.11/src/main/kotlin/myaa/subkt/tasks/asstasks.kt#L129)

Specifies a line in the included file (e.g. a song file) to serve as
a reference for shifting all lines in this file. All lines
will be shifted so that the start time of the specified source line
matches the time specified by [syncTargetTime](sync-target-time.md) or the start time
of the line specified by [syncTargetLine](sync-target-line.md).

### Parameters

`fieldValue` - The value which identifies the source sync line

`fieldName` - The field in which to look for [fieldValue](sync-source-line.md#myaa.subkt.tasks.Merge.MergeSpecification$syncSourceLine(kotlin.String, myaa.subkt.ass.EventLineAccessor((kotlin.String)))/fieldValue)