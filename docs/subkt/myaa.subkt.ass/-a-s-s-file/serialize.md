[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [ASSFile](index.md) / [serialize](./serialize.md)

# serialize

`fun serialize(includeScriptInfo: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true, includeProjectGarbage: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true, includeStyles: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true, includeEvents: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true, includeExtraData: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true): `[`Sequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.11/src/main/kotlin/myaa/subkt/ass/parser.kt#L214)

Returns a sequence of serialized lines, each ending with a newline.
The sequence starts with the UTF-8 BOM, which should always be written first
to the ASS file.

The output must be saved using UTF-8 as the encoding.

### Parameters

`includeScriptInfo` - Whether to include the Script Info section.

`includeProjectGarbage` - Whether to include the Aegisub Project Garbage section.
Has no effect if the section is empty, in which case the section will not
be generated either way.

`includeStyles` - Whether to include the V4+ Styles section.

`includeEvents` - Whether to include the Events section.

`includeExtraData` - Whether to include the Aegisub Extradata section.
Has no effect if [includeEvents](serialize.md#myaa.subkt.ass.ASSFile$serialize(kotlin.Boolean, kotlin.Boolean, kotlin.Boolean, kotlin.Boolean, kotlin.Boolean)/includeEvents) is false, in which case the section will not
be generated either way.