[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [EventLineAccessor](./index.md)

# EventLineAccessor

`sealed class EventLineAccessor<T> : `[`LineAccessor`](../-line-accessor/index.md)`<`[`T`](index.md#T)`, `[`EventLine`](../-event-line/index.md)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.9/src/main/kotlin/myaa/subkt/ass/parser.kt#L430)

A type-safe accessor for [EventLine](../-event-line/index.md).

See [MapLine](../-map-line/index.md) for a more in-depth description.

### Types

| Name | Summary |
|---|---|
| [ACTOR](-a-c-t-o-r.md) | `object ACTOR : `[`EventLineAccessor`](./index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [EFFECT](-e-f-f-e-c-t.md) | `object EFFECT : `[`EventLineAccessor`](./index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [END](-e-n-d.md) | `object END : `[`EventLineAccessor`](./index.md)`<`[`Duration`](https://docs.oracle.com/javase/9/docs/api/java/time/Duration.html)`>` |
| [LAYER](-l-a-y-e-r.md) | `object LAYER : `[`EventLineAccessor`](./index.md)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [MARGIN_L](-m-a-r-g-i-n_-l.md) | `object MARGIN_L : `[`EventLineAccessor`](./index.md)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [MARGIN_R](-m-a-r-g-i-n_-r.md) | `object MARGIN_R : `[`EventLineAccessor`](./index.md)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [MARGIN_V](-m-a-r-g-i-n_-v.md) | `object MARGIN_V : `[`EventLineAccessor`](./index.md)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [START](-s-t-a-r-t.md) | `object START : `[`EventLineAccessor`](./index.md)`<`[`Duration`](https://docs.oracle.com/javase/9/docs/api/java/time/Duration.html)`>` |
| [STYLE](-s-t-y-l-e.md) | `object STYLE : `[`EventLineAccessor`](./index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [TEXT](-t-e-x-t.md) | `object TEXT : `[`EventLineAccessor`](./index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |

### Inherited Properties

| Name | Summary |
|---|---|
| [field](../-line-accessor/field.md) | `val field: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Functions

| Name | Summary |
|---|---|
| [toString](to-string.md) | `open fun toString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [ACTOR](-a-c-t-o-r.md) | `object ACTOR : `[`EventLineAccessor`](./index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [EFFECT](-e-f-f-e-c-t.md) | `object EFFECT : `[`EventLineAccessor`](./index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [END](-e-n-d.md) | `object END : `[`EventLineAccessor`](./index.md)`<`[`Duration`](https://docs.oracle.com/javase/9/docs/api/java/time/Duration.html)`>` |
| [LAYER](-l-a-y-e-r.md) | `object LAYER : `[`EventLineAccessor`](./index.md)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [MARGIN_L](-m-a-r-g-i-n_-l.md) | `object MARGIN_L : `[`EventLineAccessor`](./index.md)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [MARGIN_R](-m-a-r-g-i-n_-r.md) | `object MARGIN_R : `[`EventLineAccessor`](./index.md)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [MARGIN_V](-m-a-r-g-i-n_-v.md) | `object MARGIN_V : `[`EventLineAccessor`](./index.md)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [START](-s-t-a-r-t.md) | `object START : `[`EventLineAccessor`](./index.md)`<`[`Duration`](https://docs.oracle.com/javase/9/docs/api/java/time/Duration.html)`>` |
| [STYLE](-s-t-y-l-e.md) | `object STYLE : `[`EventLineAccessor`](./index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [TEXT](-t-e-x-t.md) | `object TEXT : `[`EventLineAccessor`](./index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
