[subkt](../../../index.md) / [myaa.subkt.tasks](../../index.md) / [TaskCreator](../index.md) / [WrapperDelegate](./index.md)

# WrapperDelegate

`class WrapperDelegate<T>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.8/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L243)

Delegate which always returns the constant [item](item.md).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `WrapperDelegate(item: `[`T`](index.md#T)`)`<br>Delegate which always returns the constant [item](item.md). |

### Properties

| Name | Summary |
|---|---|
| [item](item.md) | `val item: `[`T`](index.md#T) |

### Functions

| Name | Summary |
|---|---|
| [getValue](get-value.md) | `operator fun getValue(receiver: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?, property: `[`KProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-property/index.html)`<*>): `[`T`](index.md#T) |
