

Tasks and utilities for automating fansubbing tasks with Gradle.
See https://github.com/Myaamori/SubKt/ for more information.

### All Types

| Name | Summary |
|---|---|
| [myaa.subkt.tasks.AbstractTransferTask](../myaa.subkt.tasks/-abstract-transfer-task/index.md) |  |
| [myaa.subkt.ass.AegisubProjectGarbageSection](../myaa.subkt.ass/-aegisub-project-garbage-section/index.md) | Represents an Aegisub project garbage section. |
| [myaa.subkt.tasks.Anidex](../myaa.subkt.tasks/-anidex/index.md) | Task for uploading a torrent file to anidex.info. A predefined task instance can be accessed through [Subs.anidex](../myaa.subkt.tasks/anidex.md). |
| [myaa.subkt.tasks.ASS](../myaa.subkt.tasks/-a-s-s/index.md) | Generic task for modifying ASS files. See [ASSFile](../myaa.subkt.ass/-a-s-s-file/index.md) and related classes for more information. The modified file is written to a new file. |
| [myaa.subkt.ass.ASSFile](../myaa.subkt.ass/-a-s-s-file/index.md) | Represents an ASS file. If provided, will parse the given file. |
| [myaa.subkt.tasks.ASSTask](../myaa.subkt.tasks/-a-s-s-task/index.md) | Represents a task that outputs an ASS file. |
| [myaa.subkt.tasks.BaseContext](../myaa.subkt.tasks/-base-context/index.md) | Simple base implementation of a Velocity [AbstractContext](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html). |
| [kotlin.Boolean](../myaa.subkt.ass/kotlin.-boolean/index.md) (extensions in package myaa.subkt.ass) |  |
| [myaa.subkt.tasks.Chapters](../myaa.subkt.tasks/-chapters/index.md) | Generates a chapter file from an ASS file in the same way as Significance. The provided ASS file will be searched for lines where the field specified by [field](../myaa.subkt.tasks/-chapters/field.md) contains the value specified by [chapterMarker](../myaa.subkt.tasks/-chapters/chapter-marker.md), and for each such line a chapter will be generated using the start time as the time, and the value of the field specified by [chapterName](../myaa.subkt.tasks/-chapters/chapter-name.md) as the chapter name. A predefined task instance can be accessed through [Subs.chapters](../myaa.subkt.tasks/chapters.md). |
| [java.awt.Color](../myaa.subkt.ass/java.awt.-color/index.md) (extensions in package myaa.subkt.ass) |  |
| [myaa.subkt.tasks.ColorSerializer](../myaa.subkt.tasks/-color-serializer/index.md) |  |
| [org.gradle.api.file.ConfigurableFileCollection](../myaa.subkt.tasks/org.gradle.api.file.-configurable-file-collection/index.md) (extensions in package myaa.subkt.tasks) |  |
| [myaa.subkt.tasks.DefaultSubTask](../myaa.subkt.tasks/-default-sub-task/index.md) | Wrapper task for [DefaultTask](https://docs.gradle.org/current/javadoc/org/gradle/api/DefaultTask.html) which implements [SubTask](../myaa.subkt.tasks/-sub-task/index.md), giving access to [SubTask.item](../myaa.subkt.tasks/-sub-task/item.md) and [SubTask.batchItems](../myaa.subkt.tasks/-sub-task/batch-items.md) for convenience. |
| [myaa.subkt.tasks.Discord](../myaa.subkt.tasks/-discord/index.md) | Task for posting to a Discord webhook. Supports normal messages, embeds, and uploading files. A predefined task instance can be accessed through [Subs.discord](../myaa.subkt.tasks/discord.md). |
| [java.time.Duration](../myaa.subkt.ass/java.time.-duration/index.md) (extensions in package myaa.subkt.ass) |  |
| [myaa.subkt.tasks.ErrorMode](../myaa.subkt.tasks/-error-mode/index.md) | Used in some tasks to specify the failure mode. |
| [myaa.subkt.ass.EventLine](../myaa.subkt.ass/-event-line/index.md) | A line in an [EventSection](../myaa.subkt.ass/-event-section/index.md). Its associated accessor is [EventLineAccessor](../myaa.subkt.ass/-event-line-accessor/index.md). |
| [myaa.subkt.ass.EventLineAccessor](../myaa.subkt.ass/-event-line-accessor/index.md) | A type-safe accessor for [EventLine](../myaa.subkt.ass/-event-line/index.md). |
| [myaa.subkt.ass.EventSection](../myaa.subkt.ass/-event-section/index.md) | Represents an events section, containing a list of [EventLine](../myaa.subkt.ass/-event-line/index.md) lines. |
| [myaa.subkt.ass.ExtraData](../myaa.subkt.ass/-extra-data.md) |  |
| [org.gradle.api.file.FileSystemLocationProperty](../myaa.subkt.tasks/org.gradle.api.file.-file-system-location-property/index.md) (extensions in package myaa.subkt.tasks) |  |
| [myaa.subkt.tasks.Filterable](../myaa.subkt.tasks/-filterable/index.md) | Represents an item that can be optionally discarded. |
| [myaa.subkt.tasks.utils.FontReport](../myaa.subkt.tasks.utils/-font-report/index.md) |  |
| [myaa.subkt.ass.FormatSection](../myaa.subkt.ass/-format-section/index.md) | Represents a section containing a sequence of lines, starting with a `Format` line specifying the order of the fields on each line. |
| [myaa.subkt.tasks.FTP](../myaa.subkt.tasks/-f-t-p/index.md) | Task for uploading files via FTP. A predefined task instance can be accessed through [Subs.ftp](../myaa.subkt.tasks/ftp.md). |
| [org.gradle.api.provider.HasMultipleValues](../myaa.subkt.tasks/org.gradle.api.provider.-has-multiple-values/index.md) (extensions in package myaa.subkt.tasks) |  |
| [myaa.subkt.tasks.HTTP](../myaa.subkt.tasks/-h-t-t-p/index.md) | Task for sending general HTTP requests. Data should be sent using one of [json](../myaa.subkt.tasks/-h-t-t-p/json.md), [body](../myaa.subkt.tasks/-h-t-t-p/body.md) and [form](../myaa.subkt.tasks/-h-t-t-p/form.md). The response can be retrieved from [responseData](../myaa.subkt.tasks/-h-t-t-p/response-data.md) or [responseJson](../myaa.subkt.tasks/-h-t-t-p/response-json.md). |
| [myaa.subkt.tasks.ItemGroup](../myaa.subkt.tasks/-item-group/index.md) | A group of items of type [T](../myaa.subkt.tasks/-item-group/index.md#T). Has convenience functions for getting all items belonging to the same entry as a certain task, or all items corresponding to a specified list of entries. |
| [myaa.subkt.tasks.ItemGroupContext](../myaa.subkt.tasks/-item-group-context/index.md) | Context for defining groups of objects such as tasks. |
| [myaa.subkt.ass.KeyValField](../myaa.subkt.ass/-key-val-field/index.md) | Annotates a property in a [KeyValSection](../myaa.subkt.ass/-key-val-section/index.md) with the corresponding ASS key. |
| [myaa.subkt.ass.KeyValLine](../myaa.subkt.ass/-key-val-line/index.md) | Corresponds to a raw ASS line represented textually as `Type: Value`, with the value unparsed. |
| [myaa.subkt.ass.KeyValSection](../myaa.subkt.ass/-key-val-section/index.md) | Represents a generic key-value section such as [ScriptInfoSection](../myaa.subkt.ass/-script-info-section/index.md) and [AegisubProjectGarbageSection](../myaa.subkt.ass/-aegisub-project-garbage-section/index.md), where each line is given in in a `Key: Value` format, and each key is unique. |
| [myaa.subkt.ass.Line](../myaa.subkt.ass/-line/index.md) | A basic line with a type. |
| [myaa.subkt.ass.LineAccessor](../myaa.subkt.ass/-line-accessor/index.md) | Provides type-safe parametric access into a [MapLine](../myaa.subkt.ass/-map-line/index.md). |
| [myaa.subkt.ass.LineField](../myaa.subkt.ass/-line-field/index.md) | Annotates a property in a [MapLine](../myaa.subkt.ass/-map-line/index.md) with the name of the corresponding ASS field. |
| [myaa.subkt.ass.MapLine](../myaa.subkt.ass/-map-line/index.md) | A line in a [FormatSection](../myaa.subkt.ass/-format-section/index.md). The fields contained in this object can be accessed in three ways: |
| [org.gradle.api.provider.MapProperty](../myaa.subkt.tasks/org.gradle.api.provider.-map-property/index.md) (extensions in package myaa.subkt.tasks) |  |
| [myaa.subkt.tasks.Merge](../myaa.subkt.tasks/-merge/index.md) | Task to merge multiple ASS files into one. A predefined task instance can be accessed through [Subs.merge](../myaa.subkt.tasks/merge.md). |
| [myaa.subkt.tasks.utils.MkvAttachment](../myaa.subkt.tasks.utils/-mkv-attachment/index.md) |  |
| [myaa.subkt.tasks.utils.MkvAttachmentProperty](../myaa.subkt.tasks.utils/-mkv-attachment-property/index.md) |  |
| [myaa.subkt.tasks.utils.MkvChapter](../myaa.subkt.tasks.utils/-mkv-chapter/index.md) |  |
| [myaa.subkt.tasks.utils.MkvContainer](../myaa.subkt.tasks.utils/-mkv-container/index.md) |  |
| [myaa.subkt.tasks.utils.MkvContainerProperties](../myaa.subkt.tasks.utils/-mkv-container-properties/index.md) |  |
| [myaa.subkt.tasks.utils.MkvContainerPropertiesProgram](../myaa.subkt.tasks.utils/-mkv-container-properties-program/index.md) |  |
| [myaa.subkt.tasks.utils.MkvGlobalTag](../myaa.subkt.tasks.utils/-mkv-global-tag/index.md) |  |
| [myaa.subkt.tasks.utils.MkvInfo](../myaa.subkt.tasks.utils/-mkv-info/index.md) |  |
| [myaa.subkt.tasks.utils.MkvTrack](../myaa.subkt.tasks.utils/-mkv-track/index.md) |  |
| [myaa.subkt.tasks.utils.MkvTrackProperties](../myaa.subkt.tasks.utils/-mkv-track-properties/index.md) |  |
| [myaa.subkt.tasks.utils.MkvTrackTag](../myaa.subkt.tasks.utils/-mkv-track-tag/index.md) |  |
| [myaa.subkt.tasks.Mux](../myaa.subkt.tasks/-mux/index.md) | Task to mux a set of files into a single Matroska container using mkvmerge. |
| [myaa.subkt.tasks.MuxFlag](../myaa.subkt.tasks/-mux-flag/index.md) | Marks a property with its corresponding mkvmerge flag. |
| [myaa.subkt.tasks.Nyaa](../myaa.subkt.tasks/-nyaa/index.md) | Task for uploading a torrent file to nyaa.si. A predefined task instance can be accessed through [Subs.nyaa](../myaa.subkt.tasks/nyaa.md). |
| [myaa.subkt.tasks.OverwriteStrategy](../myaa.subkt.tasks/-overwrite-strategy/index.md) | The strategy for overwriting existing files. |
| [org.gradle.api.Project](../myaa.subkt.tasks/org.gradle.api.-project/index.md) (extensions in package myaa.subkt.tasks) |  |
| [org.gradle.api.provider.Property](../myaa.subkt.tasks/org.gradle.api.provider.-property/index.md) (extensions in package myaa.subkt.tasks) |  |
| [myaa.subkt.tasks.PropertyTask](../myaa.subkt.tasks/-property-task/index.md) | Parent task type that automatically keeps track of and stores properties in JSON format. |
| [myaa.subkt.tasks.ProviderGroup](../myaa.subkt.tasks/-provider-group/index.md) | [ItemGroup](../myaa.subkt.tasks/-item-group/index.md) that keeps track of providers for values of type [T](../myaa.subkt.tasks/-provider-group/index.md#T). A [Provider](https://docs.gradle.org/current/javadoc/org/gradle/api/provider/Provider.html) is generated for each entry, and the given closure is evaluated when the provider's value is requested. |
| [myaa.subkt.tasks.ProviderSerializer](../myaa.subkt.tasks/-provider-serializer/index.md) |  |
| [myaa.subkt.ass.ScriptInfoSection](../myaa.subkt.ass/-script-info-section/index.md) | Represents a script info section. |
| [myaa.subkt.ass.Section](../myaa.subkt.ass/-section/index.md) | Represents a section in an ASS file, textually represented as a section name in square brackets followed by zero or more lines in a `Type: Value` format. |
| [myaa.subkt.tasks.SFTP](../myaa.subkt.tasks/-s-f-t-p/index.md) | Task for uploading files via SFTP (SSH). A predefined task instance can be accessed through [Subs.sftp](../myaa.subkt.tasks/sftp.md). |
| [myaa.subkt.tasks.SSHExec](../myaa.subkt.tasks/-s-s-h-exec/index.md) | Task for executing commands on a remote shell via SSH. |
| [myaa.subkt.tasks.SSHTask](../myaa.subkt.tasks/-s-s-h-task/index.md) | Common interface for tasks that connect to SSH. |
| [kotlin.String](../myaa.subkt.ass/kotlin.-string/index.md) (extensions in package myaa.subkt.ass) |  |
| [kotlin.String](../myaa.subkt.tasks/kotlin.-string/index.md) (extensions in package myaa.subkt.tasks) |  |
| [myaa.subkt.ass.StyleLine](../myaa.subkt.ass/-style-line/index.md) | A line in a [StyleSection](../myaa.subkt.ass/-style-section/index.md). Its associated accessor is [StyleLineAccessor](../myaa.subkt.ass/-style-line-accessor/index.md). |
| [myaa.subkt.ass.StyleLineAccessor](../myaa.subkt.ass/-style-line-accessor/index.md) | A type-safe accessor for [StyleLine](../myaa.subkt.ass/-style-line/index.md). |
| [myaa.subkt.ass.StyleSection](../myaa.subkt.ass/-style-section/index.md) | Represents a styles section, containing a list of [StyleLine](../myaa.subkt.ass/-style-line/index.md) lines. |
| [myaa.subkt.tasks.SubCopy](../myaa.subkt.tasks/-sub-copy/index.md) | Wrapper task for [Copy](https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/Copy.html) which implements [SubTask](../myaa.subkt.tasks/-sub-task/index.md), giving access to [SubTask.item](../myaa.subkt.tasks/-sub-task/item.md) and [SubTask.batchItems](../myaa.subkt.tasks/-sub-task/batch-items.md) for convenience. |
| [myaa.subkt.tasks.SubExec](../myaa.subkt.tasks/-sub-exec/index.md) | Wrapper task for [Exec](https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/Exec.html) which implements [SubTask](../myaa.subkt.tasks/-sub-task/index.md), giving access to [SubTask.item](../myaa.subkt.tasks/-sub-task/item.md) and [SubTask.batchItems](../myaa.subkt.tasks/-sub-task/batch-items.md) for convenience. |
| [myaa.subkt.tasks.SubPlugin](../myaa.subkt.tasks/-sub-plugin/index.md) | Plugin that adds a [Subs](../myaa.subkt.tasks/-subs/index.md) instance as a DSL extension with the name "subs". |
| [myaa.subkt.tasks.SubProperties](../myaa.subkt.tasks/-sub-properties/index.md) | Reads a list of properties of the form `release.entry.property=value`, which can later be looked up using the [match](../myaa.subkt.tasks/-sub-properties/match.md) method. |
| [myaa.subkt.tasks.Subs](../myaa.subkt.tasks/-subs/index.md) | Central object that keeps track of episodes, batches, tasks and user-loaded properties. For tasks to be generated correctly, [episodes](../myaa.subkt.tasks/-subs/episodes.md) and optionally [batches](../myaa.subkt.tasks/-subs/batches.md) should be set. Set [release](../myaa.subkt.tasks/-subs/release.md) if you wish to be able to differentiate between different releases when looking up properties. |
| [myaa.subkt.tasks.SubSync](../myaa.subkt.tasks/-sub-sync/index.md) | Wrapper task for [Sync](https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/Sync.html) which implements [SubTask](../myaa.subkt.tasks/-sub-task/index.md), giving access to [SubTask.item](../myaa.subkt.tasks/-sub-task/item.md) and [SubTask.batchItems](../myaa.subkt.tasks/-sub-task/batch-items.md) for convenience. |
| [myaa.subkt.tasks.SubTask](../myaa.subkt.tasks/-sub-task/index.md) | Mixin task interface providing convenience functions for accessing [ItemGroup](../myaa.subkt.tasks/-item-group/index.md) instances. |
| [myaa.subkt.tasks.SubZip](../myaa.subkt.tasks/-sub-zip/index.md) | Wrapper task for [Zip](https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/bundling/Zip.html) which implements [SubTask](../myaa.subkt.tasks/-sub-task/index.md), giving access to [SubTask.item](../myaa.subkt.tasks/-sub-task/item.md) and [SubTask.batchItems](../myaa.subkt.tasks/-sub-task/batch-items.md) for convenience. |
| [myaa.subkt.tasks.Swap](../myaa.subkt.tasks/-swap/index.md) | Task to enable/disable honorifics and the like using the same syntax as [Daiz's autoswapper](https://github.com/Daiz/AegisubMacros#autoswapperlua---autoswapper). |
| [org.gradle.api.Task](../myaa.subkt.tasks/org.gradle.api.-task/index.md) (extensions in package myaa.subkt.tasks) |  |
| [myaa.subkt.tasks.TaskContext](../myaa.subkt.tasks/-task-context/index.md) | [AbstractContext](https://velocity.apache.org/engine/2.2/apidocs/org/apache/velocity/context/AbstractContext.html) implementation for getting properties in a task context. |
| [myaa.subkt.tasks.TaskCreator](../myaa.subkt.tasks/-task-creator/index.md) | Delegate for creation of tasks using property delegate syntax. See [ItemGroupContext.task](../myaa.subkt.tasks/-item-group-context/task.md). |
| [myaa.subkt.tasks.TaskGroup](../myaa.subkt.tasks/-task-group/index.md) | A group of tasks of the same type. |
| [myaa.subkt.tasks.Torrent](../myaa.subkt.tasks/-torrent/index.md) | Task to create a torrent file from a set of files. A predefined task instance can be accessed through [Subs.torrent](../myaa.subkt.tasks/torrent.md). |
| [myaa.subkt.tasks.ValueClosure](../myaa.subkt.tasks/-value-closure/index.md) |  |
| [myaa.subkt.tasks.ValueGroup](../myaa.subkt.tasks/-value-group/index.md) | [ItemGroup](../myaa.subkt.tasks/-item-group/index.md) that keeps track of simple values of type [T](../myaa.subkt.tasks/-value-group/index.md#T). The closure is evaluated immediately for each entry. |
| [myaa.subkt.tasks.ZonedDateTimeSerializer](../myaa.subkt.tasks/-zoned-date-time-serializer/index.md) |  |
