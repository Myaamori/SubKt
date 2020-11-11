[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [Line](./index.md)

# Line

`abstract class Line : `[`Serializable`](https://docs.oracle.com/javase/9/docs/api/java/io/Serializable.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.12/src/main/kotlin/myaa/subkt/ass/parser.kt#L246)

A basic line with a type.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Line(type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)`<br>A basic line with a type. |

### Properties

| Name | Summary |
|---|---|
| [type](type.md) | `open val type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The type of the line. |

### Inheritors

| Name | Summary |
|---|---|
| [KeyValLine](../-key-val-line/index.md) | `class KeyValLine : `[`Line`](./index.md)<br>Corresponds to a raw ASS line represented textually as `Type: Value`, with the value unparsed. |
| [MapLine](../-map-line/index.md) | `abstract class MapLine<L : `[`MapLine`](../-map-line/index.md)`<`[`L`](../-map-line/index.md#L)`>> : `[`Line`](./index.md)`, `[`MutableMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>`<br>A line in a [FormatSection](../-format-section/index.md). The fields contained in this object can be accessed in three ways: |
