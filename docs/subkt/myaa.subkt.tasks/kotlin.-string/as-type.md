[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [kotlin.String](index.md) / [asType](./as-type.md)

# asType

`inline fun <reified T> `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`.asType(): `[`T`](as-type.md#T) [(source)](https://github.com/Myaamori/SubKt/blob/master/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L2106)

Converts a string to the specified type.

Supports the following formats:

* [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)
* [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)
* [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)
* [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)
* [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)
* [Regex](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-regex/index.html)
* [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) - supports the values `true` and `false`
* [Duration](https://docs.oracle.com/javase/9/docs/api/java/time/Duration.html) - supports times in ASS format

### Parameters

`T` - The type to convert to.