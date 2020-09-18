[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Mux](index.md) / [globalOptions](./global-options.md)

# globalOptions

`val globalOptions: `[`ListProperty`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/ListProperty.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.9/src/main/kotlin/myaa/subkt/tasks/muxtask.kt#L644)

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

