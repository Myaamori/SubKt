[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [ItemGroup](index.md) / [batchItems](./batch-items.md)

# batchItems

`fun batchItems(entries: `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`T`](index.md#T)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.11/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L280)

Get all items corresponding to the specified entries.

``` kotlin
mux.batchItems(listOf("01", "02", "03", "04")) // returns the mux.01, mux.02, mux.03, mux.04 tasks
```

### Parameters

`entries` - A list of entries to get the items for.`fun batchItems(entries: `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<out `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`T`](index.md#T)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.11/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L288)

Get all items corresponding to the specified entries.

``` kotlin
mux.batchItems(listOf("01", "02", "03", "04")) // returns the mux.01, mux.02, mux.03, mux.04 tasks
```

### Parameters

`entries` - A provider for a list of entries to get the items for.`fun batchItems(task: `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`T`](index.md#T)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.11/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L296)

Get all items corresponding to the episodes of the given task.

``` kotlin
// assume batch vol1 contains episodes 01, 02, 03, and 04
tasks(listOf("vol1")) { // configure tasks for vol1 only
    mux {
        merge.batchItems(this) // returns merge.01, merge.02, merge.03, merge.04
    }
}
```

