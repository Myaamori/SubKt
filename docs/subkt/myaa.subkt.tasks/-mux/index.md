[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Mux](./index.md)

# Mux

`open class Mux : `[`PropertyTask`](../-property-task/index.md) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.9/src/main/kotlin/myaa/subkt/tasks/muxtask.kt#L51)

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

### Types

| Name | Summary |
|---|---|
| [Attachment](-attachment/index.md) | `inner class Attachment : `[`Filterable`](../-filterable/index.md)<br>Represents an attachment present in a [MuxFile](-mux-file/index.md). |
| [Chapter](-chapter/index.md) | `inner class Chapter`<br>Represents a chapter file to mux, added using [chaptersProperty](chapters-property.md). |
| [CompressionType](-compression-type/index.md) | `enum class CompressionType`<br>The type of compression to use, for use with [Track.compression](-track/compression.md). |
| [ConfigurableAttachment](-configurable-attachment/index.md) | `inner class ConfigurableAttachment : `[`PatternFilterable`](https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/util/PatternFilterable.html)`, `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)`<`[`File`](https://docs.oracle.com/javase/9/docs/api/java/io/File.html)`>`<br>Represents a set of files to attach, added using [attach](attach.md). |
| [Dimensions](-dimensions/index.md) | `data class Dimensions : `[`Serializable`](https://docs.oracle.com/javase/9/docs/api/java/io/Serializable.html)<br>Represents the dimensions of a track in pixels. |
| [MuxFile](-mux-file/index.md) | `inner class MuxFile`<br>Represents a file to mux, added using [from](from.md). |
| [Track](-track/index.md) | `inner class Track : `[`Filterable`](../-filterable/index.md)<br>Represents a single track read from the source file. |
| [TrackDuration](-track-duration/index.md) | `data class TrackDuration : `[`Serializable`](https://docs.oracle.com/javase/9/docs/api/java/io/Serializable.html)<br>Represents a duration for use with [Track.defaultDuration](-track/default-duration.md). |
| [TrackType](-track-type/index.md) | `enum class TrackType`<br>The type of a track, available through [Track.TrackInfo.type](-track/-track-info/type.md). |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Mux()`<br>Task to mux a set of files into a single Matroska container using mkvmerge. |

### Properties

| Name | Summary |
|---|---|
| [attachmentsProperty](attachments-property.md) | `val attachmentsProperty: `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Mux.ConfigurableAttachment`](-configurable-attachment/index.md)`>>`<br>The files to attach, added via [attach](attach.md). |
| [chaptersProperty](chapters-property.md) | `val chaptersProperty: `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`Mux.Chapter`](-chapter/index.md)`>`<br>The chapters of the file, added via [chapters](chapters.md). |
| [crc](crc.md) | `var crc: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The CRC32 hash of the output file. Only available after the task has finished. |
| [defaultLanguage](default-language.md) | `val defaultLanguage: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>The default language used for tracks which don't have a language specified. If not set, mkvmerge will default to `und`. |
| [deterministic](deterministic.md) | `val deterministic: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>`<br>If true, a fixed value will be used as the RNG seed for generating UIDs, and mkvmerge will not write the current date and time to the output file. This means that for the same input files, flags and mkvmerge version, the output file will always be byte identical. Thus, even if you have to rerun tasks for whatever reason, you know that the same CRC32 will be generated unless the input files have changed. |
| [deterministicSeed](deterministic-seed.md) | `val deterministicSeed: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`>`<br>The seed to use for `--deterministic` -- see [deterministic](deterministic.md). If not set, a hash based on the output filename will be used. |
| [filesProperty](files-property.md) | `val filesProperty: `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Mux.MuxFile`](-mux-file/index.md)`>>`<br>The files to mux, added via [from](from.md). |
| [forceCRC](force-c-r-c.md) | `val forceCRC: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>Forces the CRC32 hash of the file to be equal to the set value. Must be a 8 character long hex string, containing only digits and letters in the range A-F. |
| [globalOptions](global-options.md) | `val globalOptions: `[`ListProperty`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/ListProperty.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>Any additional global mkvmerge options you wish to include. |
| [inputFiles](input-files.md) | `val inputFiles: `[`FileCollection`](https://docs.gradle.org/current/javadoc/org/gradle/api/file/FileCollection.html)<br>All files added via [from](from.md), [attach](attach.md) or [chapters](chapters.md). |
| [mimeTypes](mime-types.md) | `val mimeTypes: `[`MutableMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>Overrides the MIME types autodetected by mkvmerge for files attached via [attach](attach.md), on a file extension basis. E.g. to force all files ending with `.txt` to have MIME type `text/html`, you can run: |
| [mkvmerge](mkvmerge.md) | `val mkvmerge: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>Path to the mkvmerge command. Defaults to mkvmerge, and assumes the command is present in your PATH. |
| [onFaux](on-faux.md) | `val onFaux: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`ErrorMode`](../-error-mode/index.md)`>`<br>If [verifyFonts](../../myaa.subkt.tasks.utils/verify-fonts.md) is true, controls what to do if an instance of faux bold or faux italic is encountered. Defaults to [ErrorMode.WARN](../-error-mode/-w-a-r-n.md). |
| [onMissingFonts](on-missing-fonts.md) | `val onMissingFonts: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`ErrorMode`](../-error-mode/index.md)`>`<br>If [verifyFonts](../../myaa.subkt.tasks.utils/verify-fonts.md) is true, controls what to do if a missing font is encountered. Defaults to [ErrorMode.FAIL](../-error-mode/-f-a-i-l.md). |
| [onMissingGlyphs](on-missing-glyphs.md) | `val onMissingGlyphs: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`ErrorMode`](../-error-mode/index.md)`>`<br>If [verifyFonts](../../myaa.subkt.tasks.utils/verify-fonts.md) is true, controls what to do if missing glyphs are encountered. Defaults to [ErrorMode.FAIL](../-error-mode/-f-a-i-l.md). |
| [onStyleMismatch](on-style-mismatch.md) | `val onStyleMismatch: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`ErrorMode`](../-error-mode/index.md)`>`<br>If [verifyFonts](../../myaa.subkt.tasks.utils/verify-fonts.md) is true, controls what to do if the matched font differs from the requested style (e.g. non-italic was requested, but only italic found). Defaults to [ErrorMode.WARN](../-error-mode/-w-a-r-n.md). |
| [out](out.md) | `val out: `[`ConfigurableFileCollection`](https://docs.gradle.org/current/javadoc/org/gradle/api/file/ConfigurableFileCollection.html)<br>The location to save the MKV file. Defaults to an automatically generated file in the build directory. |
| [outFile](out-file.md) | `val outFile: `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`File`](https://docs.oracle.com/javase/9/docs/api/java/io/File.html)`>`<br>Read-only alias of [out](out.md). |
| [title](title.md) | `val title: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>The title of the file. Will generally show up in e.g. the window title of the video player. |
| [verifyFonts](verify-fonts.md) | `val verifyFonts: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>`<br>If true, will warn about font issues in included subtitle tracks. You can configure the error reporting using [onFaux](on-faux.md), [onStyleMismatch](on-style-mismatch.md), [onMissingGlyphs](on-missing-glyphs.md) and [onMissingFonts](on-missing-fonts.md). Defaults to true. |
| [webm](webm.md) | `val webm: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>`<br>Whether to output a WebM compliant file. Defaults to false. |

### Inherited Properties

| Name | Summary |
|---|---|
| [propertyFile](../-property-task/property-file.md) | `val propertyFile: `[`File`](https://docs.oracle.com/javase/9/docs/api/java/io/File.html) |

### Functions

| Name | Summary |
|---|---|
| [attach](attach.md) | `fun attach(dir: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, action: `[`Mux.ConfigurableAttachment`](-configurable-attachment/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` = {}): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`Mux.ConfigurableAttachment`](-configurable-attachment/index.md)`>`<br>Attach one or more files to the output file. |
| [chapters](chapters.md) | `fun chapters(file: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, action: `[`Mux.Chapter`](-chapter/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` = {}): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`Mux.Chapter`](-chapter/index.md)`>`<br>Adds a chapter file. |
| [from](from.md) | `fun from(vararg files: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, action: `[`Mux.MuxFile`](-mux-file/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` = {}): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Mux.MuxFile`](-mux-file/index.md)`>>`<br>Adds files to mux. |
| [run](run.md) | `open fun run(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inherited Functions

| Name | Summary |
|---|---|
| [doTask](../-property-task/do-task.md) | `fun doTask(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Extension Properties

| Name | Summary |
|---|---|
| [batch](../org.gradle.api.-task/batch.md) | `val `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.batch: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The same as [entry](../org.gradle.api.-task/entry.md) if this is a batch task; error otherwise. |
| [currentTask](../org.gradle.api.-task/current-task.md) | `val `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.currentTask: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The name of this task. |
| [entry](../org.gradle.api.-task/entry.md) | `val `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The entry (batch or episode) this task corresponds to. |
| [episode](../org.gradle.api.-task/episode.md) | `val `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.episode: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The same as [entry](../org.gradle.api.-task/entry.md) if this is an episode task; error otherwise. |
| [episodes](../org.gradle.api.-task/episodes.md) | `val `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.episodes: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>The episodes this task corresponds to. A single-item list containing [episode](../org.gradle.api.-task/episode.md) if this is an episode task; a list of the episodes for the batch given by [batch](../org.gradle.api.-task/batch.md) otherwise. |
| [isBatch](../org.gradle.api.-task/is-batch.md) | `val `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.isBatch: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>True if this task is a batch task. |
| [release](../org.gradle.api.-task/release.md) | `val `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.release: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The release this task was generated for. |
| [taskGroup](../task-group.md) | `val <T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> `[`T`](../task-group.md#T)`.taskGroup: `[`TaskGroup`](../-task-group/index.md)`<`[`T`](../task-group.md#T)`>`<br>The [TaskGroup](../-task-group/index.md) instance this task belongs to. |

### Extension Functions

| Name | Summary |
|---|---|
| [defaultProperty](../org.gradle.api.-task/default-property.md) | `fun <T> `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.defaultProperty(default: `[`T`](../org.gradle.api.-task/default-property.md#T)`): `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`T`](../org.gradle.api.-task/default-property.md#T)`>`<br>Returns a [Property](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html) with a default value set. |
| [evaluate](../org.gradle.api.-task/evaluate.md) | `fun `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.evaluate(expression: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>!>!`<br>Evaluates a string using [Velocity](https://velocity.apache.org/engine/2.2/user-guide.html), splits it on `|`, and processes it with [glob](../org.gradle.api.-project/glob.md). |
| [evaluateTemplate](../org.gradle.api.-task/evaluate-template.md) | `fun `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.evaluateTemplate(expression: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!>!`<br>Like [evaluate](../org.gradle.api.-task/evaluate.md) but only processes the template syntax, without globbing. |
| [get](../org.gradle.api.-task/get.md) | `fun `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.get(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!>!`<br>Searches for the given property in the [Subs](../-subs/index.md) object's [SubProperties](../-sub-properties/index.md) instance, evaluates its value using [evaluate](../org.gradle.api.-task/evaluate.md), and returns a single string, assuming that the resulting list contains only one element. |
| [getAs](../org.gradle.api.-task/get-as.md) | `fun <T> `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.getAs(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`T`](../org.gradle.api.-task/get-as.md#T)`>!`<br>Searches for the given property in the [Subs](../-subs/index.md) object's [SubProperties](../-sub-properties/index.md) instance, evaluates its value using [evaluate](../org.gradle.api.-task/evaluate.md), and returns a single string, cast to the given type using [String.asType](../kotlin.-string/as-type.md), assuming that the resulting list contains only one element. |
| [getFile](../org.gradle.api.-task/get-file.md) | `fun `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.getFile(filename: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!>!`<br>Reads the specified file and processes it using [Velocity](https://velocity.apache.org/engine/2.2/user-guide.html). |
| [getList](../org.gradle.api.-task/get-list.md) | `fun `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.getList(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>`<br>Searches for the given property in the [Subs](../-subs/index.md) object's [SubProperties](../-sub-properties/index.md) instance, and evaluates its value using [evaluate](../org.gradle.api.-task/evaluate.md). |
| [getListAs](../org.gradle.api.-task/get-list-as.md) | `fun <T> `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.getListAs(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`T`](../org.gradle.api.-task/get-list-as.md#T)`>!>!`<br>Searches for the given property in the [Subs](../-subs/index.md) object's [SubProperties](../-sub-properties/index.md) instance, evaluates its value using [evaluate](../org.gradle.api.-task/evaluate.md), and casts the list elements to the given type using [String.asType](../kotlin.-string/as-type.md). |
| [getRaw](../org.gradle.api.-task/get-raw.md) | `fun `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.getRaw(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Searches for the given property in the [Subs](../-subs/index.md) object's [SubProperties](../-sub-properties/index.md) instance, and returns the raw string. Raises an error if not found. |
| [getRawMaybe](../org.gradle.api.-task/get-raw-maybe.md) | `fun `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.getRawMaybe(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>Searches for the given property in the [Subs](../-subs/index.md) object's [SubProperties](../-sub-properties/index.md) instance, and returns the raw string, possibly null. |
| [outputFile](../org.gradle.api.-task/output-file.md) | `fun `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.outputFile(extension: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`ConfigurableFileCollection`](https://docs.gradle.org/current/javadoc/org/gradle/api/file/ConfigurableFileCollection.html)<br>Returns a [ConfigurableFileCollection](https://docs.gradle.org/current/javadoc/org/gradle/api/file/ConfigurableFileCollection.html) containing a single file `taskName.extension` located in the build directory. |
| [propertyExists](../org.gradle.api.-task/property-exists.md) | `fun `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.propertyExists(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Returns true if the given property exists in the [Subs](../-subs/index.md) object's [SubProperties](../-sub-properties/index.md) instance for the given context. |
