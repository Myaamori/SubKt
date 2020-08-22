[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [SubProperties](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`SubProperties()`

Reads a list of properties of the form `release.entry.property=value`, which can
later be looked up using the [match](match.md) method.

Properties may contain wildcards, which will match any sequence of characters.
For instance, a property defined as `TV.episode*.name=value` will be found
with a `match("name", entry="episode01", release="TV")` call.
Groups (e.g. `{01,03,04}.op=OP1.ass`) and ranges (e.g. `{01..12}.cour=1`) are also supported.

Unspecified release and entry components are equivalent to wildcards.
In other words, `name=value` is equivalent to `*.*.name=value`, while
`01.name=value` is equivalent to `*.01.name=value`.

Properties are ordered, and the value of the last property matching the
[match](match.md) query will be returned. Thus, later values will override earlier values.

Lines starting with `#` will be interpreted as comments and ignored.

