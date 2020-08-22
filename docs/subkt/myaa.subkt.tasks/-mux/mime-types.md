[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Mux](index.md) / [mimeTypes](./mime-types.md)

# mimeTypes

`val mimeTypes: `[`MutableMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.7/src/main/kotlin/myaa/subkt/tasks/muxtask.kt#L622)

Overrides the MIME types autodetected by mkvmerge for files attached via [attach](attach.md),
on a file extension basis. E.g. to force all files ending with `.txt` to have
MIME type `text/html`, you can run:

```
mimeTypes["txt"] = "text/html"
```

The file extension should be specified in lower case, and will be matched against
attached files *case-insensitively* (i.e. `ttf` will match both files ending
in `.ttf` and `.TTF`).

By default, `ttf` is mapped to `application/x-truetype-font`, and `otf` to
`application/vnd.ms-opentype`.

**Getter**

Overrides the MIME types autodetected by mkvmerge for files attached via [attach](attach.md),
on a file extension basis. E.g. to force all files ending with `.txt` to have
MIME type `text/html`, you can run:

```
mimeTypes["txt"] = "text/html"
```

The file extension should be specified in lower case, and will be matched against
attached files *case-insensitively* (i.e. `ttf` will match both files ending
in `.ttf` and `.TTF`).

By default, `ttf` is mapped to `application/x-truetype-font`, and `otf` to
`application/vnd.ms-opentype`.

