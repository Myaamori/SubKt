[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [ItemGroupContext](index.md) / [invoke](./invoke.md)

# invoke

`operator fun <T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> `[`TaskGroup`](../-task-group/index.md)`<`[`T`](invoke.md#T)`>.invoke(action: `[`T`](invoke.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Create and/or configure tasks of type [T](invoke.md#T) for all entries in [entries](entries.md).

### Parameters

`action` - A closure operating on a [Task](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html). Called once for
each entry in the current context.`inline operator fun <reified T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`.invoke(noinline action: `[`T`](invoke.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`TaskGroup`](../-task-group/index.md)`<`[`T`](invoke.md#T)`>`

Creates a new task group with the name given by the string the function is invoked on,
or returns the task group with the given name if it already exists,
and configures a task for each entry in the current context using the given closure.

``` kotlin
"copy"<SubCopy> {
    from(mux.item())
    into("downloads")
}
```

### Parameters

`T` - The type of task associated with the task group.

`action` - A closure operating on a [Task](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html). Called once for
each entry in the current context.`operator fun <T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<`[`T`](invoke.md#T)`>.invoke(action: `[`T`](invoke.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`TaskCreator`](../-task-creator/index.md)`<`[`T`](invoke.md#T)`>`

Returns a delegate that when accessed returns a task group with the same name
as the property it is bound to. Also configures one task for each entry
in the current context using the given closure.

``` kotlin
val copy by SubCopy::class {
    from(mux.item())
    into("downloads")
}
```

### Parameters

`T` - The type of task associated with the task group.

`action` - A closure operating on a [Task](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html). Called once for
each entry in the current context.