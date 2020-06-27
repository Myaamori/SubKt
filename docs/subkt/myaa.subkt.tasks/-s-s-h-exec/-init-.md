[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [SSHExec](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`SSHExec(objects: `[`ObjectFactory`](https://docs.gradle.org/current/javadoc/org/gradle/api/model/ObjectFactory.html)`)`

Task for executing commands on a remote shell via SSH.

``` kotlin
"sshexec"<SSHExec> {
    host("ssh.example.com")
    // identity file, username and port specified in config file
    // defaults to ~/.ssh/config
    config("sshconfig")
    // file with fingerprints for verifying the host
    // defaults to ~/.ssh/known_hosts
    knownHosts("known_hosts")

    command("ls")
}
```

