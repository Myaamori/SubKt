[subkt](../../../index.md) / [myaa.subkt.tasks](../../index.md) / [Discord](../index.md) / [Embed](./index.md)

# Embed

`inner class Embed` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.11/src/main/kotlin/myaa/subkt/tasks/discordtask.kt#L244)

An embed capable of rendering rich text, images and the like.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Embed()`<br>An embed capable of rendering rich text, images and the like. |

### Properties

| Name | Summary |
|---|---|
| [author](author.md) | `var author: `[`Discord.Author`](../-author/index.md)`?`<br>The author of this embed. |
| [color](color.md) | `val color: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Color`](https://docs.oracle.com/javase/9/docs/api/java/awt/Color.html)`>`<br>The color of the embed. |
| [description](description.md) | `val description: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>The description of this embed. |
| [fields](fields.md) | `val fields: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Discord.Field`](../-field/index.md)`>`<br>The fields of this embed. |
| [footer](footer.md) | `var footer: `[`Discord.Footer`](../-footer/index.md)`?`<br>The footer of this embed. |
| [image](image.md) | `var image: `[`Discord.Image`](../-image/index.md)`?`<br>The image of this embed. |
| [provider](provider.md) | `var provider: `[`Discord.Provider`](../-provider/index.md)`?`<br>The provider of this embed. |
| [thumbnail](thumbnail.md) | `var thumbnail: `[`Discord.Thumbnail`](../-thumbnail/index.md)`?`<br>The thumbnail of this embed. |
| [timestamp](timestamp.md) | `val timestamp: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`ZonedDateTime`](https://docs.oracle.com/javase/9/docs/api/java/time/ZonedDateTime.html)`>`<br>Timestamp of embed content. |
| [title](title.md) | `val title: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>The title of this embed. |
| [url](url.md) | `val url: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>The URL of this embed. |
| [video](video.md) | `var video: `[`Discord.Video`](../-video/index.md)`?`<br>The video of this embed. |

### Functions

| Name | Summary |
|---|---|
| [author](author.md) | `fun author(action: `[`Discord.Author`](../-author/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set the author of this embed. |
| [field](field.md) | `fun field(action: `[`Discord.Field`](../-field/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Add a field to this embed. |
| [footer](footer.md) | `fun footer(action: `[`Discord.Footer`](../-footer/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set the footer of this embed. |
| [image](image.md) | `fun image(action: `[`Discord.Image`](../-image/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set the image of this embed. |
| [provider](provider.md) | `fun provider(action: `[`Discord.Provider`](../-provider/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set the provider of this embed. |
| [thumbnail](thumbnail.md) | `fun thumbnail(action: `[`Discord.Thumbnail`](../-thumbnail/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set the thumbnail of this embed. |
| [video](video.md) | `fun video(action: `[`Discord.Video`](../-video/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set the video of this embed. |
