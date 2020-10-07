[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Mux](index.md) / [deterministic](./deterministic.md)

# deterministic

`val deterministic: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.10/src/main/kotlin/myaa/subkt/tasks/muxtask.kt#L576)

If true, a fixed value will be used as the RNG seed for generating UIDs,
and mkvmerge will not write the current date and time to the output file.
This means that for the same input files, flags and mkvmerge version,
the output file will always be byte identical.
Thus, even if you have to rerun tasks for whatever reason, you know that
the same CRC32 will be generated unless the input files have changed.

**Getter**

If true, a fixed value will be used as the RNG seed for generating UIDs,
and mkvmerge will not write the current date and time to the output file.
This means that for the same input files, flags and mkvmerge version,
the output file will always be byte identical.
Thus, even if you have to rerun tasks for whatever reason, you know that
the same CRC32 will be generated unless the input files have changed.

