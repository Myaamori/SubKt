[subkt](../../../index.md) / [myaa.subkt.tasks](../../index.md) / [Mux](../index.md) / [MuxFile](index.md) / [audio](./audio.md)

# audio

`fun audio(vararg trackIds: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, action: `[`Mux.Track`](../-track/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.13/src/main/kotlin/myaa/subkt/tasks/muxtask.kt#L427)

Configures the audio tracks in this file.

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

`trackIds` - The audio tracks to run [action](audio.md#myaa.subkt.tasks.Mux.MuxFile$audio(kotlin.IntArray, kotlin.Function1((myaa.subkt.tasks.Mux.Track, kotlin.Unit)))/action) against.
0 refers to the first audio track, 1 to the second audio track, and so on.

`action` - A closure operating on a [Track](../-track/index.md) instance.