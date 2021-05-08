[subkt](../../../index.md) / [myaa.subkt.tasks](../../index.md) / [Merge](../index.md) / [MergeSpecification](./index.md)

# MergeSpecification

`inner class MergeSpecification` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.19/src/main/kotlin/myaa/subkt/tasks/asstasks.kt#L107)

Defines how the files associated with this specification should be merged.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MergeSpecification()`<br>Defines how the files associated with this specification should be merged. |

### Properties

| Name | Summary |
|---|---|
| [incrementLayer](increment-layer.md) | `val incrementLayer: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>`<br>Increments the layer of all event lines by the specified amount. |
| [shiftBy](shift-by.md) | `val shiftBy: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Duration`](https://docs.oracle.com/javase/9/docs/api/java/time/Duration.html)`!>`<br>Shifts the start and end times of all event lines by the specified [Duration](https://docs.oracle.com/javase/9/docs/api/java/time/Duration.html). |
| [syncSourceLine](sync-source-line.md) | `val syncSourceLine: `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`Merge.LineSpecification`](../-line-specification/index.md)`>` |
| [syncTargetLine](sync-target-line.md) | `val syncTargetLine: `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`Merge.LineSpecification`](../-line-specification/index.md)`>` |
| [syncTargetTime](sync-target-time.md) | `val syncTargetTime: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Duration`](https://docs.oracle.com/javase/9/docs/api/java/time/Duration.html)`>`<br>Specifies a target time which the line specified by [syncSourceLine](sync-source-line.md) should be shifted to. |

### Functions

| Name | Summary |
|---|---|
| [syncSourceLine](sync-source-line.md) | `fun syncSourceLine(fieldValue: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, fieldName: `[`EventLineAccessor`](../../../myaa.subkt.ass/-event-line-accessor/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`> = EventLineAccessor.EFFECT): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Specifies a line in the included file (e.g. a song file) to serve as a reference for shifting all lines in this file. All lines will be shifted so that the start time of the specified source line matches the time specified by [syncTargetTime](sync-target-time.md) or the start time of the line specified by [syncTargetLine](sync-target-line.md). |
| [syncTargetLine](sync-target-line.md) | `fun syncTargetLine(fieldValue: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, fieldName: `[`EventLineAccessor`](../../../myaa.subkt.ass/-event-line-accessor/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`> = EventLineAccessor.EFFECT): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Specifies a target line which the line specified by [syncSourceLine](sync-source-line.md) should be shifted to. The target line may be present anywhere in the merged file. |
