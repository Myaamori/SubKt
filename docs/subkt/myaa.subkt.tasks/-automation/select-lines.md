[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Automation](index.md) / [selectLines](./select-lines.md)

# selectLines

`fun selectLines(f: `[`Automation.Selector`](-selector/index.md)`.(`[`ASSFile`](../../myaa.subkt.ass/-a-s-s-file/index.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.12/src/main/kotlin/myaa/subkt/tasks/asstasks.kt#L763)

Specify what lines to mark as selected and active.

The supplied function should call the [Selector.select](-selector/select.md) and [Selector.active](-selector/active.md)
functions to mark lines as selected/active.

### Parameters

`f` - A function operating on a [Selector](-selector/index.md) object that takes an [ASSFile](../../myaa.subkt.ass/-a-s-s-file/index.md) object.