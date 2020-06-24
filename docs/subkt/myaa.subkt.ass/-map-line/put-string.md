[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [MapLine](index.md) / [putString](./put-string.md)

# putString

`fun putString(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?` [(source)](https://github.com/Myaamori/SubKt/blob/master/src/main/kotlin/myaa/subkt/ass/parser.kt#L409)

Writes the given value to the field given by the key, automatically
deserializing the value if needed.

### Exceptions

`IllegalArgumentException` - If [value](put-string.md#myaa.subkt.ass.MapLine$putString(kotlin.String, kotlin.String)/value) could not be deserialized to
the appropriate type.