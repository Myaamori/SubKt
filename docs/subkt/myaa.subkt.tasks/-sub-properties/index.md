[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [SubProperties](./index.md)

# SubProperties

`class SubProperties` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.13/src/main/kotlin/myaa/subkt/tasks/plugin.kt#L58)

Reads a list of properties of the form `release.entry.property=value`, which can
later be looked up using the [match](match.md) method.

Properties may contain wildcards, which will match any sequence of characters.
For instance, a property defined as `TV.episode*.name=value` will be found
with a `match("name", entry="episode01", release="TV")` call.
Groups (e.g. `{01,03,04}.op=OP1.ass`) and ranges (e.g. `{01..12}.cour=1`) are also supported.

Unspecified release and entry components are equivalent to wildcards.
In other words, `name=value` is equivalent to `*.*.name=value`, while
`01.name=value` is equivalent to `*.01.name=value`.

Properties are ordered, and the value of the last property matching the
[match](match.md) query will be returned. Thus, later values will override earlier values.

Lines starting with `#` will be interpreted as comments and ignored.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SubProperties()`<br>Reads a list of properties of the form `release.entry.property=value`, which can later be looked up using the [match](match.md) method. |

### Functions

| Name | Summary |
|---|---|
| [add](add.md) | `fun add(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, value: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Add or override existing properties from the build script. |
| [match](match.md) | `fun match(property: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", release: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = ""): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>Finds the last property entry that matches the given property, entry and release. |
| [parse](parse.md) | `fun parse(f: `[`File`](https://docs.oracle.com/javase/9/docs/api/java/io/File.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
