[subkt](../../../index.md) / [myaa.subkt.tasks](../../index.md) / [Mux](../index.md) / [Track](index.md) / [trackOrder](./track-order.md)

# trackOrder

`val trackOrder: `[`Property`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Property.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.7/src/main/kotlin/myaa/subkt/tasks/muxtask.kt#L320)

The order of this track in the muxed file. Tracks will be sorted
by the values specified for this property.

The track order values specified do not need to be consecutive.
Tracks with the same [trackOrder](./track-order.md) will keep their original relative ordering.

**Getter**

The order of this track in the muxed file. Tracks will be sorted
by the values specified for this property.

The track order values specified do not need to be consecutive.
Tracks with the same [trackOrder](./track-order.md) will keep their original relative ordering.

