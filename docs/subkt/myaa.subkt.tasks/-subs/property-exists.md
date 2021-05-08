[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Subs](index.md) / [propertyExists](./property-exists.md)

# propertyExists

`fun propertyExists(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = ""): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.19/src/main/kotlin/myaa/subkt/tasks/plugin.kt#L584)

Returns true if the given property exists in the [Subs](index.md) object's [SubProperties](../-sub-properties/index.md) instance
for the given context.

This function is run outside of a task context, using only [release](release.md) for lookup
unless an entry is manually specified.

``` kotlin
merge {
    from(get("dialogue"))

    // only include OP if defined for the current episode
    if (propertyExists("op")) {
        from(get("op"))
    }
}
```

### Parameters

`propertyName` - The property to find.

`entry` - Optional manually specified entry for property lookup.