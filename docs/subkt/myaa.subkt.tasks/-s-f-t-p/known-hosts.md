[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [SFTP](index.md) / [knownHosts](./known-hosts.md)

# knownHosts

`val knownHosts: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`

The known hosts file. SFTP will refuse to connect unless the host is
present in this file.
Defaults to ~/.ssh/known_hosts.

JSch will prefer RSA fingerprints. If your known_hosts file contains
an ECDSA fingerprint and SFTP fails to recognize the fingerprint,
try running

```
ssh-keyscan -H -t rsa HOST_HERE >> ~/.ssh/known_hosts
```

to add an RSA fingerprint. See https://stackoverflow.com/a/44777270.

**Getter**

The known hosts file. SFTP will refuse to connect unless the host is
present in this file.
Defaults to ~/.ssh/known_hosts.

JSch will prefer RSA fingerprints. If your known_hosts file contains
an ECDSA fingerprint and SFTP fails to recognize the fingerprint,
try running

```
ssh-keyscan -H -t rsa HOST_HERE >> ~/.ssh/known_hosts
```

to add an RSA fingerprint. See https://stackoverflow.com/a/44777270.

