[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Mux](index.md) / [globalOptions](./global-options.md)

# globalOptions

`val globalOptions: `[`ListProperty`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/ListProperty.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`

Any additional global mkvmerge options you wish to include.

``` kotlin
mux {
    globalOptions("--segmentinfo", "segmentinfo.xml")
}
```

**Getter**

Any additional global mkvmerge options you wish to include.

``` kotlin
mux {
    globalOptions("--segmentinfo", "segmentinfo.xml")
}
```

