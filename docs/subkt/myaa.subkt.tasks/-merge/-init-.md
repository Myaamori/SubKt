[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Merge](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`Merge()`

Task to merge multiple ASS files into one. A predefined task instance can be
accessed through [Subs.merge](../merge.md).

``` kotlin
merge {
    from("dialogue.ass") {
        incrementLayer(10)
    }
    from("OP.ass") {
        syncSourceLine("sync", EventLineAccessor.ACTOR)
        syncTargetTime("0:10:24.66".assTime)
    }
    from(glob("typesetting-*.ass"))
    onScriptInfoConflict(ErrorMode.FAIL)
    namespaceStyles(true)
}
```

