[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Discord](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`Discord()`

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

