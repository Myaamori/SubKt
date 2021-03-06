[subkt](../../../index.md) / [myaa.subkt.tasks](../../index.md) / [Mux](../index.md) / [MuxFile](index.md) / [video](./video.md)

# video

`fun video(vararg trackIds: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, action: `[`Mux.Track`](../-track/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` = {}): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Mux.Track`](../-track/index.md)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.19/src/main/kotlin/myaa/subkt/tasks/muxtask.kt#L416)

Configures the video tracks in this file.

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

`trackIds` - The video tracks to run [action](video.md#myaa.subkt.tasks.Mux.MuxFile$video(kotlin.IntArray, kotlin.Function1((myaa.subkt.tasks.Mux.Track, kotlin.Unit)))/action) against.
0 refers to the first video track, 1 to the second video track, and so on.

`action` - A closure operating on a [Track](../-track/index.md) instance.