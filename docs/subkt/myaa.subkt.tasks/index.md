[subkt](../index.md) / [myaa.subkt.tasks](./index.md)

## Package myaa.subkt.tasks

This package contains facilities for defining fansubbing-related tasks using Gradle.

Of particular interest are:

* [Subs](-subs/index.md) - central extension tracking episodes, batches and tasks, and providing access to properties
* [glob](org.gradle.api.-project/glob.md) - basic brace expansion and globbing
* [ItemGroupContext](-item-group-context/index.md) - facilities for creating groups of items (e.g. tasks) for multiple entries
* [ItemGroup](-item-group/index.md) - represents a group of items (e.g. tasks) which can be accessed on an entry basis
* The extension functions to [org.gradle.api.Task](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)

The package provides the following Gradle tasks:

* [Merge](-merge/index.md) for merging ASS files
* [Chapters](-chapters/index.md) for generating chapter files
* [Swap](-swap/index.md) for swapping honorifics and the like
* [ASS](-a-s-s/index.md) for modifying ASS files
* [Automation](-automation/index.md) for running Aegisub automations
* [Mux](-mux/index.md) for muxing files using mkvmerge
* [FTP](-f-t-p/index.md) for uploading files to an FTP server
* [SFTP](-s-f-t-p/index.md) for uploading files to an SSH server
* [SSHExec](-s-s-h-exec/index.md) for executing commands on an SSH server
* [HTTP](-h-t-t-p/index.md) for making HTTP(S) requests
* [Torrent](-torrent/index.md) for generating a torrent file
* [Nyaa](-nyaa/index.md) for publishing to nyaa.si
* [Anidex](-anidex/index.md) for publishing to anidex.info
* [Discord](-discord/index.md) for posting messages to a Discord webhook

In addition, simple wrappers of standard Gradle tasks implementing [SubTask](-sub-task/index.md) are available for convenience:

* [SubCopy](-sub-copy/index.md)
* [SubExec](-sub-exec/index.md)
* [SubSync](-sub-sync/index.md)
* [SubZip](-sub-zip/index.md)

### Types

| Name | Summary |
|---|---|
| [AbstractTransferTask](-abstract-transfer-task/index.md) | `abstract class AbstractTransferTask<T> : `[`AbstractCopyTask`](https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/AbstractCopyTask.html)`, `[`SubTask`](-sub-task/index.md) |
| [Anidex](-anidex/index.md) | `open class Anidex : `[`PropertyTask`](-property-task/index.md)<br>Task for uploading a torrent file to anidex.info. A predefined task instance can be accessed through [Subs.anidex](anidex.md). |
| [ASS](-a-s-s/index.md) | `open class ASS : `[`ASSTask`](-a-s-s-task/index.md)<br>Generic task for modifying ASS files. See [ASSFile](../myaa.subkt.ass/-a-s-s-file/index.md) and related classes for more information. The modified file is written to a new file. |
| [ASSColorSerializer](-a-s-s-color-serializer/index.md) | `object ASSColorSerializer : JsonSerializer<`[`Color`](https://docs.oracle.com/javase/9/docs/api/java/awt/Color.html)`>` |
| [ASSTask](-a-s-s-task/index.md) | `abstract class ASSTask : `[`PropertyTask`](-property-task/index.md)<br>Represents a task that outputs an ASS file. |
| [Automation](-automation/index.md) | `open class Automation : `[`DefaultTask`](https://docs.gradle.org/current/javadoc/org/gradle/api/DefaultTask.html)`, `[`SubTask`](-sub-task/index.md)<br>Task for running Aegisub automations on a script using [Aegisub CLI](https://github.com/Myaamori/aegisub-cli). |
| [BaseContext](-base-context/index.md) | `abstract class BaseContext : `[`AbstractContext`](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html)<br>Simple base implementation of a Velocity [AbstractContext](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html). |
| [Chapters](-chapters/index.md) | `open class Chapters : `[`PropertyTask`](-property-task/index.md)<br>Generates a chapter file from an ASS file in the same way as Significance. The provided ASS file will be searched for lines where the field specified by [field](-chapters/field.md) contains the value specified by [chapterMarker](-chapters/chapter-marker.md), and for each such line a chapter will be generated using the start time as the time, and the value of the field specified by [chapterName](-chapters/chapter-name.md) as the chapter name. A predefined task instance can be accessed through [Subs.chapters](chapters.md). |
| [ColorSerializer](-color-serializer/index.md) | `object ColorSerializer : JsonSerializer<`[`Color`](https://docs.oracle.com/javase/9/docs/api/java/awt/Color.html)`>` |
| [DefaultSubTask](-default-sub-task/index.md) | `open class DefaultSubTask : `[`DefaultTask`](https://docs.gradle.org/current/javadoc/org/gradle/api/DefaultTask.html)`, `[`SubTask`](-sub-task/index.md)<br>Wrapper task for [DefaultTask](https://docs.gradle.org/current/javadoc/org/gradle/api/DefaultTask.html) which implements [SubTask](-sub-task/index.md), giving access to [SubTask.item](-sub-task/item.md) and [SubTask.batchItems](-sub-task/batch-items.md) for convenience. |
| [Discord](-discord/index.md) | `open class Discord : `[`DefaultTask`](https://docs.gradle.org/current/javadoc/org/gradle/api/DefaultTask.html)`, `[`SubTask`](-sub-task/index.md)<br>Task for posting to a Discord webhook. Supports normal messages, embeds, and uploading files. A predefined task instance can be accessed through [Subs.discord](discord.md). |
| [ErrorMode](-error-mode/index.md) | `enum class ErrorMode`<br>Used in some tasks to specify the failure mode. |
| [Filterable](-filterable/index.md) | `interface Filterable`<br>Represents an item that can be optionally discarded. |
| [FTP](-f-t-p/index.md) | `abstract class FTP : `[`AbstractTransferTask`](-abstract-transfer-task/index.md)`<FTPClient>`<br>Task for uploading files via FTP. A predefined task instance can be accessed through [Subs.ftp](ftp.md). |
| [HTTP](-h-t-t-p/index.md) | `open class HTTP : `[`PropertyTask`](-property-task/index.md)`, `[`SubTask`](-sub-task/index.md)<br>Task for sending general HTTP requests. Data should be sent using one of [json](-h-t-t-p/json.md), [body](-h-t-t-p/body.md) and [form](-h-t-t-p/form.md). The response can be retrieved from [responseData](-h-t-t-p/response-data.md) or [responseJson](-h-t-t-p/response-json.md). |
| [ItemGroup](-item-group/index.md) | `abstract class ItemGroup<T>`<br>A group of items of type [T](-item-group/index.md#T). Has convenience functions for getting all items belonging to the same entry as a certain task, or all items corresponding to a specified list of entries. |
| [ItemGroupContext](-item-group-context/index.md) | `abstract class ItemGroupContext`<br>Context for defining groups of objects such as tasks. |
| [Merge](-merge/index.md) | `open class Merge : `[`ASSTask`](-a-s-s-task/index.md)<br>Task to merge multiple ASS files into one. A predefined task instance can be accessed through [Subs.merge](merge.md). |
| [Mux](-mux/index.md) | `open class Mux : `[`PropertyTask`](-property-task/index.md)<br>Task to mux a set of files into a single Matroska container using mkvmerge. |
| [Nyaa](-nyaa/index.md) | `open class Nyaa : `[`PropertyTask`](-property-task/index.md)<br>Task for uploading a torrent file to nyaa.si. A predefined task instance can be accessed through [Subs.nyaa](nyaa.md). |
| [OverwriteStrategy](-overwrite-strategy/index.md) | `enum class OverwriteStrategy`<br>The strategy for overwriting existing files. |
| [PropertyTask](-property-task/index.md) | `abstract class PropertyTask : `[`DefaultTask`](https://docs.gradle.org/current/javadoc/org/gradle/api/DefaultTask.html)`, `[`SubTask`](-sub-task/index.md)<br>Parent task type that automatically keeps track of and stores properties in JSON format. |
| [ProviderGroup](-provider-group/index.md) | `class ProviderGroup<T> : `[`ItemGroup`](-item-group/index.md)`<`[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<`[`T`](-provider-group/index.md#T)`>>`<br>[ItemGroup](-item-group/index.md) that keeps track of providers for values of type [T](-provider-group/index.md#T). A [Provider](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html) is generated for each entry, and the given closure is evaluated when the provider's value is requested. |
| [ProviderSerializer](-provider-serializer/index.md) | `object ProviderSerializer : JsonSerializer<`[`Provider`](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html)`<*>>` |
| [SFTP](-s-f-t-p/index.md) | `abstract class SFTP : `[`AbstractTransferTask`](-abstract-transfer-task/index.md)`<ChannelSftp>, `[`SSHTask`](-s-s-h-task/index.md)<br>Task for uploading files via SFTP (SSH). A predefined task instance can be accessed through [Subs.sftp](sftp.md). |
| [SSHExec](-s-s-h-exec/index.md) | `open class SSHExec : `[`DefaultSubTask`](-default-sub-task/index.md)`, `[`SSHTask`](-s-s-h-task/index.md)<br>Task for executing commands on a remote shell via SSH. |
| [SSHTask](-s-s-h-task/index.md) | `interface SSHTask`<br>Common interface for tasks that connect to SSH. |
| [SubCopy](-sub-copy/index.md) | `open class SubCopy : `[`Copy`](https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/Copy.html)`, `[`SubTask`](-sub-task/index.md)<br>Wrapper task for [Copy](https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/Copy.html) which implements [SubTask](-sub-task/index.md), giving access to [SubTask.item](-sub-task/item.md) and [SubTask.batchItems](-sub-task/batch-items.md) for convenience. |
| [SubExec](-sub-exec/index.md) | `open class SubExec : `[`Exec`](https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/Exec.html)`, `[`SubTask`](-sub-task/index.md)<br>Wrapper task for [Exec](https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/Exec.html) which implements [SubTask](-sub-task/index.md), giving access to [SubTask.item](-sub-task/item.md) and [SubTask.batchItems](-sub-task/batch-items.md) for convenience. |
| [SubPlugin](-sub-plugin/index.md) | `class SubPlugin : `[`Plugin`](https://docs.gradle.org/current/javadoc/org/gradle/api/Plugin.html)`<`[`Project`](https://docs.gradle.org/current/javadoc/org/gradle/api/Project.html)`>`<br>Plugin that adds a [Subs](-subs/index.md) instance as a DSL extension with the name "subs". |
| [SubProperties](-sub-properties/index.md) | `class SubProperties`<br>Reads a list of properties of the form `release.entry.property=value`, which can later be looked up using the [match](-sub-properties/match.md) method. |
| [Subs](-subs/index.md) | `open class Subs : `[`ItemGroupContext`](-item-group-context/index.md)<br>Central object that keeps track of episodes, batches, tasks and user-loaded properties. For tasks to be generated correctly, [episodes](-subs/episodes.md) and optionally [batches](-subs/batches.md) should be set. Set [release](-subs/release.md) if you wish to be able to differentiate between different releases when looking up properties. |
| [SubSync](-sub-sync/index.md) | `open class SubSync : `[`Sync`](https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/Sync.html)`, `[`SubTask`](-sub-task/index.md)<br>Wrapper task for [Sync](https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/Sync.html) which implements [SubTask](-sub-task/index.md), giving access to [SubTask.item](-sub-task/item.md) and [SubTask.batchItems](-sub-task/batch-items.md) for convenience. |
| [SubTask](-sub-task/index.md) | `interface SubTask : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)<br>Mixin task interface providing convenience functions for accessing [ItemGroup](-item-group/index.md) instances. |
| [SubZip](-sub-zip/index.md) | `open class SubZip : `[`Zip`](https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/bundling/Zip.html)`, `[`SubTask`](-sub-task/index.md)<br>Wrapper task for [Zip](https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/bundling/Zip.html) which implements [SubTask](-sub-task/index.md), giving access to [SubTask.item](-sub-task/item.md) and [SubTask.batchItems](-sub-task/batch-items.md) for convenience. |
| [Swap](-swap/index.md) | `open class Swap : `[`ASSTask`](-a-s-s-task/index.md)<br>Task to enable/disable honorifics and the like using the same syntax as [Daiz's autoswapper](https://github.com/Daiz/AegisubMacros#autoswapperlua---autoswapper). |
| [TaskContext](-task-context/index.md) | `class TaskContext : `[`BaseContext`](-base-context/index.md)<br>[AbstractContext](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html) implementation for getting properties in a task context. |
| [TaskCreator](-task-creator/index.md) | `class TaskCreator<T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`>`<br>Delegate for creation of tasks using property delegate syntax. See [ItemGroupContext.task](-item-group-context/task.md). |
| [TaskGroup](-task-group/index.md) | `class TaskGroup<T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> : `[`ItemGroup`](-item-group/index.md)`<`[`TaskProvider`](https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/TaskProvider.html)`<`[`T`](-task-group/index.md#T)`>>`<br>A group of tasks of the same type. |
| [Torrent](-torrent/index.md) | `open class Torrent : `[`AbstractArchiveTask`](https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/bundling/AbstractArchiveTask.html)`, `[`SubTask`](-sub-task/index.md)<br>Task to create a torrent file from a set of files. A predefined task instance can be accessed through [Subs.torrent](torrent.md). |
| [ValueClosure](-value-closure/index.md) | `class ValueClosure<T>` |
| [ValueGroup](-value-group/index.md) | `class ValueGroup<T> : `[`ItemGroup`](-item-group/index.md)`<`[`T`](-value-group/index.md#T)`>`<br>[ItemGroup](-item-group/index.md) that keeps track of simple values of type [T](-value-group/index.md#T). The closure is evaluated immediately for each entry. |
| [ZonedDateTimeSerializer](-zoned-date-time-serializer/index.md) | `object ZonedDateTimeSerializer : JsonSerializer<`[`ZonedDateTime`](https://docs.oracle.com/javase/9/docs/api/java/time/ZonedDateTime.html)`>` |

### Annotations

| Name | Summary |
|---|---|
| [MuxFlag](-mux-flag/index.md) | `annotation class MuxFlag`<br>Marks a property with its corresponding mkvmerge flag. |

### Extensions for External Classes

| Name | Summary |
|---|---|
| [kotlin.String](kotlin.-string/index.md) |  |
| [org.gradle.api.file.ConfigurableFileCollection](org.gradle.api.file.-configurable-file-collection/index.md) |  |
| [org.gradle.api.file.FileSystemLocationProperty](org.gradle.api.file.-file-system-location-property/index.md) |  |
| [org.gradle.api.Project](org.gradle.api.-project/index.md) |  |
| [org.gradle.api.provider.HasMultipleValues](org.gradle.api.provider.-has-multiple-values/index.md) |  |
| [org.gradle.api.provider.MapProperty](org.gradle.api.provider.-map-property/index.md) |  |
| [org.gradle.api.provider.Property](org.gradle.api.provider.-property/index.md) |  |
| [org.gradle.api.Task](org.gradle.api.-task/index.md) |  |

### Properties

| Name | Summary |
|---|---|
| [anidex](anidex.md) | `val `[`Subs`](-subs/index.md)`.anidex: `[`TaskGroup`](-task-group/index.md)`<`[`Anidex`](-anidex/index.md)`>`<br>Convenience property that upon use automatically instantiates and returns a [TaskGroup](-task-group/index.md) of type [Anidex](-anidex/index.md) with the name `anidex`. |
| [chapters](chapters.md) | `val `[`Subs`](-subs/index.md)`.chapters: `[`TaskGroup`](-task-group/index.md)`<`[`Chapters`](-chapters/index.md)`>`<br>Convenience property that upon use automatically instantiates and returns a [TaskGroup](-task-group/index.md) of type [Chapters](-chapters/index.md) with the name `chapters`. |
| [discord](discord.md) | `val `[`Subs`](-subs/index.md)`.discord: `[`TaskGroup`](-task-group/index.md)`<`[`Discord`](-discord/index.md)`>`<br>Convenience property that upon use automatically instantiates and returns a [TaskGroup](-task-group/index.md) of type [Discord](-discord/index.md) with the name `discord`. |
| [ftp](ftp.md) | `val `[`Subs`](-subs/index.md)`.ftp: `[`TaskGroup`](-task-group/index.md)`<`[`FTP`](-f-t-p/index.md)`>`<br>Convenience property that upon use automatically instantiates and returns a [TaskGroup](-task-group/index.md) of type [FTP](-f-t-p/index.md) with the name `ftp`. |
| [merge](merge.md) | `val `[`Subs`](-subs/index.md)`.merge: `[`TaskGroup`](-task-group/index.md)`<`[`Merge`](-merge/index.md)`>`<br>Convenience property that upon use automatically instantiates and returns a [TaskGroup](-task-group/index.md) of type [Merge](-merge/index.md) with the name `merge`. |
| [mux](mux.md) | `val `[`Subs`](-subs/index.md)`.mux: `[`TaskGroup`](-task-group/index.md)`<`[`Mux`](-mux/index.md)`>`<br>Convenience property that upon use automatically instantiates and returns a [TaskGroup](-task-group/index.md) of type [Mux](-mux/index.md) with the name `mux`. |
| [nyaa](nyaa.md) | `val `[`Subs`](-subs/index.md)`.nyaa: `[`TaskGroup`](-task-group/index.md)`<`[`Nyaa`](-nyaa/index.md)`>`<br>Convenience property that upon use automatically instantiates and returns a [TaskGroup](-task-group/index.md) of type [Nyaa](-nyaa/index.md) with the name `nyaa`. |
| [sftp](sftp.md) | `val `[`Subs`](-subs/index.md)`.sftp: `[`TaskGroup`](-task-group/index.md)`<`[`SFTP`](-s-f-t-p/index.md)`>`<br>Convenience property that upon use automatically instantiates and returns a [TaskGroup](-task-group/index.md) of type [SFTP](-s-f-t-p/index.md) with the name `sftp`. |
| [swap](swap.md) | `val `[`Subs`](-subs/index.md)`.swap: `[`TaskGroup`](-task-group/index.md)`<`[`Swap`](-swap/index.md)`>`<br>Convenience property that upon use automatically instantiates and returns a [TaskGroup](-task-group/index.md) of type [Swap](-swap/index.md) with the name `swap`. |
| [taskGroup](task-group.md) | `val <T : `[`Task`](https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html)`> `[`T`](task-group.md#T)`.taskGroup: `[`TaskGroup`](-task-group/index.md)`<`[`T`](task-group.md#T)`>`<br>The [TaskGroup](-task-group/index.md) instance this task belongs to. |
| [torrent](torrent.md) | `val `[`Subs`](-subs/index.md)`.torrent: `[`TaskGroup`](-task-group/index.md)`<`[`Torrent`](-torrent/index.md)`>`<br>Convenience property that upon use automatically instantiates and returns a [TaskGroup](-task-group/index.md) of type [Torrent](-torrent/index.md) with the name `torrent`. |
