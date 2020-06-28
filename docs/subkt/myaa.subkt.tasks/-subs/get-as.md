[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Subs](index.md) / [getAs](./get-as.md)

# getAs

`inline fun <reified T> getAs(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", context: `[`AbstractContext`](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html)`? = null): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`T`](get-as.md#T)`>!` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.4/src/main/kotlin/myaa/subkt/tasks/plugin.kt#L551)

Searches for the given property in the [Subs](index.md) object's [SubProperties](../-sub-properties/index.md) instance,
evaluates its value using [evaluate](evaluate.md), and returns a single string, cast to
the given type using [String.asType](../kotlin.-string/as-type.md), assuming that the resulting list
contains only one element.

This function is run outside of a task context, using only [release](release.md) for lookup
unless an entry is manually specified.

``` kotlin
// property file: TV.*.value=10
getAs<Int>("value")
// Output: 10 (type: Int)
```

### Parameters

`propertyName` - The property to find.

`entry` - Optional manually specified entry for property lookup.