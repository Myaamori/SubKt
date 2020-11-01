[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Automation](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`Automation()`

Task for running Aegisub automations on a script using
[Aegisub CLI](https://github.com/Myaamori/aegisub-cli).

The `aegisub-cli` binary needs to be in your PATH,
or specified using [aegisubCli](aegisub-cli.md).

``` kotlin
val assWipe by task<Automation> {
    from(merge.item())
    // filename of script
    script("l0.ASSWipe.moon")
    // name of macro as passed to aegisub.register
    macro("ASSWipe")
    // video to load (needed for e.g. aegisub.frame_from_ms)
    video("premux.mkv")
    dialog(
            // dialog values
            "removeInvilible" to true,
            "cleanLevel" to 4,
            "extraDataMode" to "Remove all",
            // button to push to confirm dialog
            button = 0
    )
    loglevel(Automation.LogLevel.INFO)

    selectLines { ass ->
        // select only lines that are not commented
        select(ass.events.lines.filter { !it.comment })
    }

    out(get("mergefile"))
}
```

