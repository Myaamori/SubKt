[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [ItemGroupContext](index.md) / [task](./task.md)

# task

`fun <T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> task(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, klass: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<`[`T`](task.md#T)`>): `[`TaskGroup`](../-task-group/index.md)`<`[`T`](task.md#T)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.13/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L114)

Create a new task group, or returns the task group with the given name if it already exists.

``` kotlin
val copyTaskGroup = task("copy", SubCopy::class)
```

### Parameters

`name` - The name of the task group.

`klass` - The type of task associated with the task group.`fun <T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> task(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, klass: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<`[`T`](task.md#T)`>, action: `[`T`](task.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`TaskGroup`](../-task-group/index.md)`<`[`T`](task.md#T)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.13/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L127)

Creates a new task group, or returns the task group with the given name if it already exists,
and configures a task for each entry in the current context using the given closure.

``` kotlin
task("copy", SubCopy::class) {
    from(mux.item())
    into("downloads")
}
```

### Parameters

`name` - The name of the task group.

`klass` - The type of task associated with the task group.

`action` - A closure operating on a [Task](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html). Called once for
each entry in the current context.`inline fun <reified T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> task(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`TaskGroup`](../-task-group/index.md)`<`[`T`](task.md#T)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.13/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L137)

Creates a new task group, or returns the task group with the given name if it already exists.

``` kotlin
val copyTaskGroup = task<SubCopy>("copy")
```

### Parameters

`name` - The name of the task group.

`T` - The type of task associated with the task group.`inline fun <reified T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> task(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, noinline action: `[`T`](task.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`TaskGroup`](../-task-group/index.md)`<`[`T`](task.md#T)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.13/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L149)

Creates a new task group, or returns the task group with the given name if it already exists,
and configures a task for each entry in the current context using the given closure.

``` kotlin
task<SubCopy>("copy") {
    from(mux.item())
    into("downloads")
}
```

### Parameters

`name` - The name of the task group.

`T` - The type of task associated with the task group.

`action` - A closure operating on a [Task](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html). Called once for
each entry in the current context.`inline fun <reified T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> task(noinline action: (`[`T`](task.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)? = null): `[`TaskCreator`](../-task-creator/index.md)`<`[`T`](task.md#T)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.13/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L162)

Returns a delegate that when accessed returns a task group with the same name
as the property it is bound to. Optionally configures one task for each entry
in the current context using the given closure.

``` kotlin
val copy by task<SubCopy> {
    from(mux.item())
    into("downloads")
}
```

### Parameters

`T` - The type of task associated with the task group.

`action` - A closure operating on a [Task](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html). Called once for
each entry in the current context.