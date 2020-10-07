[subkt](../../../index.md) / [myaa.subkt.tasks](../../index.md) / [Mux](../index.md) / [ConfigurableAttachment](./index.md)

# ConfigurableAttachment

`inner class ConfigurableAttachment : `[`PatternFilterable`](https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/util/PatternFilterable.html)`, `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)`<`[`File`](https://docs.oracle.com/javase/9/docs/api/java/io/File.html)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.10/src/main/kotlin/myaa/subkt/tasks/muxtask.kt#L508)

Represents a set of files to attach, added using [attach](../attach.md).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ConfigurableAttachment(fileTree: `[`ConfigurableFileTree`](https://docs.gradle.org/current/javadoc/org/gradle/api/file/ConfigurableFileTree.html)`)`<br>Represents a set of files to attach, added using [attach](../attach.md). |

### Properties

| Name | Summary |
|---|---|
| [fileTree](file-tree.md) | `val fileTree: `[`ConfigurableFileTree`](https://docs.gradle.org/current/javadoc/org/gradle/api/file/ConfigurableFileTree.html)<br>The backing [ConfigurableFileTree](https://docs.gradle.org/current/javadoc/org/gradle/api/file/ConfigurableFileTree.html) instance, for more fine-grained control of the attached files. |

### Functions

| Name | Summary |
|---|---|
| [from](from.md) | `fun from(dir: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`ConfigurableFileTree`](https://docs.gradle.org/current/javadoc/org/gradle/api/file/ConfigurableFileTree.html)`!`<br>Specifies base directory for this file tree using the given path. |
| [includeExtensions](include-extensions.md) | `fun includeExtensions(vararg extensions: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Filter files by file extension. Case-insensitive. |
