[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [ScriptInfoSection](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`ScriptInfoSection(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)`

Represents a script info section.

Example usage:

```
val scriptInfoSection = assFile.scriptInfo
scriptInfoSection.playResX = 1920
scriptInfoSection.values["PlayResX"] = 1920 // equivalent to the above
```

### Parameters

`name` - The name of the section, usually `Script Info`.