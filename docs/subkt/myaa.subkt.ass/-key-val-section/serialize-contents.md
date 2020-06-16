[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [KeyValSection](index.md) / [serializeContents](./serialize-contents.md)

# serializeContents

`open fun serializeContents(includeExtraData: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Sequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`

Overrides [Section.serializeContents](../-section/serialize-contents.md)

Returns a string sequence representing the serialization of the
contents of this section, without the section name.

### Parameters

`includeExtraData` - Whether to include an extradata section.
Only applicable to [EventSection](../-event-section/index.md).