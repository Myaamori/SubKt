[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [org.gradle.api.Project](./index.md)

### Extensions for org.gradle.api.Project

| Name | Summary |
|---|---|
| [glob](glob.md) | `fun `[`Project`](https://docs.gradle.org/current/javadoc/org/gradle/api/Project.html)`.glob(s: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>Simple subset of Bash globs. Supports basic brace expansion using expressions like `{01..10}` and `{a,b,c}`, as well as wildcards. |
| [globPath](glob-path.md) | `fun `[`Project`](https://docs.gradle.org/current/javadoc/org/gradle/api/Project.html)`.globPath(glob: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>Finds paths matching the given string. `%` and `*` are interpreted as wildcards. |
