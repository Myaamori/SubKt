[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [FormatSection](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`FormatSection(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, lineClass: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<`[`T`](index.md#T)`>)`

Represents a section containing a sequence of lines, starting with a `Format` line
specifying the order of the fields on each line.

### Parameters

`name` - The name of the section.

`lineClass` - A class object used for instantiating new lines of type [T](index.md#T).

`T` - The type of line allowed in this section.