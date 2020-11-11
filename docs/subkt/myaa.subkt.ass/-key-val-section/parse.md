[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [KeyValSection](index.md) / [parse](./parse.md)

# parse

`open fun parse(data: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`KeyValLine`](../-key-val-line/index.md)`>, extraData: `[`ExtraData`](../-extra-data.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.12/src/main/kotlin/myaa/subkt/ass/parser.kt#L672)

Overrides [Section.parse](../-section/parse.md)

Deserializes a list of lines in [KeyValLine](../-key-val-line/index.md) format and adds
them to this section.

### Parameters

`extraData` - The extradata read from the input file.
Only affects [EventSection](../-event-section/index.md).