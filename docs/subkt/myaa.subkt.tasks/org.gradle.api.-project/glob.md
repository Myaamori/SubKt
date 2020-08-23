[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [org.gradle.api.Project](index.md) / [glob](./glob.md)

# glob

`fun `[`Project`](https://docs.gradle.org/current/javadoc/org/gradle/api/Project.html)`.glob(s: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.8/src/main/kotlin/myaa/subkt/tasks/plugin.kt#L176)

Simple subset of Bash globs. Supports basic brace expansion using
expressions like `{01..10}` and `{a,b,c}`, as well as wildcards.

``` kotlin
glob("{a,b,c}")
// Output: [a, b, c]

glob("test{a,b,c}")
// Output: [testa, testb, testc]

glob("{1..5}")
// Output: [1, 2, 3, 4, 5]

glob("{01..05}")
// Output: [01, 02, 03, 04, 05]

glob("test{01..05}hello")
// Output: [test01hello, test02hello, test03hello, test04hello, test05hello]

glob("{01,02}/*_qc.ass")
// Output: [01/eizouken_01_qc.ass, 02/eizouken_02_qc.ass]
```

