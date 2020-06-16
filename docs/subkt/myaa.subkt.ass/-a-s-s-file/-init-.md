[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [ASSFile](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`ASSFile(file: `[`File`](https://docs.oracle.com/javase/9/docs/api/java/io/File.html)`? = null)`

Represents an ASS file. If provided, will parse the given file.

Example usage:

```
val assFile = ASSFile(File("my_script.ass"))
println("Script resolution: ${assFile.scriptInfo.playResX}x${assFile.scriptInfo.playResY}")

// increase the layer of every event line by 10
assFile.events.lines.forEach { line ->
    line.layer += 10
}
```

### Parameters

`file` - The file to parse.