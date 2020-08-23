[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Subs](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`Subs(project: `[`Project`](https://docs.gradle.org/current/javadoc/org/gradle/api/Project.html)`)`

Central object that keeps track of episodes, batches, tasks and user-loaded properties.
For tasks to be generated correctly, [episodes](episodes.md) and optionally [batches](batches.md) should be set.
Set [release](release.md) if you wish to be able to differentiate between different releases
when looking up properties.

Any tasks created directly in the context of [Subs](index.md) will generate one task per episode
specified in [episodes](episodes.md).
Batch tasks can be generated from the context of [batchtasks](batchtasks.md).
Tasks created in the context of [alltasks](alltasks.md) will generate tasks for all episodes
as well as all batches.

``` kotlin
subs {
    readProperties("sub.properties")
    // read the release argument specified as -Prelease=value when invoking Gradle,
    // or default to "TV" if not specified.
    release(arg("release") ?: "TV")
    episodes("01", "02", "03", "04", "05", "06", "07", "08", "09")
    batches(
            "vol1" to listOf("01", "02", "03"),
            "vol2" to listOf("04", "05", "06"),
            "vol3" to listOf("07", "08", "09")
    )

    merge {
        from("$episode/file1.ass", "$episode/file2.ass", "$episode/file3.ass")
    }

    mux {
        from("$episode/video.mkv")
        from(merge.item())
        out("$episode/muxed.mkv")
    }

    // per-episode torrents
    torrent {
        from(mux.item())
        out("$episode/$episode.torrent")
    }

    // per-batch torrents
    batchtasks {
        torrent {
            from(mux.batchItems())
            into("My Show - $batch")
            out("$batch/$batch.torrent")
        }
    }

    // alternatively, configure all tasks in one go
    alltasks {
        torrent {
            from(mux.batchItems())
            if (isBatch) {
                into("My Show - $batch")
            }
            out("$entry/$entry.torrent")
        }
    }
}
```

