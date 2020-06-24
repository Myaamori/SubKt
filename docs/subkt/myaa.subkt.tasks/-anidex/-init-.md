[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Anidex](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`Anidex()`

Task for uploading a torrent file to anidex.info.
A predefined task instance can be accessed through [Subs.anidex](../anidex.md).

``` kotlin
anidex {
    from(torrent.item())
    apiKey("your-key-goes-hre")
    category(AnidexCategories.ANIME_SUB)
    lang(AnidexLanguage.ENGLISH)
    torrentName("[Group] My Show - 01.mkv")
    torrentDescription("This is the *description*.")
    hidden(true)
}
```

