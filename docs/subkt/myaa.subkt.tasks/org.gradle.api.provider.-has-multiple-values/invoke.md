[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [org.gradle.api.provider.HasMultipleValues](index.md) / [invoke](./invoke.md)

# invoke

`operator fun <T> `[`HasMultipleValues`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/HasMultipleValues.html)`<`[`T`](invoke.md#T)`>.invoke(vararg items: `[`T`](invoke.md#T)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/master/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L2047)
`operator fun <T> `[`HasMultipleValues`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/HasMultipleValues.html)`<`[`T`](invoke.md#T)`>.invoke(items: `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)`<`[`T`](invoke.md#T)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/master/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L2052)

Adds all specified items to the list property.

`operator fun <T> `[`HasMultipleValues`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/HasMultipleValues.html)`<`[`T`](invoke.md#T)`>.invoke(items: `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<out `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)`<`[`T`](invoke.md#T)`>>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/master/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L2057)

Adds the output of the given [Provider](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html) to the list property, evaluated lazily.

`operator fun <T> `[`HasMultipleValues`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/HasMultipleValues.html)`<`[`T`](invoke.md#T)`>.invoke(call: () -> `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)`<`[`T`](invoke.md#T)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/master/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L2062)

Adds the output of the given closure to the list property, evaluated lazily.

