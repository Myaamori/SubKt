[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [ValueGroup](./index.md)

# ValueGroup

`class ValueGroup<T> : `[`ItemGroup`](../-item-group/index.md)`<`[`T`](index.md#T)`>` [(source)](https://github.com/Myaamori/SubKt/blob/master/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L531)

[ItemGroup](../-item-group/index.md) that keeps track of simple values of type [T](index.md#T).
The closure is evaluated immediately for each entry.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ValueGroup(subs: `[`Subs`](../-subs/index.md)`, action: `[`ValueClosure`](../-value-closure/index.md)`<`[`T`](index.md#T)`>.() -> `[`T`](index.md#T)`)`<br>[ItemGroup](../-item-group/index.md) that keeps track of simple values of type [T](index.md#T). The closure is evaluated immediately for each entry. |

### Properties

| Name | Summary |
|---|---|
| [action](action.md) | `val action: `[`ValueClosure`](../-value-closure/index.md)`<`[`T`](index.md#T)`>.() -> `[`T`](index.md#T) |

### Inherited Properties

| Name | Summary |
|---|---|
| [subs](../-item-group/subs.md) | `val subs: `[`Subs`](../-subs/index.md)<br>The [Subs](../-subs/index.md) instance this [ItemGroup](../-item-group/index.md) is associated with. |

### Functions

| Name | Summary |
|---|---|
| [createItem](create-item.md) | `fun createItem(entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, isBatch: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, episodes: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`T`](index.md#T)<br>Returns a new item of type [T](../-item-group/index.md#T). |

### Inherited Functions

| Name | Summary |
|---|---|
| [item](../-item-group/item.md) | `fun item(entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`T`](../-item-group/index.md#T)<br>Get the item corresponding to the specified entry.`fun item(task: `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`): `[`T`](../-item-group/index.md#T)<br>Get the item of the same the entry as the given task. |
| [items](../-item-group/items.md) | `fun items(entries: `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`T`](../-item-group/index.md#T)`>`<br>Get all items corresponding to the specified entries.`fun items(task: `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`T`](../-item-group/index.md#T)`>`<br>Get all items corresponding to the episodes of the given task. |
| [registerItemMaybe](../-item-group/register-item-maybe.md) | `fun registerItemMaybe(entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, isBatch: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, episodes: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`T`](../-item-group/index.md#T)<br>Register a new item or return the item if it already exists. |
