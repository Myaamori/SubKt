[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [org.gradle.api.Task](index.md) / [getRaw](./get-raw.md)

# getRaw

`fun `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.getRaw(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.9/src/main/kotlin/myaa/subkt/tasks/plugin.kt#L259)

Searches for the given property in the [Subs](../-subs/index.md) object's [SubProperties](../-sub-properties/index.md) instance,
and returns the raw string.
Raises an error if not found.

This function is run in a task context, using the [entry](entry.md) and [release](release.md) values for this task.

``` kotlin
// from task mux.03
// property file: TV.03.value=hello{1..3}|test|$episode
getRawMaybe("value")
// Output: hello{1..3}|test|$episode
```

### Parameters

`propertyName` - The property to find.