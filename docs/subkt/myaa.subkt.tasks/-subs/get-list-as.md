[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Subs](index.md) / [getListAs](./get-list-as.md)

# getListAs

`inline fun <reified T> getListAs(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", context: `[`AbstractContext`](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html)`? = null): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`T`](get-list-as.md#T)`>!>!` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.4/src/main/kotlin/myaa/subkt/tasks/plugin.kt#L518)

Searches for the given property in the [Subs](index.md) object's [SubProperties](../-sub-properties/index.md) instance,
evaluates its value using [evaluate](evaluate.md), and casts the list elements to the given type
using [String.asType](../kotlin.-string/as-type.md).

This function is run outside of a task context, using only [release](release.md) for lookup
unless an entry is manually specified.

``` kotlin
// property file: TV.*.values={1..5}
getListAs<Int>("values")
// Output: [1, 2, 3, 4, 5] (type: List<Int>)
```

### Parameters

`propertyName` - The property to find.

`entry` - Optional manually specified entry for property lookup.