[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [TaskGroup](index.md) / [createItem](./create-item.md)

# createItem

`protected fun createItem(entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, isBatch: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, episodes: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`TaskProvider`](https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/TaskProvider.html)`<`[`T`](index.md#T)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.19/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L349)

Overrides [ItemGroup.createItem](../-item-group/create-item.md)

Registers a new task for the given entry, or returns an existing task if it exists.
The returned task will following the naming scheme [name](name.md).[entry](create-item.md#myaa.subkt.tasks.TaskGroup$createItem(kotlin.String, kotlin.Boolean, kotlin.collections.List((kotlin.String)))/entry).

### Parameters

`entry` - The entry for this task, i.e. the episode or the batch identifier.

`isBatch` - True if this corresponds to a batch task.

`episodes` - The episodes that correspond to this entry. Usually a single-element
list containing [entry](create-item.md#myaa.subkt.tasks.TaskGroup$createItem(kotlin.String, kotlin.Boolean, kotlin.collections.List((kotlin.String)))/entry) if [isBatch](create-item.md#myaa.subkt.tasks.TaskGroup$createItem(kotlin.String, kotlin.Boolean, kotlin.collections.List((kotlin.String)))/isBatch) is false.