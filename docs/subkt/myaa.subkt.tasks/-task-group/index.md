[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [TaskGroup](./index.md)

# TaskGroup

`class TaskGroup<T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> : `[`ItemGroup`](../-item-group/index.md)`<`[`TaskProvider`](https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/TaskProvider.html)`<`[`T`](index.md#T)`>>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.12/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L327)

A group of tasks of the same type.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `TaskGroup(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, subs: `[`Subs`](../-subs/index.md)`, klass: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<`[`T`](index.md#T)`>)`<br>A group of tasks of the same type. |

### Properties

| Name | Summary |
|---|---|
| [klass](klass.md) | `val klass: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<`[`T`](index.md#T)`>`<br>The class object of the task type for this group. |
| [name](name.md) | `val name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The name of this task group. |

### Inherited Properties

| Name | Summary |
|---|---|
| [subs](../-item-group/subs.md) | `val subs: `[`Subs`](../-subs/index.md)<br>The [Subs](../-subs/index.md) instance this [ItemGroup](../-item-group/index.md) is associated with. |

### Functions

| Name | Summary |
|---|---|
| [createItem](create-item.md) | `fun createItem(entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, isBatch: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, episodes: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`TaskProvider`](https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/TaskProvider.html)`<`[`T`](index.md#T)`>`<br>Registers a new task for the given entry, or returns an existing task if it exists. The returned task will following the naming scheme [name](name.md).[entry](create-item.md#myaa.subkt.tasks.TaskGroup$createItem(kotlin.String, kotlin.Boolean, kotlin.collections.List((kotlin.String)))/entry). |

### Inherited Functions

| Name | Summary |
|---|---|
| [batchItems](../-item-group/batch-items.md) | `fun batchItems(entries: `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`T`](../-item-group/index.md#T)`>`<br>`fun batchItems(entries: `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<out `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`T`](../-item-group/index.md#T)`>`<br>Get all items corresponding to the specified entries.`fun batchItems(task: `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`T`](../-item-group/index.md#T)`>`<br>Get all items corresponding to the episodes of the given task. |
| [item](../-item-group/item.md) | `fun item(entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`T`](../-item-group/index.md#T)<br>Get the item corresponding to the specified entry.`fun item(task: `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`): `[`T`](../-item-group/index.md#T)<br>Get the item of the same the entry as the given task. |
| [registerItemMaybe](../-item-group/register-item-maybe.md) | `fun registerItemMaybe(entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, isBatch: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, episodes: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`T`](../-item-group/index.md#T)<br>Register a new item or return the item if it already exists. |
