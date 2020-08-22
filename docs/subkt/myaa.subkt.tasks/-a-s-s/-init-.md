[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [ASS](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`ASS()`

Generic task for modifying ASS files. See [ASSFile](../../myaa.subkt.ass/-a-s-s-file/index.md) and related classes
for more information. The modified file is written to a new file.

``` kotlin
"removeLines"<ASS> {
     from("dialogue.ass")

     ass {
         // remove lines with a start time of greater than five minutes
         events.lines.removeIf { line ->
             line.start > Duration.ofMinutes(5)
         }
     }
 }
```

