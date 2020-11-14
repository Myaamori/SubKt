[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [kotlin.String](index.md) / [assBoolean](./ass-boolean.md)

# assBoolean

`val `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`.assBoolean: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.13/src/main/kotlin/myaa/subkt/ass/parser.kt#L931)

Gets this string as a [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), parsing it as an ASS boolean.
`true` if `-1` and `false` if `0`.

### Exceptions

`IllegalArgumentException` - If not a valid ASS boolean.