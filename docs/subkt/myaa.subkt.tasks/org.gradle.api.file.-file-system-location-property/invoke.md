[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [org.gradle.api.file.FileSystemLocationProperty](index.md) / [invoke](./invoke.md)

# invoke

`operator fun <T : `[`FileSystemLocation`](https://docs.gradle.org/current/javadoc/org/gradle/api/file/FileSystemLocation.html)`> `[`FileSystemLocationProperty`](https://docs.gradle.org/current/javadoc/org/gradle/api/file/FileSystemLocationProperty.html)`<`[`T`](invoke.md#T)`>.invoke(provider: `[`TaskProvider`](https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/TaskProvider.html)`<*>): `[`FileSystemLocationProperty`](https://docs.gradle.org/current/javadoc/org/gradle/api/file/FileSystemLocationProperty.html)`<`[`T`](invoke.md#T)`!>!` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.13/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L2160)

Set the file property to the output of the given task, evaluated lazily.
Only succeeds if the task outputs exactly one file.

`operator fun <T : `[`FileSystemLocation`](https://docs.gradle.org/current/javadoc/org/gradle/api/file/FileSystemLocation.html)`> `[`FileSystemLocationProperty`](https://docs.gradle.org/current/javadoc/org/gradle/api/file/FileSystemLocationProperty.html)`<`[`T`](invoke.md#T)`>.invoke(file: `[`File`](https://docs.oracle.com/javase/9/docs/api/java/io/File.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.13/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L2166)

Sets the file property to the given file.

