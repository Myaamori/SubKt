[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Subs](index.md) / [getList](./get-list.md)

# getList

`fun getList(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", context: `[`AbstractContext`](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html)`? = null): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.10/src/main/kotlin/myaa/subkt/tasks/plugin.kt#L564)

Searches for the given property in the [Subs](index.md) object's [SubProperties](../-sub-properties/index.md) instance,
and evaluates its value using [evaluate](evaluate.md).

This function is run outside of a task context, using only [release](release.md) for lookup
unless an entry is manually specified.

``` kotlin
// property file: TV.*.value=hello{1..3}|test|$episode
getList("value")
// Output: [hello1, hello2, hello3, test, $episode]
```

### Parameters

`propertyName` - The property to find.

`entry` - Optional manually specified entry for property lookup.