[subkt](../../../index.md) / [myaa.subkt.tasks](../../index.md) / [Mux](../index.md) / [MuxFile](index.md) / [subtitles](./subtitles.md)

# subtitles

`fun subtitles(vararg trackIds: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, action: `[`Mux.Track`](../-track/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.11/src/main/kotlin/myaa/subkt/tasks/muxtask.kt#L431)

Configures the subtitle tracks in this file.

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

`trackIds` - The subtitle tracks to run [action](subtitles.md#myaa.subkt.tasks.Mux.MuxFile$subtitles(kotlin.IntArray, kotlin.Function1((myaa.subkt.tasks.Mux.Track, kotlin.Unit)))/action) against.
0 refers to the first subtitle track, 1 to the second subtitle track, and so on.

`action` - A closure operating on a [Track](../-track/index.md) instance.