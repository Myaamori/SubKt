[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [org.gradle.api.Task](index.md) / [getAs](./get-as.md)

# getAs

`inline fun <reified T> `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.getAs(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`T`](get-as.md#T)`>!` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.7/src/main/kotlin/myaa/subkt/tasks/plugin.kt#L296)

Searches for the given property in the [Subs](../-subs/index.md) object's [SubProperties](../-sub-properties/index.md) instance,
evaluates its value using [evaluate](evaluate.md), and returns a single string, cast to
the given type using [String.asType](../kotlin.-string/as-type.md), assuming that the resulting list
contains only one element.

This function is run in a task context, using the [entry](entry.md) and [release](release.md) values for this task.

``` kotlin
// from task mux.03
// property file: TV.03.value=10
getAs<Int>("value")
// Output: 10 (type: Int)
```

### Parameters

`propertyName` - The property to find.