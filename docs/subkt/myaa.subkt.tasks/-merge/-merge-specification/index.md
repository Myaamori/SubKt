[subkt](../../../index.md) / [myaa.subkt.tasks](../../index.md) / [Merge](../index.md) / [MergeSpecification](./index.md)

# MergeSpecification

`inner class MergeSpecification` [(source)](https://github.com/Myaamori/SubKt/blob/master/src/main/kotlin/myaa/subkt/tasks/asstasks.kt#L85)

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
| [syncField](sync-field.md) | `val syncField: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`EventLineAccessor`](../../../myaa.subkt.ass/-event-line-accessor/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>`<br>An [EventLineAccessor](../../../myaa.subkt.ass/-event-line-accessor/index.md) specifying what field to match the value specified by [syncLine](sync-line.md) to in order to identify the sync line. Defaults to the effect field. |
| [syncLine](sync-line.md) | `val syncLine: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>The value that identifies the sync line used by [syncTo](sync-to.md). The sync line must have the specified value in the field specified by [syncField](sync-field.md). Defaults to `sync`. |
| [syncTo](sync-to.md) | `val syncTo: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Duration`](https://docs.oracle.com/javase/9/docs/api/java/time/Duration.html)`>`<br>Shifts the lines of the source file so that the start time of the sync line, as specified by [syncLine](sync-line.md) and [syncField](sync-field.md), lines up with the specified [Duration](https://docs.oracle.com/javase/9/docs/api/java/time/Duration.html). |
