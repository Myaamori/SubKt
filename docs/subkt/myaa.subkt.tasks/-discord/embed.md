[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Discord](index.md) / [embed](./embed.md)

# embed

`fun embed(action: `[`Discord.Embed`](-embed/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.8/src/main/kotlin/myaa/subkt/tasks/discordtask.kt#L457)

Add an embed to this message (up to 10 allowed).
At least one of [embed](./embed.md), [content](content.md) or [attachment](attachment.md) is required.

### Parameters

`action` - A closure acting on an [Embed](-embed/index.md) object.