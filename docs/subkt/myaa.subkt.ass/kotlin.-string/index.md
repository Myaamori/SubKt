[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [kotlin.String](./index.md)

### Extensions for kotlin.String

| Name | Summary |
|---|---|
| [assBoolean](ass-boolean.md) | `val `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`.assBoolean: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Gets this string as a [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), parsing it as an ASS boolean. `true` if `-1` and `false` if `0`. |
| [assColor](ass-color.md) | `val `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`.assColor: `[`Color`](https://docs.oracle.com/javase/9/docs/api/java/awt/Color.html)<br>Gets this string as a [Color](https://docs.oracle.com/javase/9/docs/api/java/awt/Color.html), parsing it as an ASS color. The format should follow `&HBBGGRRAA` where BB, GG, RR and AA are hex values representing the blue, green, red and alpha components respectively. |
| [assTime](ass-time.md) | `val `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`.assTime: `[`Duration`](https://docs.oracle.com/javase/9/docs/api/java/time/Duration.html)<br>Gets this string as a [Duration](https://docs.oracle.com/javase/9/docs/api/java/time/Duration.html), parsing it as an ASS time. The format should follow `H:MM:SS.CC` where H, MM, SS and CC represent the hours, minutes, seconds and centiseconds respectively. |
