[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [SSHTask](./index.md)

# SSHTask

`interface SSHTask` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.9/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L1855)

Common interface for tasks that connect to SSH.

### Properties

| Name | Summary |
|---|---|
| [config](config.md) | `abstract val config: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>The SSH config file. Contains per-host settings such as username, host address and identity file. Defaults to ~/.ssh/config. |
| [connectionTimeout](connection-timeout.md) | `abstract val connectionTimeout: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>`<br>The timeout in milliseconds after which to abort the connection. Defaults to 15000. |
| [host](host.md) | `abstract val host: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>The hostname or IP address of the SSH server. |
| [identity](identity.md) | `abstract val identity: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>The private identity key file for logging in to the SSH server. May also be specified using `IdentityFile` in the SSH config file. |
| [knownHosts](known-hosts.md) | `abstract val knownHosts: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>The known hosts file. SFTP will refuse to connect unless the host is present in this file. Defaults to ~/.ssh/known_hosts. |
| [password](password.md) | `abstract val password: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>The password for logging in to the SSH server. Not recommended; prefer using an identity file instead. |
| [port](port.md) | `abstract val port: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>`<br>The port of the SSH server. May also be specified using `Port` in the SSH config file. |
| [username](username.md) | `abstract val username: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>The username for logging in to the SSH server. May also be specified using `User` in the SSH config file. |

### Functions

| Name | Summary |
|---|---|
| [createSession](create-session.md) | `abstract fun createSession(): Session` |

### Inheritors

| Name | Summary |
|---|---|
| [SFTP](../-s-f-t-p/index.md) | `abstract class SFTP : `[`AbstractTransferTask`](../-abstract-transfer-task/index.md)`<ChannelSftp>, `[`SSHTask`](./index.md)<br>Task for uploading files via SFTP (SSH). A predefined task instance can be accessed through [Subs.sftp](../sftp.md). |
| [SSHExec](../-s-s-h-exec/index.md) | `open class SSHExec : `[`DefaultSubTask`](../-default-sub-task/index.md)`, `[`SSHTask`](./index.md)<br>Task for executing commands on a remote shell via SSH. |
