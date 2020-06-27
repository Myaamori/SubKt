[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [ItemGroupContext](./index.md)

# ItemGroupContext

`abstract class ItemGroupContext` [(source)](https://github.com/Myaamori/SubKt/blob/master/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L69)

Context for defining groups of objects such as tasks.

Provides convenience functions for defining tasks and other objects
for all entries associated with this context.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ItemGroupContext()`<br>Context for defining groups of objects such as tasks. |

### Properties

| Name | Summary |
|---|---|
| [entries](entries.md) | `abstract val entries: `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<out `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>`<br>A list of entries (episodes or batches) this context should generate tasks for. |
| [subs](subs.md) | `abstract val subs: `[`Subs`](../-subs/index.md)<br>The [Subs](../-subs/index.md) instance all tasks created in this context should be associated with. |
| [taskGroups](task-groups.md) | `abstract val taskGroups: `[`MutableMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`TaskGroup`](../-task-group/index.md)`<*>>`<br>The map to store [TaskGroup](../-task-group/index.md) objects created in this context. |

### Functions

| Name | Summary |
|---|---|
| [episodes](episodes.md) | `abstract fun episodes(entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>The episodes associated with [entry](episodes.md#myaa.subkt.tasks.ItemGroupContext$episodes(kotlin.String)/entry). |
| [invoke](invoke.md) | `operator fun <T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> `[`TaskGroup`](../-task-group/index.md)`<`[`T`](invoke.md#T)`>.invoke(action: `[`T`](invoke.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Create and/or configure tasks of type [T](invoke.md#T) for all entries in [entries](entries.md).`operator fun <T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`.invoke(action: `[`T`](invoke.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`TaskGroup`](../-task-group/index.md)`<`[`T`](invoke.md#T)`>`<br>Creates a new task group with the name given by the string the function is invoked on, or returns the task group with the given name if it already exists, and configures a task for each entry in the current context using the given closure.`operator fun <T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<`[`T`](invoke.md#T)`>.invoke(action: `[`T`](invoke.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`TaskCreator`](../-task-creator/index.md)`<`[`T`](invoke.md#T)`>`<br>Returns a delegate that when accessed returns a task group with the same name as the property it is bound to. Also configures one task for each entry in the current context using the given closure. |
| [isBatch](is-batch.md) | `abstract fun isBatch(entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>True if [entry](is-batch.md#myaa.subkt.tasks.ItemGroupContext$isBatch(kotlin.String)/entry) corresponds to a batch entry. |
| [provider](provider.md) | `fun <T> provider(action: `[`ValueClosure`](../-value-closure/index.md)`<*>.() -> `[`T`](provider.md#T)`): `[`ProviderGroup`](../-provider-group/index.md)`<`[`T`](provider.md#T)`>`<br>Creates a [ProviderGroup](../-provider-group/index.md) that evaluates the given closure lazily, returning a [Provider](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html) when an item is requested for a given entry. The closure is evaluated by running [Provider.get](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html#get()) on the returned provider. |
| [task](task.md) | `fun <T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> task(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, klass: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<`[`T`](task.md#T)`>): `[`TaskGroup`](../-task-group/index.md)`<`[`T`](task.md#T)`>`<br>Create a new task group, or returns the task group with the given name if it already exists.`fun <T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> task(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, klass: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<`[`T`](task.md#T)`>, action: `[`T`](task.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`TaskGroup`](../-task-group/index.md)`<`[`T`](task.md#T)`>`<br>`fun <T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> task(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, action: `[`T`](task.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`TaskGroup`](../-task-group/index.md)`<`[`T`](task.md#T)`>`<br>Creates a new task group, or returns the task group with the given name if it already exists, and configures a task for each entry in the current context using the given closure.`fun <T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> task(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`TaskGroup`](../-task-group/index.md)`<`[`T`](task.md#T)`>`<br>Creates a new task group, or returns the task group with the given name if it already exists.`fun <T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> task(action: (`[`T`](task.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)? = null): `[`TaskCreator`](../-task-creator/index.md)`<`[`T`](task.md#T)`>`<br>Returns a delegate that when accessed returns a task group with the same name as the property it is bound to. Optionally configures one task for each entry in the current context using the given closure. |
| [value](value.md) | `fun <T> value(action: `[`ValueClosure`](../-value-closure/index.md)`<*>.() -> `[`T`](value.md#T)`): `[`ValueGroup`](../-value-group/index.md)`<`[`T`](value.md#T)`>`<br>Creates a [ValueGroup](../-value-group/index.md), evaluating the given closure immediately for each entry in the given context. |

### Inheritors

| Name | Summary |
|---|---|
| [Subs](../-subs/index.md) | `open class Subs : `[`ItemGroupContext`](./index.md)<br>Central object that keeps track of episodes, batches, tasks and user-loaded properties. For tasks to be generated correctly, [episodes](../-subs/episodes.md) and optionally [batches](../-subs/batches.md) should be set. Set [release](../-subs/release.md) if you wish to be able to differentiate between different releases when looking up properties. |
