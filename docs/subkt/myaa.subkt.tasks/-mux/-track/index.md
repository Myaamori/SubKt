[subkt](../../../index.md) / [myaa.subkt.tasks](../../index.md) / [Mux](../index.md) / [Track](./index.md)

# Track

`inner class Track : `[`Filterable`](../../-filterable/index.md) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.11/src/main/kotlin/myaa/subkt/tasks/muxtask.kt#L156)

Represents a single track read from the source file.

### Types

| Name | Summary |
|---|---|
| [TrackInfo](-track-info/index.md) | `inner class TrackInfo`<br>Stores information extracted from the source track. |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Track(sourceTrack: `[`MkvTrack`](../../../myaa.subkt.tasks.utils/-mkv-track/index.md)`)`<br>Represents a single track read from the source file. |

### Properties

| Name | Summary |
|---|---|
| [aspectRatio](aspect-ratio.md) | `val aspectRatio: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`>`<br>Sets the aspect ratio of this track, specified as a floating point value. |
| [compression](compression.md) | `val compression: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Mux.CompressionType`](../-compression-type/index.md)`>`<br>Sets the compression type for this track. Must be one of [CompressionType.ZLIB](../-compression-type/-z-l-i-b.md), [CompressionType.MPEG4P2](../-compression-type/-m-p-e-g4-p2.md) or [CompressionType.NONE](../-compression-type/-n-o-n-e.md). |
| [default](default.md) | `val default: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>`<br>If true, set this track to be a default track. |
| [defaultDuration](default-duration.md) | `val defaultDuration: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Mux.TrackDuration`](../-track-duration/index.md)`>`<br>Forces the default duration or FPS for the track. Value must be a [TrackDuration](../-track-duration/index.md) instance, e.g.: |
| [delay](delay.md) | `val delay: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`>`<br>Sets the delay of the track in milliseconds. |
| [displayDimensions](display-dimensions.md) | `val displayDimensions: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Mux.Dimensions`](../-dimensions/index.md)`>`<br>Sets the display dimensions of this track, specified as a [Dimensions](../-dimensions/index.md) object, e.g.: |
| [forced](forced.md) | `val forced: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>`<br>If true, set this track to be a forced track. |
| [id](id.md) | `val id: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)<br>The ID of this track. |
| [include](include.md) | `val include: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>`<br>Whether to include this track in the output file. Defaults to true. |
| [lang](lang.md) | `val lang: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>Sets the language of this track. |
| [name](name.md) | `val name: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>Sets the name of this track. |
| [sourceTrack](source-track.md) | `val sourceTrack: `[`MkvTrack`](../../../myaa.subkt.tasks.utils/-mkv-track/index.md)<br>Raw track info from mkvmerge. |
| [stretch](stretch.md) | `val stretch: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`>`<br>Value to multiply the timestamps by. E.g. a value of 2 makes the track twice as long. |
| [sync](sync.md) | `val sync: `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [track](track.md) | `val track: `[`Mux.Track.TrackInfo`](-track-info/index.md)<br>A [TrackInfo](-track-info/index.md) instance providing information about the source track. |
| [trackOrder](track-order.md) | `val trackOrder: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>`<br>The order of this track in the muxed file. Tracks will be sorted by the values specified for this property. |

### Functions

| Name | Summary |
|---|---|
| [toString](to-string.md) | `fun toString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
