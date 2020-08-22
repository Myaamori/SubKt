[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Discord](./index.md)

# Discord

`open class Discord : `[`DefaultTask`](https://docs.gradle.org/current/javadoc/org/gradle/api/DefaultTask.html)`, `[`SubTask`](../-sub-task/index.md) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.7/src/main/kotlin/myaa/subkt/tasks/discordtask.kt#L52)

Task for posting to a Discord webhook. Supports normal messages, embeds,
and uploading files. A predefined task instance can be accessed through
[Subs.discord](../discord.md).

``` kotlin
discord {
    webhook("https://discordapp.com/api/webhooks/...")
    username("Gradle")
    content("This is a message")
    attachment(project.file("attachment.txt"))

    embed {
        title("Embed title")
        description("Embed description")
        color(Color.RED)

        image {
            url("https://i.imgur.com/ecfjrQj.png")
            height(480)
        }
    }
}
```

### Types

| Name | Summary |
|---|---|
| [Author](-author/index.md) | `inner class Author` |
| [Embed](-embed/index.md) | `inner class Embed`<br>An embed capable of rendering rich text, images and the like. |
| [Field](-field/index.md) | `inner class Field` |
| [Footer](-footer/index.md) | `inner class Footer`<br>The footer of an embed. |
| [Image](-image/index.md) | `inner class Image`<br>The image of an embed. |
| [Provider](-provider/index.md) | `inner class Provider`<br>The provider of an embed. |
| [Thumbnail](-thumbnail/index.md) | `inner class Thumbnail`<br>The thumbnail of an embed. |
| [Video](-video/index.md) | `inner class Video`<br>The video of an embed. |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Discord()`<br>Task for posting to a Discord webhook. Supports normal messages, embeds, and uploading files. A predefined task instance can be accessed through [Subs.discord](../discord.md). |

### Properties

| Name | Summary |
|---|---|
| [attachment](attachment.md) | `val attachment: `[`RegularFileProperty`](https://docs.gradle.org/current/javadoc/org/gradle/api/file/RegularFileProperty.html)`!`<br>A file to send with the message. At least one of [attachment](attachment.md), [content](content.md) or [embed](embed.md) is required. |
| [avatarUrl](avatar-url.md) | `val avatarUrl: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>Override the default avatar of the webhook. |
| [content](content.md) | `val content: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>Message contents (up to 2000 characters). At least one of [content](content.md), [attachment](attachment.md) or [embed](embed.md) is required. |
| [embeds](embeds.md) | `val embeds: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Discord.Embed`](-embed/index.md)`>`<br>A list of [Embed](-embed/index.md) objects currently added to this message. |
| [tts](tts.md) | `val tts: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>`<br>`true` if this is a TTS message. |
| [username](username.md) | `val username: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>Override the default username of the webhook. |
| [webhook](webhook.md) | `val webhook: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>The webhook to post to (required). |

### Functions

| Name | Summary |
|---|---|
| [embed](embed.md) | `fun embed(action: `[`Discord.Embed`](-embed/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Add an embed to this message (up to 10 allowed). At least one of [embed](embed.md), [content](content.md) or [attachment](attachment.md) is required. |
| [run](run.md) | `fun run(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inherited Functions

| Name | Summary |
|---|---|
| [item](../-sub-task/item.md) | `open fun <T> `[`ItemGroup`](../-item-group/index.md)`<`[`T`](../-sub-task/item.md#T)`>.item(): `[`T`](../-sub-task/item.md#T)<br>Gets the item from the given item group that corresponds to [entry](../org.gradle.api.-task/entry.md). |
| [items](../-sub-task/items.md) | `open fun <T> `[`ItemGroup`](../-item-group/index.md)`<`[`T`](../-sub-task/items.md#T)`>.items(): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`T`](../-sub-task/items.md#T)`>`<br>Gets the items from the given item group that correspond to [episodes](../org.gradle.api.-task/episodes.md). |

### Extension Properties

| Name | Summary |
|---|---|
| [batch](../org.gradle.api.-task/batch.md) | `val `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.batch: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The same as [entry](../org.gradle.api.-task/entry.md) if this is a batch task; error otherwise. |
| [currentTask](../org.gradle.api.-task/current-task.md) | `val `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.currentTask: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The name of this task. |
| [entry](../org.gradle.api.-task/entry.md) | `val `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.entry: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The entry (batch or episode) this task corresponds to. |
| [episode](../org.gradle.api.-task/episode.md) | `val `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.episode: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The same as [entry](../org.gradle.api.-task/entry.md) if this is an episode task; error otherwise. |
| [episodes](../org.gradle.api.-task/episodes.md) | `val `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.episodes: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>The episodes this task corresponds to. A single-item list containing [episode](../org.gradle.api.-task/episode.md) if this is an episode task; a list of the episodes for the batch given by [batch](../org.gradle.api.-task/batch.md) otherwise. |
| [isBatch](../org.gradle.api.-task/is-batch.md) | `val `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.isBatch: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>True if this task is a batch task. |
| [release](../org.gradle.api.-task/release.md) | `val `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.release: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The release this task was generated for. |
| [taskGroup](../task-group.md) | `val <T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> `[`T`](../task-group.md#T)`.taskGroup: `[`TaskGroup`](../-task-group/index.md)`<`[`T`](../task-group.md#T)`>`<br>The [TaskGroup](../-task-group/index.md) instance this task belongs to. |

### Extension Functions

| Name | Summary |
|---|---|
| [defaultProperty](../org.gradle.api.-task/default-property.md) | `fun <T> `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.defaultProperty(default: `[`T`](../org.gradle.api.-task/default-property.md#T)`): `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`T`](../org.gradle.api.-task/default-property.md#T)`>`<br>Returns a [Property](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html) with a default value set. |
| [evaluate](../org.gradle.api.-task/evaluate.md) | `fun `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.evaluate(expression: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>!>!`<br>Evaluates a string using [Velocity](https://velocity.apache.org/engine/2.2/user-guide.html), splits it on `|`, and processes it with [glob](../org.gradle.api.-project/glob.md). |
| [evaluateTemplate](../org.gradle.api.-task/evaluate-template.md) | `fun `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.evaluateTemplate(expression: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!>!`<br>Like [evaluate](../org.gradle.api.-task/evaluate.md) but only processes the template syntax, without globbing. |
| [get](../org.gradle.api.-task/get.md) | `fun `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.get(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!>!`<br>Searches for the given property in the [Subs](../-subs/index.md) object's [SubProperties](../-sub-properties/index.md) instance, evaluates its value using [evaluate](../org.gradle.api.-task/evaluate.md), and returns a single string, assuming that the resulting list contains only one element. |
| [getAs](../org.gradle.api.-task/get-as.md) | `fun <T> `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.getAs(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`T`](../org.gradle.api.-task/get-as.md#T)`>!`<br>Searches for the given property in the [Subs](../-subs/index.md) object's [SubProperties](../-sub-properties/index.md) instance, evaluates its value using [evaluate](../org.gradle.api.-task/evaluate.md), and returns a single string, cast to the given type using [String.asType](../kotlin.-string/as-type.md), assuming that the resulting list contains only one element. |
| [getFile](../org.gradle.api.-task/get-file.md) | `fun `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.getFile(filename: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!>!`<br>Reads the specified file and processes it using [Velocity](https://velocity.apache.org/engine/2.2/user-guide.html). |
| [getList](../org.gradle.api.-task/get-list.md) | `fun `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.getList(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>`<br>Searches for the given property in the [Subs](../-subs/index.md) object's [SubProperties](../-sub-properties/index.md) instance, and evaluates its value using [evaluate](../org.gradle.api.-task/evaluate.md). |
| [getListAs](../org.gradle.api.-task/get-list-as.md) | `fun <T> `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.getListAs(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`T`](../org.gradle.api.-task/get-list-as.md#T)`>!>!`<br>Searches for the given property in the [Subs](../-subs/index.md) object's [SubProperties](../-sub-properties/index.md) instance, evaluates its value using [evaluate](../org.gradle.api.-task/evaluate.md), and casts the list elements to the given type using [String.asType](../kotlin.-string/as-type.md). |
| [getRaw](../org.gradle.api.-task/get-raw.md) | `fun `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.getRaw(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Searches for the given property in the [Subs](../-subs/index.md) object's [SubProperties](../-sub-properties/index.md) instance, and returns the raw string. Raises an error if not found. |
| [getRawMaybe](../org.gradle.api.-task/get-raw-maybe.md) | `fun `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.getRawMaybe(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>Searches for the given property in the [Subs](../-subs/index.md) object's [SubProperties](../-sub-properties/index.md) instance, and returns the raw string, possibly null. |
| [outputFile](../org.gradle.api.-task/output-file.md) | `fun `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.outputFile(extension: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`ConfigurableFileCollection`](https://docs.gradle.org/current/javadoc/org/gradle/api/file/ConfigurableFileCollection.html)<br>Returns a [ConfigurableFileCollection](https://docs.gradle.org/current/javadoc/org/gradle/api/file/ConfigurableFileCollection.html) containing a single file `taskName.extension` located in the build directory. |
