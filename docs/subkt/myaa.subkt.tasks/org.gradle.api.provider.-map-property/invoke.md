[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [org.gradle.api.provider.MapProperty](index.md) / [invoke](./invoke.md)

# invoke

`inline operator fun <K, reified V> `[`MapProperty`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/MapProperty.html)`<`[`K`](invoke.md#K)`, `[`V`](invoke.md#V)`>.invoke(vararg pairs: `[`Pair`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)`<`[`K`](invoke.md#K)`, `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.12/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L2197)

Adds all specified key/value pairs to the map property.

`operator fun <K, V> `[`MapProperty`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/MapProperty.html)`<`[`K`](invoke.md#K)`, `[`V`](invoke.md#V)`>.invoke(map: `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<`[`K`](invoke.md#K)`, `[`V`](invoke.md#V)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.12/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L2208)

Adds all items in the specified map to the map property.

`operator fun <K, V> `[`MapProperty`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/MapProperty.html)`<`[`K`](invoke.md#K)`, `[`V`](invoke.md#V)`>.invoke(map: `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<out `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<`[`K`](invoke.md#K)`, `[`V`](invoke.md#V)`>>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.12/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L2213)

Adds the items of the output of the given [Provider](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html) to the map property, evaluated lazily.

`operator fun <K, V> `[`MapProperty`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/MapProperty.html)`<`[`K`](invoke.md#K)`, `[`V`](invoke.md#V)`>.invoke(call: () -> `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<`[`K`](invoke.md#K)`, `[`V`](invoke.md#V)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.12/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L2218)

Adds the items of the output of the given closure to the map property, evaluated lazily.

