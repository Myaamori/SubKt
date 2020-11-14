[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [HTTP](./index.md)

# HTTP

`open class HTTP : `[`PropertyTask`](../-property-task/index.md)`, `[`SubTask`](../-sub-task/index.md) [(source)](https://github.com/Myaamori/SubKt/blob/0.1.13/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L1303)

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

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `HTTP()`<br>Task for sending general HTTP requests. Data should be sent using one of [json](json.md), [body](body.md) and [form](form.md). The response can be retrieved from [responseData](response-data.md) or [responseJson](response-json.md). |

### Properties

| Name | Summary |
|---|---|
| [attachment](attachment.md) | `val attachment: `[`ConfigurableFileCollection`](https://docs.gradle.org/current/javadoc/org/gradle/api/file/ConfigurableFileCollection.html)`!`<br>Binary file data to send. |
| [basicAuthPass](basic-auth-pass.md) | `val basicAuthPass: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>Password for basic authentication. |
| [basicAuthUser](basic-auth-user.md) | `val basicAuthUser: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>Username for basic authentication. |
| [body](body.md) | `val body: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>Plain text data to send. |
| [contentType](content-type.md) | `val contentType: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<ContentType>`<br>The content type. Only used if sending data using [body](body.md). |
| [endpoint](endpoint.md) | `val endpoint: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>The endpoint to request. Defaults to /. |
| [form](form.md) | `val form: `[`MapProperty`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/MapProperty.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>URL encoded form data to send. |
| [headers](headers.md) | `val headers: `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>`<br>HTTP headers. |
| [host](host.md) | `val host: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>The hostname or IP address. |
| [https](https.md) | `val https: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>`<br>If true, use HTTPS; HTTP otherwise. Defaults to true. |
| [json](json.md) | `val json: `[`MapProperty`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/MapProperty.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>`<br>JSON data to send. |
| [method](method.md) | `val method: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>The HTTP method. Defaults to GET. |
| [out](out.md) | `val out: `[`File`](https://docs.oracle.com/javase/9/docs/api/java/io/File.html) |
| [parameters](parameters.md) | `val parameters: `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>`<br>Query parameters. |
| [port](port.md) | `val port: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>`<br>The port to use. Defaults to the default port for the protocol used. |
| [responseData](response-data.md) | `var responseData: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The response. |
| [responseJson](response-json.md) | `val responseJson: JsonElement`<br>The response as JSON. |
| [urlPass](url-pass.md) | `val urlPass: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>A password for https://username:password@example.com style authentication. |
| [urlUser](url-user.md) | `val urlUser: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>A username for https://username:password@example.com style authentication. |
| [userAgent](user-agent.md) | `val userAgent: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>The user agent. |

### Inherited Properties

| Name | Summary |
|---|---|
| [propertyFile](../-property-task/property-file.md) | `val propertyFile: `[`File`](https://docs.oracle.com/javase/9/docs/api/java/io/File.html) |

### Functions

| Name | Summary |
|---|---|
| [header](header.md) | `fun header(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun header(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Add an HTTP header. |
| [parameter](parameter.md) | `fun parameter(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun parameter(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Add a query parameter. |
| [run](run.md) | `open fun run(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inherited Functions

| Name | Summary |
|---|---|
| [doTask](../-property-task/do-task.md) | `fun doTask(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

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
| [propertyExists](../org.gradle.api.-task/property-exists.md) | `fun `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.propertyExists(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Returns true if the given property exists in the [Subs](../-subs/index.md) object's [SubProperties](../-sub-properties/index.md) instance for the given context. |
