[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [org.gradle.api.provider.Property](index.md) / [invoke](./invoke.md)

# invoke

`operator fun <T> `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`T`](invoke.md#T)`>.invoke(value: `[`T`](invoke.md#T)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.4/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L2120)

Set the property to the given value.

`operator fun <T> `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`T`](invoke.md#T)`>.invoke(provider: `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`T`](invoke.md#T)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.4/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L2125)

Set the property to the result of the given [Provider](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html), evaluated lazily.

`operator fun <T> `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`T`](invoke.md#T)`>.invoke(call: () -> `[`T`](invoke.md#T)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.4/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L2130)

Set the property to the result of the given closure, evaluated lazily.

