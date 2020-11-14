[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [TaskCreator](./index.md)

# TaskCreator

`class TaskCreator<T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.13/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L226)

Delegate for creation of tasks using property delegate syntax.
See [ItemGroupContext.task](../-item-group-context/task.md).

### Types

| Name | Summary |
|---|---|
| [WrapperDelegate](-wrapper-delegate/index.md) | `class WrapperDelegate<T>`<br>Delegate which always returns the constant [item](-wrapper-delegate/item.md). |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `TaskCreator(context: `[`ItemGroupContext`](../-item-group-context/index.md)`, klass: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<`[`T`](index.md#T)`>, initAction: (`[`T`](index.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)? = null)`<br>Delegate for creation of tasks using property delegate syntax. See [ItemGroupContext.task](../-item-group-context/task.md). |

### Properties

| Name | Summary |
|---|---|
| [context](context.md) | `val context: `[`ItemGroupContext`](../-item-group-context/index.md)<br>The context this delegate belongs to. |
| [initAction](init-action.md) | `val initAction: (`[`T`](index.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)?`<br>An optional closure for configuring the task. |
| [klass](klass.md) | `val klass: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<`[`T`](index.md#T)`>`<br>The type of the task to create. |

### Functions

| Name | Summary |
|---|---|
| [provideDelegate](provide-delegate.md) | `operator fun provideDelegate(receiver: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?, prop: `[`KProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-property/index.html)`<*>): `[`TaskCreator.WrapperDelegate`](-wrapper-delegate/index.md)`<`[`TaskGroup`](../-task-group/index.md)`<`[`T`](index.md#T)`>>` |
