[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Chapters](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`Chapters()`

Generates a chapter file from an ASS file in the same way as Significance.
The provided ASS file will be searched for lines where the field
specified by [field](field.md) contains the value specified by [chapterMarker](chapter-marker.md),
and for each such line a chapter will be generated using the start time
as the time, and the value of the field specified by [chapterName](chapter-name.md)
as the chapter name.
A predefined task instance can be accessed through [Subs.chapters](../chapters.md).

Example usage:

```
chapters {
    from("chapter_file.ass")
    chapterName(EventLineAccessor.EFFECT)
    chapterMarker("chapter")
    generateIntro(false)
    out("chapters.txt")
}
```

