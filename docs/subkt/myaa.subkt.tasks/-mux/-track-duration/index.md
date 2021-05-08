[subkt](../../../index.md) / [myaa.subkt.tasks](../../index.md) / [Mux](../index.md) / [TrackDuration](./index.md)

# TrackDuration

`data class TrackDuration : `[`Serializable`](https://docs.oracle.com/javase/9/docs/api/java/io/Serializable.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.19/src/main/kotlin/myaa/subkt/tasks/muxtask.kt#L145)

Represents a duration for use with [Track.defaultDuration](../-track/default-duration.md).

### Types

| Name | Summary |
|---|---|
| [DurationUnit](-duration-unit/index.md) | `enum class DurationUnit` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `TrackDuration(duration: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, durationUnit: `[`Mux.TrackDuration.DurationUnit`](-duration-unit/index.md)`)`<br>Represents a duration for use with [Track.defaultDuration](../-track/default-duration.md). |

### Properties

| Name | Summary |
|---|---|
| [duration](duration.md) | `val duration: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [durationUnit](duration-unit.md) | `val durationUnit: `[`Mux.TrackDuration.DurationUnit`](-duration-unit/index.md) |

### Functions

| Name | Summary |
|---|---|
| [toString](to-string.md) | `fun toString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
