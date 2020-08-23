[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [StyleLine](./index.md)

# StyleLine

`class StyleLine : `[`MapLine`](../-map-line/index.md)`<`[`StyleLine`](./index.md)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.8/src/main/kotlin/myaa/subkt/ass/parser.kt#L528)

A line in a [StyleSection](../-style-section/index.md).
Its associated accessor is [StyleLineAccessor](../-style-line-accessor/index.md).

See [MapLine](../-map-line/index.md) for a more in-depth description.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `StyleLine(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "Default", font: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "Arial", fontSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 20, primaryColor: `[`Color`](https://docs.oracle.com/javase/9/docs/api/java/awt/Color.html)` = Color.WHITE, secondaryColor: `[`Color`](https://docs.oracle.com/javase/9/docs/api/java/awt/Color.html)` = Color.RED, outlineColor: `[`Color`](https://docs.oracle.com/javase/9/docs/api/java/awt/Color.html)` = Color.BLACK, shadowColor: `[`Color`](https://docs.oracle.com/javase/9/docs/api/java/awt/Color.html)` = Color.BLACK, bold: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, italic: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, underline: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, strikeout: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, scaleX: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 100, scaleY: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 100, spacing: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)` = 0.0, rotation: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, borderStyle: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 1, outline: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)` = 0.0, shadow: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)` = 0.0, alignment: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 2, marginL: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 10, marginR: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 10, marginV: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 10, encoding: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 1, type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "Style")`<br>A line in a [StyleSection](../-style-section/index.md). Its associated accessor is [StyleLineAccessor](../-style-line-accessor/index.md). |

### Properties

| Name | Summary |
|---|---|
| [alignment](alignment.md) | `var alignment: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [bold](bold.md) | `var bold: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [borderStyle](border-style.md) | `var borderStyle: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [encoding](encoding.md) | `var encoding: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [font](font.md) | `var font: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [fontSize](font-size.md) | `var fontSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [italic](italic.md) | `var italic: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [marginL](margin-l.md) | `var marginL: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [marginR](margin-r.md) | `var marginR: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [marginV](margin-v.md) | `var marginV: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [name](name.md) | `var name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [outline](outline.md) | `var outline: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [outlineColor](outline-color.md) | `var outlineColor: `[`Color`](https://docs.oracle.com/javase/9/docs/api/java/awt/Color.html) |
| [primaryColor](primary-color.md) | `var primaryColor: `[`Color`](https://docs.oracle.com/javase/9/docs/api/java/awt/Color.html) |
| [rotation](rotation.md) | `var rotation: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [scaleX](scale-x.md) | `var scaleX: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [scaleY](scale-y.md) | `var scaleY: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [secondaryColor](secondary-color.md) | `var secondaryColor: `[`Color`](https://docs.oracle.com/javase/9/docs/api/java/awt/Color.html) |
| [shadow](shadow.md) | `var shadow: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [shadowColor](shadow-color.md) | `var shadowColor: `[`Color`](https://docs.oracle.com/javase/9/docs/api/java/awt/Color.html) |
| [spacing](spacing.md) | `var spacing: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [strikeout](strikeout.md) | `var strikeout: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [underline](underline.md) | `var underline: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

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
