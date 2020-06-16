[subkt](../index.md) / [myaa.subkt.tasks](index.md) / [globPath](./glob-path.md)

# globPath

`fun globPath(glob: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Path`](https://docs.oracle.com/javase/9/docs/api/java/nio/file/Path.html)`>`

Finds paths matching the given string. `%` and `*` are interpreted as wildcards.

Returns the original string if no matching path was found.

