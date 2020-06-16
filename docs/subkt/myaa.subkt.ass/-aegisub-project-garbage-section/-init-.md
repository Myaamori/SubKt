[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [AegisubProjectGarbageSection](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`AegisubProjectGarbageSection(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)`

Represents an Aegisub project garbage section.

Example usage:

```
val projectGarbageSection = assFile.projectGarbage
println(projectGarbageSection.videoFile)
println(projectGarbageSection.values["Video File"]) // equivalent to the above
```

### Parameters

`name` - The name of the section, usually `Aegisub Project Garbage`.