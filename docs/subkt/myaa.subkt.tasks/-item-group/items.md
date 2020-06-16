[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [ItemGroup](index.md) / [items](./items.md)

# items

`fun items(entries: `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`T`](index.md#T)`>`

Get all items corresponding to the specified entries.

``` kotlin
mux.items(listOf("01", "02", "03", "04")) // returns the mux.01, mux.02, mux.03, mux.04 tasks
```

`fun items(task: `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`T`](index.md#T)`>`

Get all items corresponding to the episodes of the given task.

``` kotlin
// assume batch vol1 contains episodes 01, 02, 03, and 04
tasks(listOf("vol1")) { // configure tasks for vol1 only
    mux {
        merge.items(this) // returns merge.01, merge.02, merge.03, merge.04
    }
}
```

