[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [LineAccessor](./index.md)

# LineAccessor

`sealed class LineAccessor<T, L : `[`MapLine`](../-map-line/index.md)`<`[`L`](index.md#L)`>> : `[`Serializable`](https://docs.oracle.com/javase/9/docs/api/java/io/Serializable.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.10/src/main/kotlin/myaa/subkt/ass/parser.kt#L266)

Provides type-safe parametric access into a [MapLine](../-map-line/index.md).

See [MapLine](../-map-line/index.md) for a more in-depth description.

### Properties

| Name | Summary |
|---|---|
| [field](field.md) | `val field: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [EventLineAccessor](../-event-line-accessor/index.md) | `sealed class EventLineAccessor<T> : `[`LineAccessor`](./index.md)`<`[`T`](../-event-line-accessor/index.md#T)`, `[`EventLine`](../-event-line/index.md)`>`<br>A type-safe accessor for [EventLine](../-event-line/index.md). |
| [StyleLineAccessor](../-style-line-accessor/index.md) | `sealed class StyleLineAccessor<T> : `[`LineAccessor`](./index.md)`<`[`T`](../-style-line-accessor/index.md#T)`, `[`StyleLine`](../-style-line/index.md)`>`<br>A type-safe accessor for [StyleLine](../-style-line/index.md). |
