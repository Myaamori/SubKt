[subkt](../../../index.md) / [myaa.subkt.tasks](../../index.md) / [ValueClosure](../index.md) / [ClosureContext](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`ClosureContext()`

[AbstractContext](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html) implementation for getting properties in a [ValueClosure](../index.md) context.

Will look for variables in the following places:

1. The properties of the associated [ValueClosure](../index.md)
2. The loaded [SubProperties](../../-sub-properties/index.md) properties, searched using the current entry and release
3. All tasks in the current project
