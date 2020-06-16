[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [TaskContext](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`TaskContext(task: `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`)`

[AbstractContext](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html) implementation for getting properties in a task context.

Will look for variables in the following places:

1. The extra properties of the task
2. The loaded [SubProperties](../-sub-properties/index.md) properties, searched using the current entry and release
3. The current task, if the variable matches the name of the task
4. The dependencies of the current task
