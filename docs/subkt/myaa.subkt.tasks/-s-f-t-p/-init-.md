[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [SFTP](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`SFTP()`

Task for uploading files via SFTP (SSH).
A predefined task instance can be accessed through [Subs.sftp](../sftp.md).

``` kotlin
sftp {
    from(mux.items())
    // copy mkv files in the 01/extra directory into a "bonus" directory
    from("01/extra") {
        include("*.mkv")
        into("bonus")
    }
    // directory to copy to
    into("Downloads/My Show - 01")

    host("ssh.example.com")
    // identity file, username and port specified in config file
    // defaults to ~/.ssh/config
    config("sshconfig")
    // file with fingerprints for verifying the host
    // defaults to ~/.ssh/known_hosts
    knownHosts("known_hosts")
    overwriteIf(OverwriteStrategy.ALWAYS)
}
```

