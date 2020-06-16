[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Mux](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`Mux()`

Task to mux a set of files into a single Matroska container using mkvmerge.

A predefined task instance can be accessed through [Subs.mux](../mux.md).

``` kotlin
mux {
    title("My Show - 01")
    forceCRC("DEADBEEF")

    from("video.mkv") {
        includeChapters(false)

        tracks {
            include(track.type == TrackType.VIDEO)
            name("Video")
            default(true)
        }

        attachments {
            include(false)
        }
    }

    from("audio.aac") {
        tracks {
            name("Audio")
            lang("jpn")
            default(true)
        }
    }

    from("commentary.aac") {
        tracks {
            name("Commentary")
            lang("jpn")
            delay(1000)
        }
    }

    from("subtitles.ass") {
        tracks {
            name("English")
            lang("eng")
            default(true)
        }
    }

    chapters("chapters.txt") {
        lang("eng")
    }

    attach("fonts") {
        includeExtensions("ttf", "otf")
    }

    out("My Show - 01.mkv")
}
```

