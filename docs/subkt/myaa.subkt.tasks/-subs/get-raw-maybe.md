[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Subs](index.md) / [getRawMaybe](./get-raw-maybe.md)

# getRawMaybe

`fun getRawMaybe(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = ""): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` [(source)](https://github.com/Myaamori/SubKt/blob/master/src/main/kotlin/myaa/subkt/tasks/plugin.kt#L476)

Searches for the given property in the [Subs](index.md) object's [SubProperties](../-sub-properties/index.md) instance,
and returns the raw string, possibly null.

This function is run outside of a task context, using only [release](release.md) for lookup
unless an entry is manually specified.

### Parameters

`propertyName` - The property to find.

`entry` - Optional manually specified entry for property lookup.