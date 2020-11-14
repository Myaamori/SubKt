[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [EventLine](./index.md)

# EventLine

`class EventLine : `[`MapLine`](../-map-line/index.md)`<`[`EventLine`](./index.md)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.13/src/main/kotlin/myaa/subkt/ass/parser.kt#L453)

A line in an [EventSection](../-event-section/index.md).
Its associated accessor is [EventLineAccessor](../-event-line-accessor/index.md).

See [MapLine](../-map-line/index.md) for a more in-depth description.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `EventLine(layer: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, start: `[`Duration`](https://docs.oracle.com/javase/9/docs/api/java/time/Duration.html)` = Duration.ZERO, end: `[`Duration`](https://docs.oracle.com/javase/9/docs/api/java/time/Duration.html)` = Duration.ZERO, style: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "Default", actor: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", marginL: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, marginR: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, marginV: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, effect: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", comment: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`? = null, type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "Dialogue", extraData: `[`ExtraData`](../-extra-data.md)`? = null)`<br>A line in an [EventSection](../-event-section/index.md). Its associated accessor is [EventLineAccessor](../-event-line-accessor/index.md). |

### Properties

| Name | Summary |
|---|---|
| [actor](actor.md) | `var actor: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [comment](comment.md) | `var comment: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [effect](effect.md) | `var effect: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [end](end.md) | `var end: `[`Duration`](https://docs.oracle.com/javase/9/docs/api/java/time/Duration.html) |
| [extraData](extra-data.md) | `val extraData: `[`MutableMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [layer](layer.md) | `var layer: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [marginL](margin-l.md) | `var marginL: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [marginR](margin-r.md) | `var marginR: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [marginV](margin-v.md) | `var marginV: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [start](start.md) | `var start: `[`Duration`](https://docs.oracle.com/javase/9/docs/api/java/time/Duration.html) |
| [style](style.md) | `var style: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [text](text.md) | `var text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [type](type.md) | `val type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The type of the line. |

### Inherited Properties

| Name | Summary |
|---|---|
| [entries](../-map-line/entries.md) | `open val entries: `[`MutableSet`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-set/index.html)`<`[`MutableEntry`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/-mutable-entry/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>>` |
| [keys](../-map-line/keys.md) | `open val keys: `[`MutableSet`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-set/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [size](../-map-line/size.md) | `open val size: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [values](../-map-line/values.md) | `open val values: `[`MutableCollection`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-collection/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>` |

### Inherited Functions

| Name | Summary |
|---|---|
| [clear](../-map-line/clear.md) | `open fun clear(): `[`Nothing`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-nothing/index.html) |
| [containsKey](../-map-line/contains-key.md) | `open fun containsKey(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [containsValue](../-map-line/contains-value.md) | `open fun containsValue(value: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [get](../-map-line/get.md) | `open fun get(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?``fun <T> get(key: `[`LineAccessor`](../-line-accessor/index.md)`<`[`T`](../-map-line/get.md#T)`, `[`L`](../-map-line/index.md#L)`>): `[`T`](../-map-line/get.md#T)<br>Get a field in a type-safe way using a [LineAccessor](../-line-accessor/index.md). |
| [isEmpty](../-map-line/is-empty.md) | `open fun isEmpty(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [put](../-map-line/put.md) | `open fun put(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?``fun <T : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`> put(key: `[`LineAccessor`](../-line-accessor/index.md)`<`[`T`](../-map-line/put.md#T)`, `[`L`](../-map-line/index.md#L)`>, value: `[`T`](../-map-line/put.md#T)`): `[`T`](../-map-line/put.md#T)`?`<br>Write to a field in a type-safe way using a [LineAccessor](../-line-accessor/index.md). |
| [putAll](../-map-line/put-all.md) | `open fun putAll(from: `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<out `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [putString](../-map-line/put-string.md) | `fun putString(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?`<br>Writes the given value to the field given by the key, automatically deserializing the value if needed. |
| [remove](../-map-line/remove.md) | `open fun remove(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Nothing`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-nothing/index.html) |

### Companion Object Properties

| Name | Summary |
|---|---|
| [extraDataPattern](extra-data-pattern.md) | `val extraDataPattern: `[`Regex`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-regex/index.html) |
