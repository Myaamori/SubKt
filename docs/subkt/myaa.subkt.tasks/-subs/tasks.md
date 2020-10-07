[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Subs](index.md) / [tasks](./tasks.md)

# tasks

`fun tasks(entries: `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, action: `[`ItemGroupContext`](../-item-group-context/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.10/src/main/kotlin/myaa/subkt/tasks/plugin.kt#L410)

Creates a context for generating tasks for the specified entries.

### Parameters

`entries` - A list of entries to generate tasks for.

`action` - A closure operating on a [ItemGroupContext](../-item-group-context/index.md) entry.`fun tasks(vararg entries: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, action: `[`ItemGroupContext`](../-item-group-context/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.10/src/main/kotlin/myaa/subkt/tasks/plugin.kt#L419)

Creates a context for generating tasks for the specified entries.

### Parameters

`entries` - Entries to generate tasks for.

`action` - A closure operating on a [ItemGroupContext](../-item-group-context/index.md) entry.`fun tasks(entries: `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<out `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>, action: `[`ItemGroupContext`](../-item-group-context/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.10/src/main/kotlin/myaa/subkt/tasks/plugin.kt#L428)

Creates a context for generating tasks for the specified entries.

### Parameters

`entries` - A provider for a list of entries to generate tasks for.

`action` - A closure operating on a [ItemGroupContext](../-item-group-context/index.md) entry.