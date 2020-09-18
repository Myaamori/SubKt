[subkt](../../../index.md) / [myaa.subkt.tasks](../../index.md) / [Mux](../index.md) / [MuxFile](index.md) / [tracks](./tracks.md)

# tracks

`val tracks: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Mux.Track`](../-track/index.md)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.9/src/main/kotlin/myaa/subkt/tasks/muxtask.kt#L356)

The tracks present in this file.

**Getter**

The tracks present in this file.

`fun tracks(vararg trackIds: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, trackType: `[`Mux.TrackType`](../-track-type/index.md)`? = null, action: `[`Mux.Track`](../-track/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.9/src/main/kotlin/myaa/subkt/tasks/muxtask.kt#L392)

Configures the tracks in this file.

``` kotlin
from("video.mkv") {
    tracks {
        include(track.type == TrackType.VIDEO || track.type == TrackType.AUDIO)
    }

    video {
        name("Video")
    }

    audio(0) {
        name("English Audio")
        lang("eng")
    }

    audio(1) {
        name("Japanese Audio")
        lang("jpn")
    }
}

from("subtitles.ass") {
    subtitles {
        lang("eng")
        default(true)
        name("English")
    }
}
```

### Parameters

`trackIds` - The IDs of the tracks to run [action](tracks.md#myaa.subkt.tasks.Mux.MuxFile$tracks(kotlin.IntArray, myaa.subkt.tasks.Mux.TrackType, kotlin.Function1((myaa.subkt.tasks.Mux.Track, kotlin.Unit)))/action) against, starting at 0.
If [trackType](tracks.md#myaa.subkt.tasks.Mux.MuxFile$tracks(kotlin.IntArray, myaa.subkt.tasks.Mux.TrackType, kotlin.Function1((myaa.subkt.tasks.Mux.Track, kotlin.Unit)))/trackType) is specified, 0 will refer to the first track of that type.

`trackType` - The type of the tracks to run [action](tracks.md#myaa.subkt.tasks.Mux.MuxFile$tracks(kotlin.IntArray, myaa.subkt.tasks.Mux.TrackType, kotlin.Function1((myaa.subkt.tasks.Mux.Track, kotlin.Unit)))/action) against.

`action` - A closure operating on a [Track](../-track/index.md) instance.