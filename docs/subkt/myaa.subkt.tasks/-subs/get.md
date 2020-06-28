[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Subs](index.md) / [get](./get.md)

# get

`fun get(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", context: `[`AbstractContext`](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html)`? = null): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!>!` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.4/src/main/kotlin/myaa/subkt/tasks/plugin.kt#L535)

Searches for the given property in the [Subs](index.md) object's [SubProperties](../-sub-properties/index.md) instance,
evaluates its value using [evaluate](evaluate.md), and returns a single string, assuming
that the resulting list contains only one element.

This function is run outside of a task context, using only [release](release.md) for lookup
unless an entry is manually specified.

``` kotlin
// property file: TV.*.value=$release/$episode/show_${episode}.ass
get("value")
// Output: TV/$episode/show_${episode}.ass
```

### Parameters

`propertyName` - The property to find.

`entry` - Optional manually specified entry for property lookup.