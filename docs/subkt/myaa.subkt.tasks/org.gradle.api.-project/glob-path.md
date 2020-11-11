[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [org.gradle.api.Project](index.md) / [globPath](./glob-path.md)

# globPath

`fun `[`Project`](https://docs.gradle.org/current/javadoc/org/gradle/api/Project.html)`.globPath(glob: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.12/src/main/kotlin/myaa/subkt/tasks/plugin.kt#L158)

Finds paths matching the given string. `%` and `*` are interpreted as wildcards.

Returns the original string if no matching path was found.

