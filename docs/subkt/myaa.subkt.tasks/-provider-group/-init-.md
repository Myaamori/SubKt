[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [ProviderGroup](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`ProviderGroup(subs: `[`Subs`](../-subs/index.md)`, action: `[`ValueClosure`](../-value-closure/index.md)`<*>.() -> `[`T`](index.md#T)`)`

[ItemGroup](../-item-group/index.md) that keeps track of providers for values of type [T](index.md#T).
A [Provider](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html) is generated for each entry, and the given closure
is evaluated when the provider's value is requested.

