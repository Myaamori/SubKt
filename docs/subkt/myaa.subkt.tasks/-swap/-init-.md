[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Swap](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`Swap()`

Task to enable/disable honorifics and the like using the same syntax as
[Daiz's autoswapper](https://github.com/Daiz/AegisubMacros#autoswapperlua---autoswapper).

On the lines whose style name matches [styles](styles.md), e.g. `Karen{**-chan}` will turn
into `Karen{*}-chan{*}`, and {*}Miu{*Nee-nee} will become {*}Nee-nee{*Miu}.
Further, any line where the effect line contains the string specified by [lineMarker](line-marker.md)
will be commented if it is not a comment already, and will otherwise be uncommented.

A predefined task instance can be accessed through [Subs.swap](../swap.md).

Example usage:

```
```

