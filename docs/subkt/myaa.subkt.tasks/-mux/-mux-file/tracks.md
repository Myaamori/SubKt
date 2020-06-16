[subkt](../../../index.md) / [myaa.subkt.tasks](../../index.md) / [Mux](../index.md) / [MuxFile](index.md) / [tracks](./tracks.md)

# tracks

`val tracks: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Mux.Track`](../-track/index.md)`>`

The tracks present in this file.

**Getter**

The tracks present in this file.

`fun tracks(action: `[`Mux.Track`](../-track/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Configures the tracks in this file.

``` kotlin
from("video.mkv") {
    tracks {
        include(track.type == TrackType.VIDEO || track.type == TrackType.AUDIO)
        lang("jpn")
        if (track.type == TrackType.VIDEO) {
            name("Video")
        } else {
            name("Audio")
        }
    }
}

from("subtitles.ass") {
    tracks {
        lang("eng")
        default(true)
        name("English")
    }
}
```

### Parameters

`action` - A closure operating on a [Track](../-track/index.md) instance.