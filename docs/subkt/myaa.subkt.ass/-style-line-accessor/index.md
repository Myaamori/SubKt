[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [StyleLineAccessor](./index.md)

# StyleLineAccessor

`sealed class StyleLineAccessor<T> : `[`LineAccessor`](../-line-accessor/index.md)`<`[`T`](index.md#T)`, `[`StyleLine`](../-style-line/index.md)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.7/src/main/kotlin/myaa/subkt/ass/parser.kt#L497)

A type-safe accessor for [StyleLine](../-style-line/index.md).

See [MapLine](../-map-line/index.md) for a more in-depth description.

### Types

| Name | Summary |
|---|---|
| [ALIGNMENT](-a-l-i-g-n-m-e-n-t.md) | `object ALIGNMENT : `[`StyleLineAccessor`](./index.md)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [BOLD](-b-o-l-d.md) | `object BOLD : `[`StyleLineAccessor`](./index.md)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [BORDER_STYLE](-b-o-r-d-e-r_-s-t-y-l-e.md) | `object BORDER_STYLE : `[`StyleLineAccessor`](./index.md)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [ENCODING](-e-n-c-o-d-i-n-g.md) | `object ENCODING : `[`StyleLineAccessor`](./index.md)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [FONT_NAME](-f-o-n-t_-n-a-m-e.md) | `object FONT_NAME : `[`StyleLineAccessor`](./index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [ITALIC](-i-t-a-l-i-c.md) | `object ITALIC : `[`StyleLineAccessor`](./index.md)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [MARGIN_L](-m-a-r-g-i-n_-l.md) | `object MARGIN_L : `[`StyleLineAccessor`](./index.md)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [MARGIN_R](-m-a-r-g-i-n_-r.md) | `object MARGIN_R : `[`StyleLineAccessor`](./index.md)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [MARGIN_V](-m-a-r-g-i-n_-v.md) | `object MARGIN_V : `[`StyleLineAccessor`](./index.md)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [NAME](-n-a-m-e.md) | `object NAME : `[`StyleLineAccessor`](./index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [OUTLINE](-o-u-t-l-i-n-e.md) | `object OUTLINE : `[`StyleLineAccessor`](./index.md)`<`[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`>` |
| [OUTLINE_COLOR](-o-u-t-l-i-n-e_-c-o-l-o-r.md) | `object OUTLINE_COLOR : `[`StyleLineAccessor`](./index.md)`<`[`Color`](https://docs.oracle.com/javase/9/docs/api/java/awt/Color.html)`>` |
| [PRIMARY_COLOR](-p-r-i-m-a-r-y_-c-o-l-o-r.md) | `object PRIMARY_COLOR : `[`StyleLineAccessor`](./index.md)`<`[`Color`](https://docs.oracle.com/javase/9/docs/api/java/awt/Color.html)`>` |
| [ROTATION](-r-o-t-a-t-i-o-n.md) | `object ROTATION : `[`StyleLineAccessor`](./index.md)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [SCALE_X](-s-c-a-l-e_-x.md) | `object SCALE_X : `[`StyleLineAccessor`](./index.md)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [SCALE_Y](-s-c-a-l-e_-y.md) | `object SCALE_Y : `[`StyleLineAccessor`](./index.md)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [SECONDARY_COLOR](-s-e-c-o-n-d-a-r-y_-c-o-l-o-r.md) | `object SECONDARY_COLOR : `[`StyleLineAccessor`](./index.md)`<`[`Color`](https://docs.oracle.com/javase/9/docs/api/java/awt/Color.html)`>` |
| [SHADOW](-s-h-a-d-o-w.md) | `object SHADOW : `[`StyleLineAccessor`](./index.md)`<`[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`>` |
| [SHADOW_COLOR](-s-h-a-d-o-w_-c-o-l-o-r.md) | `object SHADOW_COLOR : `[`StyleLineAccessor`](./index.md)`<`[`Color`](https://docs.oracle.com/javase/9/docs/api/java/awt/Color.html)`>` |
| [SPACING](-s-p-a-c-i-n-g.md) | `object SPACING : `[`StyleLineAccessor`](./index.md)`<`[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`>` |
| [STRIKEOUT](-s-t-r-i-k-e-o-u-t.md) | `object STRIKEOUT : `[`StyleLineAccessor`](./index.md)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [UNDERLINE](-u-n-d-e-r-l-i-n-e.md) | `object UNDERLINE : `[`StyleLineAccessor`](./index.md)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |

### Inherited Properties

| Name | Summary |
|---|---|
| [field](../-line-accessor/field.md) | `val field: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [ALIGNMENT](-a-l-i-g-n-m-e-n-t.md) | `object ALIGNMENT : `[`StyleLineAccessor`](./index.md)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [BOLD](-b-o-l-d.md) | `object BOLD : `[`StyleLineAccessor`](./index.md)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [BORDER_STYLE](-b-o-r-d-e-r_-s-t-y-l-e.md) | `object BORDER_STYLE : `[`StyleLineAccessor`](./index.md)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [ENCODING](-e-n-c-o-d-i-n-g.md) | `object ENCODING : `[`StyleLineAccessor`](./index.md)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [FONT_NAME](-f-o-n-t_-n-a-m-e.md) | `object FONT_NAME : `[`StyleLineAccessor`](./index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [ITALIC](-i-t-a-l-i-c.md) | `object ITALIC : `[`StyleLineAccessor`](./index.md)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [MARGIN_L](-m-a-r-g-i-n_-l.md) | `object MARGIN_L : `[`StyleLineAccessor`](./index.md)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [MARGIN_R](-m-a-r-g-i-n_-r.md) | `object MARGIN_R : `[`StyleLineAccessor`](./index.md)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [MARGIN_V](-m-a-r-g-i-n_-v.md) | `object MARGIN_V : `[`StyleLineAccessor`](./index.md)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [NAME](-n-a-m-e.md) | `object NAME : `[`StyleLineAccessor`](./index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [OUTLINE](-o-u-t-l-i-n-e.md) | `object OUTLINE : `[`StyleLineAccessor`](./index.md)`<`[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`>` |
| [OUTLINE_COLOR](-o-u-t-l-i-n-e_-c-o-l-o-r.md) | `object OUTLINE_COLOR : `[`StyleLineAccessor`](./index.md)`<`[`Color`](https://docs.oracle.com/javase/9/docs/api/java/awt/Color.html)`>` |
| [PRIMARY_COLOR](-p-r-i-m-a-r-y_-c-o-l-o-r.md) | `object PRIMARY_COLOR : `[`StyleLineAccessor`](./index.md)`<`[`Color`](https://docs.oracle.com/javase/9/docs/api/java/awt/Color.html)`>` |
| [ROTATION](-r-o-t-a-t-i-o-n.md) | `object ROTATION : `[`StyleLineAccessor`](./index.md)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [SCALE_X](-s-c-a-l-e_-x.md) | `object SCALE_X : `[`StyleLineAccessor`](./index.md)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [SCALE_Y](-s-c-a-l-e_-y.md) | `object SCALE_Y : `[`StyleLineAccessor`](./index.md)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [SECONDARY_COLOR](-s-e-c-o-n-d-a-r-y_-c-o-l-o-r.md) | `object SECONDARY_COLOR : `[`StyleLineAccessor`](./index.md)`<`[`Color`](https://docs.oracle.com/javase/9/docs/api/java/awt/Color.html)`>` |
| [SHADOW](-s-h-a-d-o-w.md) | `object SHADOW : `[`StyleLineAccessor`](./index.md)`<`[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`>` |
| [SHADOW_COLOR](-s-h-a-d-o-w_-c-o-l-o-r.md) | `object SHADOW_COLOR : `[`StyleLineAccessor`](./index.md)`<`[`Color`](https://docs.oracle.com/javase/9/docs/api/java/awt/Color.html)`>` |
| [SPACING](-s-p-a-c-i-n-g.md) | `object SPACING : `[`StyleLineAccessor`](./index.md)`<`[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`>` |
| [STRIKEOUT](-s-t-r-i-k-e-o-u-t.md) | `object STRIKEOUT : `[`StyleLineAccessor`](./index.md)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [UNDERLINE](-u-n-d-e-r-l-i-n-e.md) | `object UNDERLINE : `[`StyleLineAccessor`](./index.md)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
