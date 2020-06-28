[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [ItemGroupContext](index.md) / [provider](./provider.md)

# provider

`fun <T> provider(action: `[`ValueClosure`](../-value-closure/index.md)`<*>.() -> `[`T`](provider.md#T)`): `[`ProviderGroup`](../-provider-group/index.md)`<`[`T`](provider.md#T)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.4/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L214)

Creates a [ProviderGroup](../-provider-group/index.md) that evaluates the given closure lazily, returning a
[Provider](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html) when an item is requested for a given entry.
The closure is evaluated by running [Provider.get](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html#get()) on the returned provider.

### Parameters

`T` - The type of the item contained in the [ProviderGroup](../-provider-group/index.md).

`action` - A closure operating on a [ValueClosure](../-value-closure/index.md).