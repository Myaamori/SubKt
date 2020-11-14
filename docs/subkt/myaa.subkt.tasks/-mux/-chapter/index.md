[subkt](../../../index.md) / [myaa.subkt.tasks](../../index.md) / [Mux](../index.md) / [Chapter](./index.md)

# Chapter

`inner class Chapter` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.13/src/main/kotlin/myaa/subkt/tasks/muxtask.kt#L461)

Represents a chapter file to mux, added using [chaptersProperty](../chapters-property.md).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Chapter(file: `[`File`](https://docs.oracle.com/javase/9/docs/api/java/io/File.html)`)`<br>Represents a chapter file to mux, added using [chaptersProperty](../chapters-property.md). |

### Properties

| Name | Summary |
|---|---|
| [charset](charset.md) | `val charset: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>Sets the character encoding of the chapter file. |
| [delay](delay.md) | `val delay: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`>`<br>Sets the delay of the chapters in milliseconds. |
| [file](file.md) | `val file: `[`File`](https://docs.oracle.com/javase/9/docs/api/java/io/File.html)<br>The source file. |
| [lang](lang.md) | `val lang: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>Sets the language of the chapters. |
| [stretch](stretch.md) | `val stretch: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`>`<br>Value to multiply the timestamps by. E.g. a value of 2 makes the track twice as long. |
| [sync](sync.md) | `val sync: `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
