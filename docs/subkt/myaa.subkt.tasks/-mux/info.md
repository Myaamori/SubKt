[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Mux](index.md) / [info](./info.md)

# info

`var info: `[`MutableMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.19/src/main/kotlin/myaa/subkt/tasks/muxtask.kt#L706)

Information about the output file as returned by `mkvinfo -J`.
See the [mkvmerge identification output schema](https://mkvtoolnix.download/doc/mkvmerge-identification-output-schema-v12.json).
Additionally, the map contains the keys `video_tracks`, `audio_tracks` and `subtitles_tracks`
which are lists of tracks of the respective types.

Example:

```
res=$mux.info["video_tracks"][0]["properties"]["display_dimensions"].split("x")[1]
acodec=$mux.info["audio_tracks"][0]["properties"]["codec_id"].substring(2)
filename=$title - $episode (${source} ${res}p ${acodec}) [${mux.crc}].mkv
```

**Getter**

Information about the output file as returned by `mkvinfo -J`.
See the [mkvmerge identification output schema](https://mkvtoolnix.download/doc/mkvmerge-identification-output-schema-v12.json).
Additionally, the map contains the keys `video_tracks`, `audio_tracks` and `subtitles_tracks`
which are lists of tracks of the respective types.

Example:

```
res=$mux.info["video_tracks"][0]["properties"]["display_dimensions"].split("x")[1]
acodec=$mux.info["audio_tracks"][0]["properties"]["codec_id"].substring(2)
filename=$title - $episode (${source} ${res}p ${acodec}) [${mux.crc}].mkv
```

