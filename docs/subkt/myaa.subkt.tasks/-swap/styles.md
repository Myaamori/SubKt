[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Swap](index.md) / [styles](./styles.md)

# styles

`val styles: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.12/src/main/kotlin/myaa/subkt/tasks/asstasks.kt#L554)

Only lines with a style name matching the specified pattern will have their contents swapped.
Defaults to `Regex("Main|Default")`, meaning all styles that contain
either `Main` or `Default` somewhere in the name.

Can be a [Regex](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-regex/index.html) or a list of style names.

**Getter**

Only lines with a style name matching the specified pattern will have their contents swapped.
Defaults to `Regex("Main|Default")`, meaning all styles that contain
either `Main` or `Default` somewhere in the name.

Can be a [Regex](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-regex/index.html) or a list of style names.

