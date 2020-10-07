[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [org.gradle.api.Task](index.md) / [getListAs](./get-list-as.md)

# getListAs

`inline fun <reified T> `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.getListAs(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`T`](get-list-as.md#T)`>!>!` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.10/src/main/kotlin/myaa/subkt/tasks/plugin.kt#L284)

Searches for the given property in the [Subs](../-subs/index.md) object's [SubProperties](../-sub-properties/index.md) instance,
evaluates its value using [evaluate](evaluate.md), and casts the list elements to the given type
using [String.asType](../kotlin.-string/as-type.md).

This function is run in a task context, using the [entry](entry.md) and [release](release.md) values for this task.

``` kotlin
// from task mux.03
// property file: TV.03.values={1..5}
getListAs<Int>("values")
// Output: [1, 2, 3, 4, 5] (type: List<Int>)
```

### Parameters

`propertyName` - The property to find.