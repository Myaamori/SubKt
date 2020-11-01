[subkt](../../../index.md) / [myaa.subkt.tasks](../../index.md) / [PropertyTask](../index.md) / [TaskProperty](./index.md)

# TaskProperty

`inner class TaskProperty<T : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.11/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L618)

Delegate for reading properties from a JSON file associated with this task.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `TaskProperty(default: () -> `[`T`](index.md#T)`)`<br>Delegate for reading properties from a JSON file associated with this task. |

### Properties

| Name | Summary |
|---|---|
| [default](default.md) | `val default: () -> `[`T`](index.md#T) |

### Functions

| Name | Summary |
|---|---|
| [getValue](get-value.md) | `operator fun getValue(thisRef: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?, property: `[`KProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-property/index.html)`<*>): `[`T`](index.md#T) |
| [setValue](set-value.md) | `operator fun setValue(thisRef: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?, property: `[`KProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-property/index.html)`<*>, value: `[`T`](index.md#T)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
