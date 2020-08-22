[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [Subs](index.md) / [getMap](./get-map.md)

# getMap

`fun getMap(keys: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, values: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>!>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.7/src/main/kotlin/myaa/subkt/tasks/plugin.kt#L593)

Constructs a map by looking up [keys](get-map.md#myaa.subkt.tasks.Subs$getMap(kotlin.String, kotlin.String)/keys) in the properties, taking
its values to be the keys of the map, and then for each such key,
looking up the property with the key as its entry and [values](get-map.md#myaa.subkt.tasks.Subs$getMap(kotlin.String, kotlin.String)/values)
as its property name, and taking the value of that property
to be the value associated with that key.

Consider e.g. a properties file containing:

```
batches=vol{1..3}
vol1.episodes={01..04}
vol2.episodes={05..08}
vol3.episodes={09..12}
```

Calling `getMap("batches", "episodes")` would return the following map:

```
{vol1=[01, 02, 03, 04], vol2=[05, 06, 07, 08], vol3=[09, 10, 11, 12]}
```

