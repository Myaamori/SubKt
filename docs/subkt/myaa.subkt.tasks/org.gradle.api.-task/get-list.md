[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [org.gradle.api.Task](index.md) / [getList](./get-list.md)

# getList

`fun `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.getList(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.12/src/main/kotlin/myaa/subkt/tasks/plugin.kt#L296)

Searches for the given property in the [Subs](../-subs/index.md) object's [SubProperties](../-sub-properties/index.md) instance,
and evaluates its value using [evaluate](evaluate.md).

This function is run in a task context, using the [entry](entry.md) and [release](release.md) values for this task.

``` kotlin
// from task mux.03
// property file: TV.03.value=hello{1..3}|test|$episode
getList("value")
// Output: [hello1, hello2, hello3, test, 03]
```

### Parameters

`propertyName` - The property to find.