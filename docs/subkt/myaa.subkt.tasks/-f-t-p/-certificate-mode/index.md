[subkt](../../../index.md) / [myaa.subkt.tasks](../../index.md) / [FTP](../index.md) / [CertificateMode](./index.md)

# CertificateMode

`enum class CertificateMode` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.4/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L1709)

The strategy for verifying certificates when using FTPS.

### Enum Values

| Name | Summary |
|---|---|
| [DEFAULT](-d-e-f-a-u-l-t.md) | Use the default Java trust manager. |
| [VERIFY_NOT_EXPIRED](-v-e-r-i-f-y_-n-o-t_-e-x-p-i-r-e-d.md) | Verify that the certificate has not expired, but do not perform any further checks. |
| [ACCEPT_ALL](-a-c-c-e-p-t_-a-l-l.md) | Accept all certificates. |
