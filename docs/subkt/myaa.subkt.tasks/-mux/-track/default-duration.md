[subkt](../../../index.md) / [myaa.subkt.tasks](../../index.md) / [Mux](../index.md) / [Track](index.md) / [defaultDuration](./default-duration.md)

# defaultDuration

`val defaultDuration: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Mux.TrackDuration`](../-track-duration/index.md)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.7/src/main/kotlin/myaa/subkt/tasks/muxtask.kt#L309)

Forces the default duration or FPS for the track. Value must be a
[TrackDuration](../-track-duration/index.md) instance, e.g.:

```
defaultDuration(Duration(24, TrackDuration.DurationUnit.FPS))
```

Corresponds to the `--default-duration` mkvmerge flag.

**Getter**

Forces the default duration or FPS for the track. Value must be a
[TrackDuration](../-track-duration/index.md) instance, e.g.:

```
defaultDuration(Duration(24, TrackDuration.DurationUnit.FPS))
```

Corresponds to the `--default-duration` mkvmerge flag.

