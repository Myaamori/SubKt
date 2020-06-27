[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [ValueClosure](./index.md)

# ValueClosure

`class ValueClosure<T>` [(source)](https://github.com/Myaamori/SubKt/blob/master/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L408)

### Types

| Name | Summary |
|---|---|
| [ClosureContext](-closure-context/index.md) | `inner class ClosureContext<T> : `[`BaseContext`](../-base-context/index.md)<br>[AbstractContext](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html) implementation for getting properties in a [ValueClosure](./index.md) context. |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ValueClosure(entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, isBatch: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, episodes: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, taskGroup: `[`ItemGroup`](../-item-group/index.md)`<`[`T`](-closure-context/index.md#T)`>)` |

### Properties

| Name | Summary |
|---|---|
| [batch](batch.md) | `val batch: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Alias of [entry](entry.md). |
| [entry](entry.md) | `val entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The entry this value corresponds to. |
| [episode](episode.md) | `val episode: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Alias of [entry](entry.md). |
| [episodes](episodes.md) | `val episodes: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>The episodes corresponding to this entry. |
| [isBatch](is-batch.md) | `val isBatch: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Whether this entry is a batch entry. |
| [release](release.md) | `val release: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!`<br>The current release. |
| [taskGroup](task-group.md) | `val taskGroup: `[`ItemGroup`](../-item-group/index.md)`<`[`T`](-closure-context/index.md#T)`>`<br>The item group this value belongs to. |

### Functions

| Name | Summary |
|---|---|
| [evaluate](evaluate.md) | `fun evaluate(expression: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>!>!`<br>Evaluates a string. See [Task.evaluate](../org.gradle.api.-task/evaluate.md). |
| [evaluateTemplate](evaluate-template.md) | `fun evaluateTemplate(expression: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!>!`<br>Evaluates a string. See [Task.evaluateTemplate](../org.gradle.api.-task/evaluate-template.md). |
| [get](get.md) | `fun get(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!>!`<br>Searches for the given property and returns it as a single element. See [Task.get](../org.gradle.api.-task/get.md). |
| [getAs](get-as.md) | `fun <T> getAs(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`T`](get-as.md#T)`>!`<br>Searches for the given property and returns it as a single element of the given type. See [Task.getAs](../org.gradle.api.-task/get-as.md). |
| [getFile](get-file.md) | `fun getFile(filename: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!>!`<br>Processes the given file. See [Task.getFile](../org.gradle.api.-task/get-file.md). |
| [getList](get-list.md) | `fun getList(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>`<br>Searches for the given property and returns it as a list. See [Task.getList](../org.gradle.api.-task/get-list.md). |
| [getListAs](get-list-as.md) | `fun <T> getListAs(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`T`](get-list-as.md#T)`>!>!`<br>Searches for the given property and returns it as a list of the given type. See [Task.getListAs](../org.gradle.api.-task/get-list-as.md). |
| [getRaw](get-raw.md) | `fun getRaw(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Searches for the given property. See [Task.getRaw](../org.gradle.api.-task/get-raw.md). |
| [getRawMaybe](get-raw-maybe.md) | `fun getRawMaybe(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>Searches for the given property. Can return null. See [Task.getRawMaybe](../org.gradle.api.-task/get-raw-maybe.md). |
| [item](item.md) | `fun <U> `[`ItemGroup`](../-item-group/index.md)`<`[`U`](item.md#U)`>.item(): `[`U`](item.md#U)<br>Gets the item from the given item group that corresponds to [entry](entry.md). |
| [items](items.md) | `fun <U> `[`ItemGroup`](../-item-group/index.md)`<`[`U`](items.md#U)`>.items(): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`U`](items.md#U)`>`<br>Gets the items from the given item group that correspond to [episodes](episodes.md). |
