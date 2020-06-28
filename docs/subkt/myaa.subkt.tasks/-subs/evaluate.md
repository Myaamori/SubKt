[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Subs](index.md) / [evaluate](./evaluate.md)

# evaluate

`fun evaluate(expression: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", context: `[`AbstractContext`](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html)`? = null): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>!>!` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.4/src/main/kotlin/myaa/subkt/tasks/plugin.kt#L447)

Evaluates a string using [Velocity](https://velocity.apache.org/engine/2.2/user-guide.html),
splits it on `|`, and processes it with [glob](../glob.md).

This function is run outside of a task context, meaning that task-specific values such as
[entry](evaluate.md#myaa.subkt.tasks.Subs$evaluate(kotlin.String, kotlin.String, org.apache.velocity.context.AbstractContext)/entry), [currentTask](../org.gradle.api.-task/current-task.md) and [isBatch](is-batch.md) are *not* available.

``` kotlin
evaluate("\$release - \$episode - \$merge.out.get()")
// Output: TV - $episode - $merge.out.get()
```

### Parameters

`expression` - The expression to evaluate.

`entry` - Optional manually specified entry for property lookup.