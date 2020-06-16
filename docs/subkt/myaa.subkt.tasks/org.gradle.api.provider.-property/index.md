[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [org.gradle.api.provider.Property](./index.md)

### Extensions for org.gradle.api.provider.Property

| Name | Summary |
|---|---|
| [invoke](invoke.md) | `operator fun <T> `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`T`](invoke.md#T)`>.invoke(value: `[`T`](invoke.md#T)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set the property to the given value.`operator fun <T> `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`T`](invoke.md#T)`>.invoke(provider: `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`T`](invoke.md#T)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set the property to the result of the given [Provider](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html), evaluated lazily.`operator fun <T> `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`T`](invoke.md#T)`>.invoke(call: () -> `[`T`](invoke.md#T)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set the property to the result of the given closure, evaluated lazily. |
