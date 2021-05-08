[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [kotlin.String](index.md) / [assTime](./ass-time.md)

# assTime

`val `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`.assTime: `[`Duration`](https://docs.oracle.com/javase/9/docs/api/java/time/Duration.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.19/src/main/kotlin/myaa/subkt/ass/parser.kt#L895)

Gets this string as a [Duration](https://docs.oracle.com/javase/9/docs/api/java/time/Duration.html), parsing it as an ASS time.
The format should follow `H:MM:SS.CC` where H, MM, SS and CC
represent the hours, minutes, seconds and centiseconds respectively.

### Exceptions

`IllegalArgumentException` - If not a valid ASS time.