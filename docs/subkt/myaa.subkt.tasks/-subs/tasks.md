[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Subs](index.md) / [tasks](./tasks.md)

# tasks

`fun tasks(episodes: `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, action: `[`ItemGroupContext`](../-item-group-context/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.4/src/main/kotlin/myaa/subkt/tasks/plugin.kt#L380)

Creates a context for generating tasks for the specified entries.

### Parameters

`action` - A closure operating on a [ItemGroupContext](../-item-group-context/index.md) entry.