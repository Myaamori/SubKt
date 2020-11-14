[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [ASSSerializer](./index.md)

# ASSSerializer

`interface ASSSerializer<T>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.13/src/main/kotlin/myaa/subkt/ass/parser.kt#L702)

### Functions

| Name | Summary |
|---|---|
| [deserialize](deserialize.md) | `abstract fun deserialize(s: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`T`](index.md#T) |
| [serialize](serialize.md) | `abstract fun serialize(a: `[`T`](index.md#T)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [CollisionsSerializer](../-collisions-serializer/index.md) | `class CollisionsSerializer : `[`ASSSerializer`](./index.md)`<`[`Collisions`](../-collisions/index.md)`>` |
| [ColorMatrixSerializer](../-color-matrix-serializer/index.md) | `class ColorMatrixSerializer : `[`ASSSerializer`](./index.md)`<`[`ColorMatrix`](../-color-matrix/index.md)`>` |
| [ScaledBorderAndShadowSerializer](../-scaled-border-and-shadow-serializer/index.md) | `class ScaledBorderAndShadowSerializer : `[`ASSSerializer`](./index.md)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [TimerSerializer](../-timer-serializer/index.md) | `class TimerSerializer : `[`ASSSerializer`](./index.md)`<`[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`>` |
| [WrapStyleSerializer](../-wrap-style-serializer/index.md) | `class WrapStyleSerializer : `[`ASSSerializer`](./index.md)`<`[`WrapStyle`](../-wrap-style/index.md)`>` |
