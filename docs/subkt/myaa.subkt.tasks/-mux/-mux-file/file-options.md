[subkt](../../../index.md) / [myaa.subkt.tasks](../../index.md) / [Mux](../index.md) / [MuxFile](index.md) / [fileOptions](./file-options.md)

# fileOptions

`val fileOptions: `[`ListProperty`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/ListProperty.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.10/src/main/kotlin/myaa/subkt/tasks/muxtask.kt#L381)

Any additional file-specific mkvmerge options you wish to include.

If you wish to include track-specific options, you must manually prepend
the option value with the track ID. This can be done by accessing the
[Track.id](../-track/id.md) value of a track.

``` kotlin
from("video.mkv") {
    fileOptions("--no-track-tags")

    tracks {
        fileOptions("--cues", "$id:all")
    }
}
```

**Getter**

Any additional file-specific mkvmerge options you wish to include.

If you wish to include track-specific options, you must manually prepend
the option value with the track ID. This can be done by accessing the
[Track.id](../-track/id.md) value of a track.

``` kotlin
from("video.mkv") {
    fileOptions("--no-track-tags")

    tracks {
        fileOptions("--cues", "$id:all")
    }
}
```

