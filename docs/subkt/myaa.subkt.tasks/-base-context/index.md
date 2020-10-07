[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [BaseContext](./index.md)

# BaseContext

`abstract class BaseContext : `[`AbstractContext`](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.10/src/main/kotlin/myaa/subkt/tasks/plugin.kt#L86)

Simple base implementation of a Velocity [AbstractContext](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `BaseContext()`<br>Simple base implementation of a Velocity [AbstractContext](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html). |

### Functions

| Name | Summary |
|---|---|
| [doGet](do-get.md) | `abstract fun doGet(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?` |
| [internalContainsKey](internal-contains-key.md) | `open fun internalContainsKey(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [internalGet](internal-get.md) | `open fun internalGet(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?` |
| [internalGetKeys](internal-get-keys.md) | `open fun internalGetKeys(): `[`Nothing`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-nothing/index.html) |
| [internalPut](internal-put.md) | `open fun internalPut(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?` |
| [internalRemove](internal-remove.md) | `open fun internalRemove(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?` |

### Inheritors

| Name | Summary |
|---|---|
| [ClosureContext](../-value-closure/-closure-context/index.md) | `inner class ClosureContext<T> : `[`BaseContext`](./index.md)<br>[AbstractContext](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html) implementation for getting properties in a [ValueClosure](../-value-closure/index.md) context. |
| [SubContext](../-subs/-sub-context/index.md) | `inner class SubContext : `[`BaseContext`](./index.md)<br>[AbstractContext](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html) implementation for getting properties in a [Subs](../-subs/index.md) context. |
| [TaskContext](../-task-context/index.md) | `class TaskContext : `[`BaseContext`](./index.md)<br>[AbstractContext](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html) implementation for getting properties in a task context. |
