[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Subs](index.md) / [getRaw](./get-raw.md)

# getRaw

`fun getRaw(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = ""): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.7/src/main/kotlin/myaa/subkt/tasks/plugin.kt#L501)

Searches for the given property in the [Subs](index.md) object's [SubProperties](../-sub-properties/index.md) instance,
and returns the raw string.
Raises an error if not found.

This function is run outside of a task context, using only [release](release.md) for lookup
unless an entry is manually specified.

``` kotlin
// property file: TV.*.value=hello{1..3}|test|$episode
getRaw("value")
// Output: hello{1..3}|test|$episode
```

### Parameters

`propertyName` - The property to find.

`entry` - Optional manually specified entry for property lookup.