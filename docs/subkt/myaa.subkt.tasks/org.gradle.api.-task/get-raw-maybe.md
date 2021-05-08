[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [org.gradle.api.Task](index.md) / [getRawMaybe](./get-raw-maybe.md)

# getRawMaybe

`fun `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.getRawMaybe(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.19/src/main/kotlin/myaa/subkt/tasks/plugin.kt#L270)

Searches for the given property in the [Subs](../-subs/index.md) object's [SubProperties](../-sub-properties/index.md) instance,
and returns the raw string, possibly null.

This function is run in a task context, using the [entry](entry.md) and [release](release.md) values for this task.

### Parameters

`propertyName` - The property to find.