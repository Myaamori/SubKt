[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Subs](./index.md)

# Subs

`open class Subs : `[`ItemGroupContext`](../-item-group-context/index.md) [(source)](https://github.com/Myaamori/SubKt/blob/master/src/main/kotlin/myaa/subkt/tasks/plugin.kt#L311)

Central object that keeps track of episodes, batches, tasks and user-loaded properties.
For tasks to be generated correctly, [episodes](episodes.md) and optionally [batches](batches.md) should be set.
Set [release](release.md) if you wish to be able to differentiate between different releases
when looking up properties.

Any tasks created directly in the context of [Subs](./index.md) will generate one task per episode
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
            from(mux.items())
            into("My Show - $batch")
            out("$batch/$batch.torrent")
        }
    }

    // alternatively, configure all tasks in one go
    alltasks {
        torrent {
            from(mux.items())
            if (isBatch) {
                into("My Show - $batch")
            }
            out("$entry/$entry.torrent")
        }
    }
}
```

### Types

| Name | Summary |
|---|---|
| [SubContext](-sub-context/index.md) | `inner class SubContext : `[`BaseContext`](../-base-context/index.md)<br>[AbstractContext](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html) implementation for getting properties in a [Subs](./index.md) context. |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Subs(project: `[`Project`](https://docs.gradle.org/current/javadoc/org/gradle/api/Project.html)`)`<br>Central object that keeps track of episodes, batches, tasks and user-loaded properties. For tasks to be generated correctly, [episodes](episodes.md) and optionally [batches](batches.md) should be set. Set [release](release.md) if you wish to be able to differentiate between different releases when looking up properties. |

### Properties

| Name | Summary |
|---|---|
| [batches](batches.md) | `val batches: `[`MapProperty`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/MapProperty.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>`<br>A map of batches to associated episodes. Must be set for per-batch tasks to be generated. |
| [entries](entries.md) | `open val entries: `[`ListProperty`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/ListProperty.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>A list of entries (episodes or batches) this context should generate tasks for. |
| [episodes](episodes.md) | `val episodes: `[`ListProperty`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/ListProperty.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>A list of all episodes. Must be set for per-episode tasks to be generated. |
| [project](project.md) | `val project: `[`Project`](https://docs.gradle.org/current/javadoc/org/gradle/api/Project.html) |
| [properties](properties.md) | `val properties: `[`SubProperties`](../-sub-properties/index.md)<br>A [SubProperties](../-sub-properties/index.md) instance for looking up properties using [get](get.md) and related functions.. |
| [release](release.md) | `val release: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!>!`<br>The current release. Defaults to "default". |
| [subs](subs.md) | `open val subs: `[`Subs`](./index.md)<br>The [Subs](./index.md) instance all tasks created in this context should be associated with. |
| [taskGroups](task-groups.md) | `open val taskGroups: `[`MutableMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`TaskGroup`](../-task-group/index.md)`<*>>`<br>The map to store [TaskGroup](../-task-group/index.md) objects created in this context. |

### Functions

| Name | Summary |
|---|---|
| [alltasks](alltasks.md) | `fun alltasks(action: `[`ItemGroupContext`](../-item-group-context/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Creates a context for generating both episode and batch tasks. |
| [arg](arg.md) | `fun arg(argName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>Reads properties defined in `gradle.properties` or supplied on the command line using `-Pproperty=value`. |
| [batchtasks](batchtasks.md) | `fun batchtasks(action: `[`ItemGroupContext`](../-item-group-context/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Creates a context for generating batch tasks. |
| [episodes](episodes.md) | `open fun episodes(entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>The episodes associated with [entry](../-item-group-context/episodes.md#myaa.subkt.tasks.ItemGroupContext$episodes(kotlin.String)/entry). |
| [evaluate](evaluate.md) | `fun evaluate(expression: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", context: `[`AbstractContext`](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html)`? = null): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>!>!`<br>Evaluates a string using [Velocity](https://velocity.apache.org/engine/2.2/user-guide.html), splits it on `|`, and processes it with [glob](../glob.md). |
| [evaluateTemplate](evaluate-template.md) | `fun evaluateTemplate(expression: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", context: `[`AbstractContext`](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html)`? = null): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!>!`<br>Like [evaluate](evaluate.md) but only processes the template syntax, without globbing. |
| [get](get.md) | `fun get(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", context: `[`AbstractContext`](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html)`? = null): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!>!`<br>Searches for the given property in the [Subs](./index.md) object's [SubProperties](../-sub-properties/index.md) instance, evaluates its value using [evaluate](evaluate.md), and returns a single string, assuming that the resulting list contains only one element. |
| [getAs](get-as.md) | `fun <T> getAs(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", context: `[`AbstractContext`](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html)`? = null): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`T`](get-as.md#T)`>!`<br>Searches for the given property in the [Subs](./index.md) object's [SubProperties](../-sub-properties/index.md) instance, evaluates its value using [evaluate](evaluate.md), and returns a single string, cast to the given type using [String.asType](../kotlin.-string/as-type.md), assuming that the resulting list contains only one element. |
| [getFile](get-file.md) | `fun getFile(filename: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", context: `[`AbstractContext`](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html)`? = null): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!>!`<br>Reads the specified file and processes it using [Velocity](https://velocity.apache.org/engine/2.2/user-guide.html). |
| [getList](get-list.md) | `fun getList(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", context: `[`AbstractContext`](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html)`? = null): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>`<br>Searches for the given property in the [Subs](./index.md) object's [SubProperties](../-sub-properties/index.md) instance, and evaluates its value using [evaluate](evaluate.md). |
| [getListAs](get-list-as.md) | `fun <T> getListAs(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", context: `[`AbstractContext`](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html)`? = null): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`T`](get-list-as.md#T)`>!>!`<br>Searches for the given property in the [Subs](./index.md) object's [SubProperties](../-sub-properties/index.md) instance, evaluates its value using [evaluate](evaluate.md), and casts the list elements to the given type using [String.asType](../kotlin.-string/as-type.md). |
| [getMap](get-map.md) | `fun getMap(keys: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, values: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>!>`<br>Constructs a map by looking up [keys](get-map.md#myaa.subkt.tasks.Subs$getMap(kotlin.String, kotlin.String)/keys) in the properties, taking its values to be the keys of the map, and then for each such key, looking up the property with the key as its entry and [values](get-map.md#myaa.subkt.tasks.Subs$getMap(kotlin.String, kotlin.String)/values) as its property name, and taking the value of that property to be the value associated with that key. |
| [getRaw](get-raw.md) | `fun getRaw(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = ""): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Searches for the given property in the [Subs](./index.md) object's [SubProperties](../-sub-properties/index.md) instance, and returns the raw string. Raises an error if not found. |
| [getRawMaybe](get-raw-maybe.md) | `fun getRawMaybe(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = ""): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>Searches for the given property in the [Subs](./index.md) object's [SubProperties](../-sub-properties/index.md) instance, and returns the raw string, possibly null. |
| [isBatch](is-batch.md) | `open fun isBatch(entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>True if [entry](../-item-group-context/is-batch.md#myaa.subkt.tasks.ItemGroupContext$isBatch(kotlin.String)/entry) corresponds to a batch entry. |
| [readProperties](read-properties.md) | `fun readProperties(vararg file: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Reads properties from one or more files as described in [SubProperties](../-sub-properties/index.md). |
| [tasks](tasks.md) | `fun tasks(episodes: `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, action: `[`ItemGroupContext`](../-item-group-context/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Creates a context for generating tasks for the specified entries. |

### Inherited Functions

| Name | Summary |
|---|---|
| [invoke](../-item-group-context/invoke.md) | `operator fun <T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> `[`TaskGroup`](../-task-group/index.md)`<`[`T`](../-item-group-context/invoke.md#T)`>.invoke(action: `[`T`](../-item-group-context/invoke.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Create and/or configure tasks of type [T](../-item-group-context/invoke.md#T) for all entries in [entries](../-item-group-context/entries.md).`operator fun <T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`.invoke(action: `[`T`](../-item-group-context/invoke.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`TaskGroup`](../-task-group/index.md)`<`[`T`](../-item-group-context/invoke.md#T)`>`<br>Creates a new task group with the name given by the string the function is invoked on, or returns the task group with the given name if it already exists, and configures a task for each entry in the current context using the given closure.`operator fun <T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<`[`T`](../-item-group-context/invoke.md#T)`>.invoke(action: `[`T`](../-item-group-context/invoke.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`TaskCreator`](../-task-creator/index.md)`<`[`T`](../-item-group-context/invoke.md#T)`>`<br>Returns a delegate that when accessed returns a task group with the same name as the property it is bound to. Also configures one task for each entry in the current context using the given closure. |
| [provider](../-item-group-context/provider.md) | `fun <T> provider(action: `[`ValueClosure`](../-value-closure/index.md)`<*>.() -> `[`T`](../-item-group-context/provider.md#T)`): `[`ProviderGroup`](../-provider-group/index.md)`<`[`T`](../-item-group-context/provider.md#T)`>`<br>Creates a [ProviderGroup](../-provider-group/index.md) that evaluates the given closure lazily, returning a [Provider](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html) when an item is requested for a given entry. The closure is evaluated by running [Provider.get](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html#get()) on the returned provider. |
| [task](../-item-group-context/task.md) | `fun <T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> task(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, klass: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<`[`T`](../-item-group-context/task.md#T)`>): `[`TaskGroup`](../-task-group/index.md)`<`[`T`](../-item-group-context/task.md#T)`>`<br>Create a new task group, or returns the task group with the given name if it already exists.`fun <T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> task(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, klass: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<`[`T`](../-item-group-context/task.md#T)`>, action: `[`T`](../-item-group-context/task.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`TaskGroup`](../-task-group/index.md)`<`[`T`](../-item-group-context/task.md#T)`>`<br>`fun <T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> task(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, action: `[`T`](../-item-group-context/task.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`TaskGroup`](../-task-group/index.md)`<`[`T`](../-item-group-context/task.md#T)`>`<br>Creates a new task group, or returns the task group with the given name if it already exists, and configures a task for each entry in the current context using the given closure.`fun <T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> task(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`TaskGroup`](../-task-group/index.md)`<`[`T`](../-item-group-context/task.md#T)`>`<br>Creates a new task group, or returns the task group with the given name if it already exists.`fun <T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> task(action: (`[`T`](../-item-group-context/task.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)? = null): `[`TaskCreator`](../-task-creator/index.md)`<`[`T`](../-item-group-context/task.md#T)`>`<br>Returns a delegate that when accessed returns a task group with the same name as the property it is bound to. Optionally configures one task for each entry in the current context using the given closure. |
| [value](../-item-group-context/value.md) | `fun <T> value(action: `[`ValueClosure`](../-value-closure/index.md)`<*>.() -> `[`T`](../-item-group-context/value.md#T)`): `[`ValueGroup`](../-value-group/index.md)`<`[`T`](../-item-group-context/value.md#T)`>`<br>Creates a [ValueGroup](../-value-group/index.md), evaluating the given closure immediately for each entry in the given context. |

### Extension Properties

| Name | Summary |
|---|---|
| [anidex](../anidex.md) | `val `[`Subs`](./index.md)`.anidex: `[`TaskGroup`](../-task-group/index.md)`<`[`Anidex`](../-anidex/index.md)`>`<br>Convenience property that upon use automatically instantiates and returns a [TaskGroup](../-task-group/index.md) of type [Anidex](../-anidex/index.md) with the name `anidex`. |
| [chapters](../chapters.md) | `val `[`Subs`](./index.md)`.chapters: `[`TaskGroup`](../-task-group/index.md)`<`[`Chapters`](../-chapters/index.md)`>`<br>Convenience property that upon use automatically instantiates and returns a [TaskGroup](../-task-group/index.md) of type [Chapters](../-chapters/index.md) with the name `chapters`. |
| [discord](../discord.md) | `val `[`Subs`](./index.md)`.discord: `[`TaskGroup`](../-task-group/index.md)`<`[`Discord`](../-discord/index.md)`>`<br>Convenience property that upon use automatically instantiates and returns a [TaskGroup](../-task-group/index.md) of type [Discord](../-discord/index.md) with the name `discord`. |
| [ftp](../ftp.md) | `val `[`Subs`](./index.md)`.ftp: `[`TaskGroup`](../-task-group/index.md)`<`[`FTP`](../-f-t-p/index.md)`>`<br>Convenience property that upon use automatically instantiates and returns a [TaskGroup](../-task-group/index.md) of type [FTP](../-f-t-p/index.md) with the name `ftp`. |
| [merge](../merge.md) | `val `[`Subs`](./index.md)`.merge: `[`TaskGroup`](../-task-group/index.md)`<`[`Merge`](../-merge/index.md)`>`<br>Convenience property that upon use automatically instantiates and returns a [TaskGroup](../-task-group/index.md) of type [Merge](../-merge/index.md) with the name `merge`. |
| [mux](../mux.md) | `val `[`Subs`](./index.md)`.mux: `[`TaskGroup`](../-task-group/index.md)`<`[`Mux`](../-mux/index.md)`>`<br>Convenience property that upon use automatically instantiates and returns a [TaskGroup](../-task-group/index.md) of type [Mux](../-mux/index.md) with the name `mux`. |
| [nyaa](../nyaa.md) | `val `[`Subs`](./index.md)`.nyaa: `[`TaskGroup`](../-task-group/index.md)`<`[`Nyaa`](../-nyaa/index.md)`>`<br>Convenience property that upon use automatically instantiates and returns a [TaskGroup](../-task-group/index.md) of type [Nyaa](../-nyaa/index.md) with the name `nyaa`. |
| [sftp](../sftp.md) | `val `[`Subs`](./index.md)`.sftp: `[`TaskGroup`](../-task-group/index.md)`<`[`SFTP`](../-s-f-t-p/index.md)`>`<br>Convenience property that upon use automatically instantiates and returns a [TaskGroup](../-task-group/index.md) of type [SFTP](../-s-f-t-p/index.md) with the name `sftp`. |
| [swap](../swap.md) | `val `[`Subs`](./index.md)`.swap: `[`TaskGroup`](../-task-group/index.md)`<`[`Swap`](../-swap/index.md)`>`<br>Convenience property that upon use automatically instantiates and returns a [TaskGroup](../-task-group/index.md) of type [Swap](../-swap/index.md) with the name `swap`. |
| [torrent](../torrent.md) | `val `[`Subs`](./index.md)`.torrent: `[`TaskGroup`](../-task-group/index.md)`<`[`Torrent`](../-torrent/index.md)`>`<br>Convenience property that upon use automatically instantiates and returns a [TaskGroup](../-task-group/index.md) of type [Torrent](../-torrent/index.md) with the name `torrent`. |
