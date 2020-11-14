[subkt](../../../index.md) / [myaa.subkt.tasks](../../index.md) / [ValueClosure](../index.md) / [ClosureContext](./index.md)

# ClosureContext

`inner class ClosureContext<T> : `[`BaseContext`](../../-base-context/index.md) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.13/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L469)

[AbstractContext](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html) implementation for getting properties in a [ValueClosure](../index.md) context.

Will look for variables in the following places:

1. The properties of the associated [ValueClosure](../index.md)
2. The loaded [SubProperties](../../-sub-properties/index.md) properties, searched using the current entry and release
3. All tasks in the current project

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ClosureContext()`<br>[AbstractContext](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html) implementation for getting properties in a [ValueClosure](../index.md) context. |

### Functions

| Name | Summary |
|---|---|
| [doGet](do-get.md) | `fun doGet(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?` |

### Inherited Functions

| Name | Summary |
|---|---|
| [internalContainsKey](../../-base-context/internal-contains-key.md) | `open fun internalContainsKey(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [internalGet](../../-base-context/internal-get.md) | `open fun internalGet(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?` |
| [internalGetKeys](../../-base-context/internal-get-keys.md) | `open fun internalGetKeys(): `[`Nothing`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-nothing/index.html) |
| [internalPut](../../-base-context/internal-put.md) | `open fun internalPut(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?` |
| [internalRemove](../../-base-context/internal-remove.md) | `open fun internalRemove(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?` |
