[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [FTP](./index.md)

# FTP

`abstract class FTP : `[`AbstractTransferTask`](../-abstract-transfer-task/index.md)`<FTPClient>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.13/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L1736)

Task for uploading files via FTP.
A predefined task instance can be accessed through [Subs.ftp](../ftp.md).

``` kotlin
ftp {
    from(mux.batchItems())
    // copy mkv files in the 01/extra directory into a "bonus" directory
    from("01/extra") {
        include("*.mkv")
        into("bonus")
    }
    // directory to copy to
    into("/Downloads/My Show - 01")

    host("ftp.example.com")
    port(980)
    username("username")
    password("password")
    certificateVerificationMode(FTP.CertificateMode.ACCEPT_ALL)
    overwriteIf(OverwriteStrategy.DIFFERENT_SIZE_OR_SOURCE_NEWER)
}
```

### Types

| Name | Summary |
|---|---|
| [CertificateMode](-certificate-mode/index.md) | `enum class CertificateMode`<br>The strategy for verifying certificates when using FTPS. |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FTP()`<br>Task for uploading files via FTP. A predefined task instance can be accessed through [Subs.ftp](../ftp.md). |

### Properties

| Name | Summary |
|---|---|
| [certificateVerificationMode](certificate-verification-mode.md) | `val certificateVerificationMode: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`FTP.CertificateMode`](-certificate-mode/index.md)`>`<br>The certificate verification strategy to use for SSL connections. Defaults to [CertificateMode.VERIFY_NOT_EXPIRED](-certificate-mode/-v-e-r-i-f-y_-n-o-t_-e-x-p-i-r-e-d.md). |
| [host](host.md) | `val host: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>The hostname or IP address of the FTP server. |
| [implicitSsl](implicit-ssl.md) | `val implicitSsl: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>`<br>Whether to use implicit mode for the connection. Defaults to false. |
| [password](password.md) | `val password: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>The password for logging in to the FTP server. |
| [port](port.md) | `val port: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>`<br>The port of the FTP server. |
| [sslSessionReuse](ssl-session-reuse.md) | `val sslSessionReuse: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>`<br>If true, the same SSL session as the control channel will be used for data connections. Required for some servers, e.g. `vsftpd` with `require_ssl_reuse` enabled, but requires a hacky workaround for Java clients. Try disabling if you get strange issues. Defaults to true. |
| [username](username.md) | `val username: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>The username for logging in to the FTP server. |
| [useSsl](use-ssl.md) | `val useSsl: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>`<br>Whether to encrypt the connection (FTPS). Defaults to true. |

### Inherited Properties

| Name | Summary |
|---|---|
| [overwriteIf](../-abstract-transfer-task/overwrite-if.md) | `val overwriteIf: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`OverwriteStrategy`](../-overwrite-strategy/index.md)`>`<br>The overwriting strategy to use when encountering existing files with the same name. Defaults to [OverwriteStrategy.DIFFERENT_SIZE](../-overwrite-strategy/-d-i-f-f-e-r-e-n-t_-s-i-z-e.md). |
| [progressLoggerFactory](../-abstract-transfer-task/progress-logger-factory.md) | `abstract val progressLoggerFactory: ProgressLoggerFactory` |

### Functions

| Name | Summary |
|---|---|
| [createClient](create-client.md) | `open fun createClient(): FTPClient` |
| [makedir](makedir.md) | `open fun makedir(client: FTPClient, path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [stat](stat.md) | `open fun stat(client: FTPClient, file: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`AbstractTransferTask.FileDetails`](../-abstract-transfer-task/-file-details/index.md)`?` |
| [upload](upload.md) | `open fun upload(client: FTPClient, file: `[`InputStream`](https://docs.oracle.com/javase/9/docs/api/java/io/InputStream.html)`, dest: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, callback: (`[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Inherited Functions

| Name | Summary |
|---|---|
| [createCopyAction](../-abstract-transfer-task/create-copy-action.md) | `fun createCopyAction(): CopyAction` |
| [createRootSpec](../-abstract-transfer-task/create-root-spec.md) | `open fun createRootSpec(): `[`AbstractTransferTask.DestDirRootSpec`](../-abstract-transfer-task/-dest-dir-root-spec/index.md)`<`[`T`](../-abstract-transfer-task/-dest-dir-root-spec/index.md#T)`>` |
| [getRootSpec](../-abstract-transfer-task/get-root-spec.md) | `open fun getRootSpec(): `[`AbstractTransferTask.DestDirRootSpec`](../-abstract-transfer-task/-dest-dir-root-spec/index.md)`<*>` |
| [makedirs](../-abstract-transfer-task/makedirs.md) | `fun makedirs(client: `[`T`](../-abstract-transfer-task/-dest-dir-root-spec/index.md#T)`, path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

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
| [includeExtensions](../org.gradle.api.tasks.util.-pattern-filterable/include-extensions.md) | `fun `[`PatternFilterable`](https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/util/PatternFilterable.html)`.includeExtensions(vararg extensions: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Filter files by file extension. Case-insensitive. |
| [outputFile](../org.gradle.api.-task/output-file.md) | `fun `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.outputFile(extension: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`ConfigurableFileCollection`](https://docs.gradle.org/current/javadoc/org/gradle/api/file/ConfigurableFileCollection.html)<br>Returns a [ConfigurableFileCollection](https://docs.gradle.org/current/javadoc/org/gradle/api/file/ConfigurableFileCollection.html) containing a single file `taskName.extension` located in the build directory. |
| [propertyExists](../org.gradle.api.-task/property-exists.md) | `fun `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`.propertyExists(propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Returns true if the given property exists in the [Subs](../-subs/index.md) object's [SubProperties](../-sub-properties/index.md) instance for the given context. |
