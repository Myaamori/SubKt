[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [ItemGroupContext](index.md) / [value](./value.md)

# value

`fun <T> value(action: `[`ValueClosure`](../-value-closure/index.md)`<*>.() -> `[`T`](value.md#T)`): `[`ValueGroup`](../-value-group/index.md)`<`[`T`](value.md#T)`>`

Creates a [ValueGroup](../-value-group/index.md), evaluating the given closure immediately for each entry
in the given context.

### Parameters

`T` - The type of the item contained in the [ValueGroup](../-value-group/index.md).

`action` - A closure operating on a [ValueClosure](../-value-closure/index.md). Called once for
each entry in the current context.