[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [ItemGroup](./index.md)

# ItemGroup

`abstract class ItemGroup<T>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.11/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L266)

A group of items of type [T](index.md#T). Has convenience functions
for getting all items belonging to the same entry as a certain task,
or all items corresponding to a specified list of entries.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ItemGroup(subs: `[`Subs`](../-subs/index.md)`)`<br>A group of items of type [T](index.md#T). Has convenience functions for getting all items belonging to the same entry as a certain task, or all items corresponding to a specified list of entries. |

### Properties

| Name | Summary |
|---|---|
| [subs](subs.md) | `val subs: `[`Subs`](../-subs/index.md)<br>The [Subs](../-subs/index.md) instance this [ItemGroup](./index.md) is associated with. |

### Functions

| Name | Summary |
|---|---|
| [batchItems](batch-items.md) | `fun batchItems(entries: `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`T`](index.md#T)`>`<br>`fun batchItems(entries: `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<out `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`T`](index.md#T)`>`<br>Get all items corresponding to the specified entries.`fun batchItems(task: `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`T`](index.md#T)`>`<br>Get all items corresponding to the episodes of the given task. |
| [createItem](create-item.md) | `abstract fun createItem(entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, isBatch: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, episodes: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`T`](index.md#T)<br>Returns a new item of type [T](index.md#T). |
| [item](item.md) | `fun item(entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`T`](index.md#T)<br>Get the item corresponding to the specified entry.`fun item(task: `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`): `[`T`](index.md#T)<br>Get the item of the same the entry as the given task. |
| [registerItemMaybe](register-item-maybe.md) | `fun registerItemMaybe(entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, isBatch: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, episodes: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`T`](index.md#T)<br>Register a new item or return the item if it already exists. |

### Inheritors

| Name | Summary |
|---|---|
| [ProviderGroup](../-provider-group/index.md) | `class ProviderGroup<T> : `[`ItemGroup`](./index.md)`<`[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`T`](../-provider-group/index.md#T)`>>`<br>[ItemGroup](./index.md) that keeps track of providers for values of type [T](../-provider-group/index.md#T). A [Provider](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html) is generated for each entry, and the given closure is evaluated when the provider's value is requested. |
| [TaskGroup](../-task-group/index.md) | `class TaskGroup<T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> : `[`ItemGroup`](./index.md)`<`[`TaskProvider`](https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/TaskProvider.html)`<`[`T`](../-task-group/index.md#T)`>>`<br>A group of tasks of the same type. |
| [ValueGroup](../-value-group/index.md) | `class ValueGroup<T> : `[`ItemGroup`](./index.md)`<`[`T`](../-value-group/index.md#T)`>`<br>[ItemGroup](./index.md) that keeps track of simple values of type [T](../-value-group/index.md#T). The closure is evaluated immediately for each entry. |
