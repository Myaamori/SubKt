[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [kotlin.String](index.md) / [assColor](./ass-color.md)

# assColor

`val `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`.assColor: `[`Color`](https://docs.oracle.com/javase/9/docs/api/java/awt/Color.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.4/src/main/kotlin/myaa/subkt/ass/parser.kt#L809)

Gets this string as a [Color](https://docs.oracle.com/javase/9/docs/api/java/awt/Color.html), parsing it as an ASS color.
The format should follow `&HBBGGRRAA` where BB, GG, RR and AA
are hex values representing the blue, green, red and alpha
components respectively.

### Exceptions

`IllegalArgumentException` - If not a valid ASS color.