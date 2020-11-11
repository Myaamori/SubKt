[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [FTP](index.md) / [sslSessionReuse](./ssl-session-reuse.md)

# sslSessionReuse

`val sslSessionReuse: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.12/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L1800)

If true, the same SSL session as the control channel will be used for data connections.
Required for some servers, e.g. `vsftpd` with `require_ssl_reuse` enabled,
but requires a hacky workaround for Java clients. Try disabling if you get strange issues.
Defaults to true.

**Getter**

If true, the same SSL session as the control channel will be used for data connections.
Required for some servers, e.g. `vsftpd` with `require_ssl_reuse` enabled,
but requires a hacky workaround for Java clients. Try disabling if you get strange issues.
Defaults to true.

