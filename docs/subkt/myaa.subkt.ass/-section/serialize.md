[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [Section](index.md) / [serialize](./serialize.md)

# serialize

`fun serialize(includeExtraData: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true): `[`Sequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.19/src/main/kotlin/myaa/subkt/ass/parser.kt#L616)

Returns a string sequence representing the serialization (textual representation)
of this section, *without* newlines.

### Parameters

`includeExtraData` - Whether to include an extradata section.
Only applicable to [EventSection](../-event-section/index.md).