[subkt](../../index.md) / [myaa.subkt.ass](../index.md) / [MapLine](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`MapLine(type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)`

A line in a [FormatSection](../-format-section/index.md). The fields contained in this object can be
accessed in three ways:

1. Statically, using the properties defined in subtypes;
2. Unsafely as a map, using the ASS field names as keys;
3. Using type-safe accessors.

The following three assignments are equivalent:

```
eventLine.layer = 10
eventLine.put(EventLineAccessor.LAYER, 10)
eventLine["Layer"] = 10 // no compiler error if assigning wrong type
```

Further, consider these three lines:

```
val layer = eventLine.layer // inferred type: Int
val layer = eventLine.get(EventLineAccessor.LAYER) // inferred type: Int
val layer = eventLine["Layer"] // inferred type: Any?; must be manually cast to Int
```

Accessors provide a way for functions to define a type-safe contract while
still allowing the user to specify what field to read from or write to.
E.g. a function that only operates on [EventLine](../-event-line/index.md) fields of type [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)
can be defined as follows:

```
fun doThing(line: EventLine, accessor: EventLineAccessor<T>) { /* ... */}
```

This is particularly useful for DSLs.

