[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Merge](./index.md)

# Merge

`open class Merge : `[`ASSTask`](../-a-s-s-task/index.md) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.7/src/main/kotlin/myaa/subkt/tasks/asstasks.kt#L74)

Task to merge multiple ASS files into one. A predefined task instance can be
accessed through [Subs.merge](../merge.md).

``` kotlin
merge {
    from("dialogue.ass") {
        incrementLayer(10)
    }
    from("OP.ass") {
        syncField(EventLineAccessor.ACTOR)
        syncTo("0:10:24.66".assTime)
    }
    from(glob("typesetting-*.ass"))
    onScriptInfoConflict(ErrorMode.FAIL)
    namespaceStyles(true)
}
```

### Types

| Name | Summary |
|---|---|
| [FileSpecPair](-file-spec-pair/index.md) | `data class FileSpecPair`<br>Represents a pair of files and corresponding merge specification. |
| [MergeSpecification](-merge-specification/index.md) | `inner class MergeSpecification`<br>Defines how the files associated with this specification should be merged. |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Merge()`<br>Task to merge multiple ASS files into one. A predefined task instance can be accessed through [Subs.merge](../merge.md). |

### Properties

| Name | Summary |
|---|---|
| [conflictingScriptInfo](conflicting-script-info.md) | `val conflictingScriptInfo: `[`ListProperty`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/ListProperty.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!>!`<br>Fields for which differing values are considered a conflict. Defaults to PlayResX, PlayResY, YCbCr Matrix, ScaledBorderAndShadow and WrapStyle. |
| [namespaceStyles](namespace-styles.md) | `val namespaceStyles: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>`<br>If true, prepends every style name with an identifier unique to each file, to make style conflicts impossible. |
| [onScriptInfoConflict](on-script-info-conflict.md) | `val onScriptInfoConflict: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`ErrorMode`](../-error-mode/index.md)`>`<br>What to do when encountering conflicting values for fields specified in [conflictingScriptInfo](conflicting-script-info.md). |
| [onStyleConflict](on-style-conflict.md) | `val onStyleConflict: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`ErrorMode`](../-error-mode/index.md)`>`<br>What to do when encountering styles with identical names but conflicting definitions. |
| [scriptInfo](script-info.md) | `val scriptInfo: `[`ScriptInfoSection`](../../myaa.subkt.ass/-script-info-section/index.md)<br>A [ScriptInfoSection](../../myaa.subkt.ass/-script-info-section/index.md) instance for overriding the script info read from the merged files. A [scriptInfo](script-info.md) method is available for easier modification. |
| [sources](sources.md) | `val sources: `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Merge.FileSpecPair`](-file-spec-pair/index.md)`>>`<br>The files to be merged, added via [from](from.md) or [fromMergeTemplate](from-merge-template.md). |

### Inherited Properties

| Name | Summary |
|---|---|
| [includeExtraData](../-a-s-s-task/include-extra-data.md) | `val includeExtraData: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`!>!`<br>Whether to include the Aegisub Extradata section. |
| [includeProjectGarbage](../-a-s-s-task/include-project-garbage.md) | `val includeProjectGarbage: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`!>!`<br>Whether to include the Aegisub Project Garbage section. |
| [out](../-a-s-s-task/out.md) | `val out: `[`ConfigurableFileCollection`](https://docs.gradle.org/current/javadoc/org/gradle/api/file/ConfigurableFileCollection.html)<br>The location to save the ASS file. Defaults to an automatically generated file in the build directory. |

### Functions

| Name | Summary |
|---|---|
| [buildAss](build-ass.md) | `open fun buildAss(): `[`ASSFile`](../../myaa.subkt.ass/-a-s-s-file/index.md)<br>Constructs the ASS file to save. Must be implemented by subtypes. |
| [from](from.md) | `fun from(vararg files: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, action: `[`Merge.MergeSpecification`](-merge-specification/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` = {}): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Adds files to merge. |
| [fromMergeTemplate](from-merge-template.md) | `fun fromMergeTemplate(file: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Merges files as defined in a template file following [Merge Scripts](https://github.com/TypesettingTools/Myaamori-Aegisub-Scripts/#merge-scripts) syntax. |
| [scriptInfo](script-info.md) | `fun scriptInfo(action: `[`ScriptInfoSection`](../../myaa.subkt.ass/-script-info-section/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Allows you to override values in the Script Info section as needed. |

### Inherited Functions

| Name | Summary |
|---|---|
| [run](../-a-s-s-task/run.md) | `open fun run(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

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
