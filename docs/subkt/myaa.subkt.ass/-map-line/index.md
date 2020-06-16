[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [MapLine](./index.md)

# MapLine

`abstract class MapLine<L : `[`MapLine`](./index.md)`<`[`L`](index.md#L)`>> : `[`Line`](../-line/index.md)`, `[`MutableMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>`

A line in a [FormatSection](../-format-section/index.md). The fields contained in this object can be
accessed in three ways:

1. Statically, using the properties defined in subtypes;
2. Unsafely as a map, using the ASS field names as keys;
3. Using type-safe accessors.

The following three assignments are equivalent:

```
eventLine.layer = 10
eventLine.put(EventLineAccessor.LAYER, 10)
eventLine["Layer"] = 10 // no compiler error if assigning wrong type
```

Further, consider these three lines:

```
val layer = eventLine.layer // inferred type: Int
val layer = eventLine.get(EventLineAccessor.LAYER) // inferred type: Int
val layer = eventLine["Layer"] // inferred type: Any?; must be manually cast to Int
```

Accessors provide a way for functions to define a type-safe contract while
still allowing the user to specify what field to read from or write to.
E.g. a function that only operates on [EventLine](../-event-line/index.md) fields of type [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)
can be defined as follows:

```
fun doThing(line: EventLine, accessor: EventLineAccessor<T>) { /* ... */}
```

This is particularly useful for DSLs.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MapLine(type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)`<br>A line in a [FormatSection](../-format-section/index.md). The fields contained in this object can be accessed in three ways: |

### Properties

| Name | Summary |
|---|---|
| [entries](entries.md) | `open val entries: `[`MutableSet`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-set/index.html)`<`[`MutableEntry`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/-mutable-entry/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>>` |
| [keys](keys.md) | `open val keys: `[`MutableSet`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-set/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [size](size.md) | `open val size: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [values](values.md) | `open val values: `[`MutableCollection`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-collection/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>` |

### Inherited Properties

| Name | Summary |
|---|---|
| [type](../-line/type.md) | `open val type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The type of the line. |

### Functions

| Name | Summary |
|---|---|
| [clear](clear.md) | `open fun clear(): `[`Nothing`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-nothing/index.html) |
| [containsKey](contains-key.md) | `open fun containsKey(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [containsValue](contains-value.md) | `open fun containsValue(value: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [get](get.md) | `open fun get(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?``fun <T> get(key: `[`LineAccessor`](../-line-accessor/index.md)`<`[`T`](get.md#T)`, `[`L`](index.md#L)`>): `[`T`](get.md#T)<br>Get a field in a type-safe way using a [LineAccessor](../-line-accessor/index.md). |
| [isEmpty](is-empty.md) | `open fun isEmpty(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [put](put.md) | `open fun put(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?``fun <T : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`> put(key: `[`LineAccessor`](../-line-accessor/index.md)`<`[`T`](put.md#T)`, `[`L`](index.md#L)`>, value: `[`T`](put.md#T)`): `[`T`](put.md#T)`?`<br>Write to a field in a type-safe way using a [LineAccessor](../-line-accessor/index.md). |
| [putAll](put-all.md) | `open fun putAll(from: `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<out `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [putString](put-string.md) | `fun putString(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?`<br>Writes the given value to the field given by the key, automatically deserializing the value if needed. |
| [remove](remove.md) | `open fun remove(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Nothing`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-nothing/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [EventLine](../-event-line/index.md) | `class EventLine : `[`MapLine`](./index.md)`<`[`EventLine`](../-event-line/index.md)`>`<br>A line in an [EventSection](../-event-section/index.md). Its associated accessor is [EventLineAccessor](../-event-line-accessor/index.md). |
| [StyleLine](../-style-line/index.md) | `class StyleLine : `[`MapLine`](./index.md)`<`[`StyleLine`](../-style-line/index.md)`>`<br>A line in a [StyleSection](../-style-section/index.md). Its associated accessor is [StyleLineAccessor](../-style-line-accessor/index.md). |
