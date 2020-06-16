[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Nyaa](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`Nyaa()`

Task for uploading a torrent file to nyaa.si.
A predefined task instance can be accessed through [Subs.nyaa](../nyaa.md).

``` kotlin
nyaa {
    from(torrent.item())
    username("username")
    password("password")
    category(NyaaCategories.ANIME_ENGLISH)
    torrentName("[Group] My Show - 01.mkv")
    torrentDescription("This is the *description*.")
    information("#channel @ irc.rizon.net")
    hidden(true)
}
```

