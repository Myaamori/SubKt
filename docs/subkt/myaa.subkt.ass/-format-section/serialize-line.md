[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [FormatSection](index.md) / [serializeLine](./serialize-line.md)

# serializeLine

`fun serializeLine(line: `[`T`](index.md#T)`, process: (`[`MutableMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` = { }): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/master/src/main/kotlin/myaa/subkt/ass/parser.kt#L985)

Serializes a single line according to the field order given by [format](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/format.html) in this section.

### Parameters

`line` - The line object to serialize.

`process` - An optional callback allowing the caller to modify the line before it
gets serialized. Takes a mutable map where the keys are the ASS fields as named in [format](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/format.html).
Changes to the map will be reflected in the serialization.