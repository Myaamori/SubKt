[subkt](../../index.md) / [myaa.subkt.tasks](../index.md) / [OverwriteStrategy](./index.md)

# OverwriteStrategy

`enum class OverwriteStrategy` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.4/src/main/kotlin/myaa/subkt/tasks/tasks.kt#L1539)

The strategy for overwriting existing files.

### Enum Values

| Name | Summary |
|---|---|
| [NEVER](-n-e-v-e-r.md) | Never overwrite existing files. |
| [SOURCE_NEWER](-s-o-u-r-c-e_-n-e-w-e-r.md) | Overwrite if the source file is newer. |
| [DIFFERENT_SIZE](-d-i-f-f-e-r-e-n-t_-s-i-z-e.md) | Overwrite if the source file has a different size. |
| [DIFFERENT_SIZE_OR_SOURCE_NEWER](-d-i-f-f-e-r-e-n-t_-s-i-z-e_-o-r_-s-o-u-r-c-e_-n-e-w-e-r.md) | Overwrite if the source file is newer or has a different size. |
| [ALWAYS](-a-l-w-a-y-s.md) | Always overwrite. |
