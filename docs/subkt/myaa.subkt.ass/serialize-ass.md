[subkt](../index.md) / [myaa.subkt.ass](index.md) / [serializeAss](./serialize-ass.md)

# serializeAss

`fun serializeAss(v: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.9/src/main/kotlin/myaa/subkt/ass/parser.kt#L870)

Serializes the given value as a string in ASS format.

Supports [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), [Color](https://docs.oracle.com/javase/9/docs/api/java/awt/Color.html) and [Duration](https://docs.oracle.com/javase/9/docs/api/java/time/Duration.html).

### Parameters

`v` - The value to serialize.

### Exceptions

`IllegalArgumentException` - If the type of the input is unsupported.