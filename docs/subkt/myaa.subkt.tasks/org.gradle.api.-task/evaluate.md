[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [org.gradle.api.Task](index.md) / [evaluate](./evaluate.md)

# evaluate

`fun `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.evaluate(expression: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>!>!` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.8/src/main/kotlin/myaa/subkt/tasks/plugin.kt#L224)

Evaluates a string using [Velocity](https://velocity.apache.org/engine/2.2/user-guide.html),
splits it on `|`, and processes it with [glob](../org.gradle.api.-project/glob.md).

This function is run in a task context, meaning that task-specific values such as
[entry](entry.md), [currentTask](current-task.md) and [isBatch](is-batch.md) are available.

``` kotlin
// from task mux.03
evaluate("\$currentTask - \$episode - \$merge.out.get()")
// Output: mux - 03 - build/merge.03.ass
```

### Parameters

`expression` - The expression to evaluate.