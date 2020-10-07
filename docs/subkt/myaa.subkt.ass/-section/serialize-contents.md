[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [Section](index.md) / [serializeContents](./serialize-contents.md)

# serializeContents

`abstract fun serializeContents(includeExtraData: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Sequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.10/src/main/kotlin/myaa/subkt/ass/parser.kt#L603)

Returns a string sequence representing the serialization of the
contents of this section, without the section name.

### Parameters

`includeExtraData` - Whether to include an extradata section.
Only applicable to [EventSection](../-event-section/index.md).