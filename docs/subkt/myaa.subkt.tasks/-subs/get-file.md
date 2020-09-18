[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Subs](index.md) / [getFile](./get-file.md)

# getFile

`fun getFile(filename: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", context: `[`AbstractContext`](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html)`? = null): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!>!` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.9/src/main/kotlin/myaa/subkt/tasks/plugin.kt#L657)

Reads the specified file and processes it using
[Velocity](https://velocity.apache.org/engine/2.2/user-guide.html).

This function is run outside of a task context, using only [release](release.md) for lookup
unless an entry is manually specified.

``` kotlin
getFile("info.txt")
```

### Parameters

`filename` - The path to the file to read.

`entry` - Optional manually specified entry for property lookup.