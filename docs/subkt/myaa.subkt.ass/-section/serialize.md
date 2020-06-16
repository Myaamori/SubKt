[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [Section](index.md) / [serialize](./serialize.md)

# serialize

`fun serialize(includeExtraData: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true): `[`Sequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`

Returns a string sequence representing the serialization (textual representation)
of this section, *without* newlines.

### Parameters

`includeExtraData` - Whether to include an extradata section.
Only applicable to [EventSection](../-event-section/index.md).