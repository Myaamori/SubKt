[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [HTTP](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`HTTP()`

Task for sending general HTTP requests.
Data should be sent using one of [json](json.md), [body](body.md) and [form](form.md).
The response can be retrieved from [responseData](response-data.md) or [responseJson](response-json.md).

``` kotlin
"http"<HTTP> {
    host("example.com")
    endpoint("/api/v2/post")
    port(8080)
    https(false)
    method("POST")
    json(
            "content" to "This is the content",
            "author" to "Jane Doe"
    )

    // equivalently:
    contentType(ContentType.Application.Json)
    body("{\"content\":\"This is the content\",\"author\":\"Jane Doe\"}")

    doLast {
        println(responseData)
    }
}
```

