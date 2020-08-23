[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Torrent](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`Torrent()`

Task to create a torrent file from a set of files.
A predefined task instance can be accessed through [Subs.torrent](../torrent.md).

Note that if more than one file is added to the torrent,
a root directory must be specified using the [into](https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/bundling/AbstractArchiveTask.html#into(java.lang.Object)) function.

``` kotlin
torrent {
    trackers("http://tracker.example.com:7777/announce",
             "udp://tracker.example.info:1337/announce")
    from(mux.batchItems())
    // copy mkv files in the 01/extra directory into a "bonus" directory
    from("01/extra") {
        include("*.mkv")
        into("bonus")
    }
    // root directory of torrent
    into("My Show - 01")
    out("My Show - 01.torrent")
}
```

