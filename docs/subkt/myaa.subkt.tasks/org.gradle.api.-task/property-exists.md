[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [org.gradle.api.Task](index.md) / [propertyExists](./property-exists.md)

# propertyExists

`fun `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.propertyExists(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.13/src/main/kotlin/myaa/subkt/tasks/plugin.kt#L271)

Returns true if the given property exists in the [Subs](../-subs/index.md) object's [SubProperties](../-sub-properties/index.md) instance
for the given context.

This function is run in a task context, using the [entry](entry.md) and [release](release.md) values for this task.

### Parameters

`propertyName` - The property to find.