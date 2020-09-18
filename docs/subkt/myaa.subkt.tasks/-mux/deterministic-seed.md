[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Mux](index.md) / [deterministicSeed](./deterministic-seed.md)

# deterministicSeed

`val deterministicSeed: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.9/src/main/kotlin/myaa/subkt/tasks/muxtask.kt#L588)

The seed to use for `--deterministic` -- see [deterministic](deterministic.md).
If not set, a hash based on the output filename will be used.

To avoid potential issues with e.g. segment UID clashes for ordered chapters,
you should make sure to use different seeds for different output files,
or alternatively specify segment UIDs manually.

**Getter**

The seed to use for `--deterministic` -- see [deterministic](deterministic.md).
If not set, a hash based on the output filename will be used.

To avoid potential issues with e.g. segment UID clashes for ordered chapters,
you should make sure to use different seeds for different output files,
or alternatively specify segment UIDs manually.

