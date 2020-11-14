[subkt](../index.md) / [myaa.subkt.ass](index.md) / [deserializeAss](./deserialize-ass.md)

# deserializeAss

`fun <T : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`> deserializeAss(s: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, klass: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<`[`T`](deserialize-ass.md#T)`>): `[`T`](deserialize-ass.md#T)`?` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.13/src/main/kotlin/myaa/subkt/ass/parser.kt#L947)

Return the given ASS string deserialized to the given type [T](deserialize-ass.md#T), or `null`
if the string could not be deserialized.

Supports [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), [Color](https://docs.oracle.com/javase/9/docs/api/java/awt/Color.html) and [Duration](https://docs.oracle.com/javase/9/docs/api/java/time/Duration.html).

### Parameters

`s` - The string to deserialize.

`klass` - The class object of the type to deserialize to.