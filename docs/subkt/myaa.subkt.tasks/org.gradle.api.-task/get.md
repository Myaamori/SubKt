[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [org.gradle.api.Task](index.md) / [get](./get.md)

# get

`fun `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.get(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!>!` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.13/src/main/kotlin/myaa/subkt/tasks/plugin.kt#L322)

Searches for the given property in the [Subs](../-subs/index.md) object's [SubProperties](../-sub-properties/index.md) instance,
evaluates its value using [evaluate](evaluate.md), and returns a single string, assuming
that the resulting list contains only one element.

This function is run in a task context, using the [entry](entry.md) and [release](release.md) values for this task.

``` kotlin
// from task mux.03
// property file: TV.03.value=$release/$episode/show_${episode}.ass
get("value")
// Output: TV/03/show_03.ass
```

### Parameters

`propertyName` - The property to find.