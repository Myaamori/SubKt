[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [FTP](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`FTP()`

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

