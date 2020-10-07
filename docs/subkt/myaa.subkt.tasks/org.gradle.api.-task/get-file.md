[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [org.gradle.api.Task](index.md) / [getFile](./get-file.md)

# getFile

`fun `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.getFile(filename: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!>!` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.10/src/main/kotlin/myaa/subkt/tasks/plugin.kt#L323)

Reads the specified file and processes it using
[Velocity](https://velocity.apache.org/engine/2.2/user-guide.html).

This function is run in a task context, using the [entry](entry.md) and [release](release.md) values for this task.

``` kotlin
getFile("02/release_post.txt")
```

### Parameters

`filename` - The path to the file to read.