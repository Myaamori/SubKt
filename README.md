
# Table of Contents

1. [Table of Contents](#table-of-contents)
2. [What is SubKt?](#what-is-subkt)
3. [Quickstart Guide](#quickstart-guide)
4. [Manual](#manual)
    1. [Preliminaries](#preliminaries)
        1. [Defining tasks](#defining-tasks)
        2. [Using predefined tasks](#using-predefined-tasks)
        3. [Connecting tasks](#connecting-tasks)
    2. [Batch Tasks](#batch-tasks)
    3. [Flexible Configuration With External Properties](#flexible-configuration-with-external-properties)
    4. [Template Syntax](#template-syntax)
    5. [A Note on Task Properties](#a-note-on-task-properties)
    6. [Per-Task Variables (Or: Avoiding Having to Declare the Same Directory Structure Five Times Over)](#per-task-variables-or-avoiding-having-to-declare-the-same-directory-structure-five-times-over)
    7. [Kotlin Primer](#kotlin-primer)
        1. [Data Structures](#data-structures)
        2. [Nullable Types](#nullable-types)
        3. [Functions](#functions)
    8. [Example: Publishing to WordPress](#example-publishing-to-wordpress)
    9. [Example: Moving Repeat Task Configurations to an External Function](#example-moving-repeat-task-configurations-to-an-external-function)
    10. [Advanced Usage: Developing in IntelliJ IDEA](#advanced-usage-developing-in-intellij-idea)
    11. [Advanced Usage: Custom Tasks](#advanced-usage-custom-tasks)
    12. [Advanced Usage: Using Pure Gradle](#advanced-usage-using-pure-gradle)

# What is SubKt?

SubKt is a highly configurable framework for fansubbing that enables automation of tasks such as merging ASS files, muxing media files into a Matroska container, creating torrents, and even uploading files to seedboxes and posting Discord notifications.

SubKt makes no assumptions on the structure of your project or what steps are required to release it, while also making it as simple as possible to configure the toolchain according to your needs.

SubKt is based on Gradle, and by simply declaring the output of one task as the input of another, Gradle will automatically figure out which tasks depend on which other tasks, meaning there is often no need to manually specify the order of the tasks.

SubKt makes it possible to do everything from muxing to release in one go, with one single command.
Take the following complete example, where we merge ASS files, generate chapters, generate a second honorifics track, upload files to a seedbox, and publish the torrent, all by issuing one command.

```
$ ./gradlew startSeeding.02
> Task :chapters.02
> Task :merge.02
> Task :swap.02

> Task :mux.02
Output: Eizouken ni wa Te wo Dasu na! - 02 [D536A60F].mkv

> Task :ftp.02

> Task :torrent.02
Eizouken ni wa Te wo Dasu na! - 02 [D536A60F].mkv [02/Eizouken ni wa Te wo Dasu na! - 02 [D536A60F].mkv]

> Task :nyaa.02
Uploaded torrent: https://nyaa.si/view/[...] [Eizouken ni wa Te wo Dasu na! - 02 [D536A60F].mkv]

> Task :startSeeding.02
```

This was achieved using the below configuration:

```kotlin
subs {
    readProperties("sub.properties")
    episodes("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12")

    merge {
        from("$episode/eizouken_$episode.ass") {
            incrementLayer(10)
        }

        from(glob("$episode/eizouken$episode-ts*.ass"))

        from("songs/eizouken_OP.ass") {
            syncTo(getAs<Duration>("opshift"))
        }

        from("songs/eizouken_ED.ass") {
            syncTo(getAs<Duration>("edshift"))
        }
    }

    chapters {
        // chapters defined using Significance syntax
        from("$episode/eizouken_${episode}.ass")
    }

    swap {
        from(merge.item())
    }

    mux {
        title("Eizouken ni wa Te wo Dasu na! - $episode")

        from("$episode/eizouken${episode}_premux.mkv") {
            tracks {
                include(track.type == AUDIO || track.type == VIDEO)
                lang("jpn")
            }

            attachments {
                include(false)
            }
        }

        from(merge.item()) {
            tracks {
                name("English")
                lang("eng")
                default(true)
            }
        }

        from(swap.item()) {
            tracks {
                name("English (Honorifics)")
                lang("enm")
            }
        }

        chapters(chapters.item()) {
            lang("eng")
            charset("UTF-8")
        }

        attach("$episode/fonts") {
            includeExtensions("ttf", "otf")
        }

        // we don't know the CRC until after the file has been muxed,
        // so pass filename as a lambda (surrounded by braces)
        // which won't be evaluated until it's needed
        out { "$episode/Eizouken ni wa Te wo Dasu na! - $episode [$crc].mkv" }
    }

    torrent {
        trackers(getList("trackers"))
        from(mux.item())
        out("$episode/Eizouken ni wa Te wo Dasu na! - $episode.torrent")
    }

    nyaa {
        from(torrent.item())
        username(get("torrentuser"))
        password(get("torrentpass"))
        category(ANIME_ENGLISH)
        torrentDescription(getFile("mytemplate.vm"))
    }

    // shared configuration for both FTP tasks
    fun FTP.configure() {
        host(get("ftphost"))
        port(getAs<Int>("ftpport"))
        username(get("ftpuser"))
        password(get("ftppass"))
    }

    ftp {
        from(mux.item())
        into("/downloads")
        configure()
    }

    "startSeeding"<FTP> {
        // wait until files have been uploaded and torrent
        // has been published to start seeding
        dependsOn(ftp.item(), nyaa.item())

        from(torrent.item())
        into("/torrents")
        configure()
    }
}
```

In addition, an external `sub.properties` file, included at the top, contains the following:

```ini
02.opshift=0:04:06.55
02.edshift=0:23:39.48

trackers=http://nyaa.tracker.wf:7777/announce
torrentuser=nyaauser
torrentpass=nyaapass

ftphost=ftp.example.com
ftpport=980
ftpuser=username
ftppass=password
```

# Quickstart Guide

To use SubKt you will need:

* A [recent version of the JDK](https://www.oracle.com/uk/java/technologies/javase-downloads.html)
* [mkvmerge](https://mkvtoolnix.download/downloads.html)

Ensure that both `java` and `mkvmerge` are available on the PATH.
Instructions for setting the PATH for JDK 14 on Windows can be found [here](https://docs.oracle.com/en/java/javase/14/install/installation-jdk-microsoft-windows-platforms.html#GUID-96EB3876-8C7A-4A25-9F3A-A2983FEC016A).
The same procedure can be used to add `mkvmerge` to the PATH; take note of where you installed/extracted it.

Download the [project template](https://github.com/Myaamori/SubKt/releases/download/0.1.7/subkt-template-0.1.7.zip) and place the files in the directory where you keep your project files.
After you have modified `build.gradle.kts` and `sub.properties` as needed, you can run tasks using `gradlew`.
For instance, to run the `mux` task for episode `03`, navigate to the same directory as the `build.gradle.kts` file in a terminal, and run `./gradlew mux.03`.
On Windows, simply run `gradlew mux.03` from `cmd.exe`.

**Note**: The first time you run `gradlew` may take some time as Gradle as well as all dependencies need to be downloaded.
Future runs will be much quicker.

# Manual

A detailed description of the different tasks, functions and classes provided can be found in [the documentation](docs/subkt/index.md).

## Preliminaries

### Defining tasks

Generally all your tasks will be configured inside the `subs` block in the `build.gradle.kts` file.
In order for any tasks to be generated, you must first define the episodes.
The episodes can be any valid string, and do not need to be numerical.

```kotlin
subs {
    episodes("01", "02", "03", "OVA1", "OVA2")
}
```

You can now define a task:

```kotlin
subs {
    episodes("01", "02", "03", "OVA1", "OVA2")

    val myTask by task<DefaultSubTask> {
        doLast {
            println("This is task $currentTask for episode $episode.")
        }
    }
}
```

We have defined a group of tasks of type `DefaultSubTask` using the `task` function.
This will generate one task for each episode defined.
`DefaultSubTask` is a simple default type with no particular defined behavior.
We add a function to run when the task is executed using the `doLast` function.

We can list the resulting tasks using `./gradlew` (or simply `gradlew` on Windows):

```
$ ./gradlew tasks --all
[...]
Other tasks
-----------
myTask.01
myTask.02
myTask.03
myTask.OVA1
myTask.OVA2
[...]
```

Note that the first time you run `gradlew` will take some time, as Gradle needs to download all dependencies and compile SubKt.
Future runs will be much faster.

We can now execute the task you defined, here for episode 02:

```kotlin
$ ./gradlew myTask.02
[...]
> Task :myTask.02
This is task myTask for episode 02.

BUILD SUCCESSFUL in 1s
1 actionable task: 1 executed
```

Depending on your mood, you may use any of the following equivalent syntaxes to define a task:

```kotlin
val myTask by task<TaskType> {
}

val myTask by TaskType::class {
}

task("myTask", TaskType::class) {
}

task<TaskType>("myTask") {
}

"myTask"<TaskType> {
}
```

### Using predefined tasks

SubKt comes with a number of predefined tasks such as [Merge](docs/subkt/myaa.subkt.tasks/-merge/index.md), [Swap](docs/subkt/myaa.subkt.tasks/-swap/index.md), [Mux](docs/subkt/myaa.subkt.tasks/-mux/index.md) and [Nyaa](docs/subkt/myaa.subkt.tasks/-nyaa/index.md).
Let's have a look at how one would define a task to merge multiple ASS files:

```kotlin
subs {
    episodes("01", "02", "03", "04")

    val merge by task<Merge> {
        onStyleConflict(ErrorMode.FAIL)

        from("$episode/myshow_$episode-dialogue.ass") {
            incrementLayer(10)
        }

        from(glob("$episode/myshow_$episode-ts*.ass"))

        // also supports Merge Scripts templates
        // fromMergeTemplate("$episode/myshow_$episode-template.ass")

        out("$episode/myshow_$episode-merged.ass")
    }

}
```

The `from` function of the `Merge` task adds the given file as input to the merge operation.
You can optionally provide a function that can set properties on a [MergeSpecification](docs/subkt/myaa.subkt.tasks/-merge/-merge-specification/index.md) to e.g. increment layers or shift all lines.
The variable `episode` will contain the name of the current episode, so if you are executing the task `merge.03`, the input file to the first `from` call will evaluate to `03/myshow_03-dialogue.ass`.
For the second `from` call, we use the `glob` function which will return a list of all files that match the given pattern.
This is useful if you have e.g. multiple files containing typesetting from different typesetters.

In addition to defining inputs to a task, you can set properties that change the behavior of the task.
`onStyleConflict` governs what happens if two styles with the same name but different definitions are encountered---by default, the task will only emit a warning, but `ErrorMode.FAIL` instead tells it to abort if a conflict is detected.
The `out` property specifies the output filename.
If not specified, a default filename inside a `build` directory will be used.

```
$ ls 03/
myshow_03-dialogue.ass  myshow_03-ts-user1.ass  myshow_03-ts-user2.ass
$ ./gradlew merge.03
[...]
> Task :merge.03

BUILD SUCCESSFUL in 4s
1 actionable task: 1 executed
$ ls 03/
myshow_03-dialogue.ass  myshow_03-merged.ass  myshow_03-ts-user1.ass  myshow_03-ts-user2.ass
```

In addition to defining tasks using the `task` function, a number of tasks, including those mentioned above, come with predefined instances for convenience.
For instance, you can access a `Merge` task instance using the `merge` property:

```kotlin
subs {
    episodes(/* ... */)

    merge {
        from("dialogue.ass")
        from("OP.ass")
        // ...
    }
}
```

Similarly, properties such as `mux`, `nyaa`, `ftp` etc are available.
If you need to define multiple tasks of the same type, you will need to use the `task` manually for the additional tasks.
Make sure to provide a unique name for each such task.

See the documentation of the [myaa.subkt.tasks package](docs/subkt/myaa.subkt.tasks/index.md) for a complete list of available tasks, more comprehensive examples, and a full list of properties and functions available for each task.

### Connecting tasks

What if we want to use the output of one task as the input of another?
For instance, we may want to merge a set of files, and then run the `Swap` task on the resulting file to create a separate track with honorifics enabled.
Or we might want to use the output of a `Mux` task as the input to a `Torrent` task.

One option is to specify output filenames using the `out` property for each task, and then manually specify the same filename as the input to another task.
However, this is error prone (what if you misspell a filename?), and also means you need to manually declare what tasks depend on what other tasks using the `dependsOn` function.

There's a better solution:
You can use the actual task object itself as the input to another task.
That way you don't need to specify the filename manually, and Gradle will automatically realize that the input task needs to be run before the second task.
Consider the following example:

```kotlin
subs {
    // specify episodes etc...

    merge {
        from("$episode/dialogue.ass")
        from("$episode/typesetting.ass")
        out("$episode/merged.ass")
    }

    swap {
        from(merge.item())
        out("$episode/swapped.ass")
    }
}
```

By specifying `merge.item()` as the input, the dependency on the `Merge` task is automatically detected, and the two tasks will run in order:

```
$ ls 03/
dialogue.ass  typesetting.ass
$ ./gradlew swap.03
[...]
> Task :merge.03
> Task :swap.03

BUILD SUCCESSFUL in 1s
2 actionable tasks: 2 executed
$ ls 03/
dialogue.ass  merged.ass  swapped.ass  typesetting.ass
```

One important thing to note is the use of `merge.item()` as opposed to just `merge` when passing the task as input to the `Swap` task.
This is because `merge` actually represents a *group* of tasks, one for each episode defined through `subs.episodes`.
The `item()` function will return the specific task that belongs to the same episode as the task currently being run---so running `merge.item()` from the `swap.03` task will return the `merge.03` task.

## Batch Tasks

Above we have seen how to define tasks that operate on individual episode.
What if we would like to collect the output of tasks belonging to multiple different episodes?
For this, we first need to define a set of batches, specifying what episodes each batch contain:

```kotlin
subs {
    episodes("01", "02", "03", "04")
    batches(
            "vol1" to listOf("01", "02"),
            "vol2" to listOf("03", "04")
    )
}
```

Here, we've defined the batch named "vol1" to contain episodes "01" and "02", and "vol2" to contain episodes "03" and "04". (For an explanation of the syntax used, see the [Kotlin Primer](#kotlin-primer) section.)

By default, SubKt will only create episode tasks, not batch tasks.
In order for batch tasks to be generated, they must be configured inside a `batchtasks` block:

```kotlin
subs {
    episodes("01", "02", "03", "04")
    batches(
            "vol1" to listOf("01", "02"),
            "vol2" to listOf("03", "04")
    )

    mux {
        from("$episode/premux.mkv")
        from("$episode/merged.ass")
        out("$episode/My Show - $episode.mkv")
    }

    // torrent.01, torrent.02, torrent.03, torrent.04
    torrent {
        from(mux.item())
        out("$episode/My Show - $episode.torrent")
    }

    batchtasks {
        // define names of batches to use in folder name
        val batchNames = mapOf(
                "vol1" to "Vol. 1",
                "vol2" to "Vol. 2"
        )

        // torrent.vol1, torrent.vol2
        torrent {
            from(mux.batchItems())
            into("My Show - ${batchNames[batch]}")
            out("$batch/My Show - ${batchNames[batch]}.torrent")
        }
    }
}
```

The key here is the use of `mux.batchItems()`.
While `mux.item()` called from e.g. `torrent.vol1` would return the (non-existent) task `mux.vol1`, `batchItems()` instead returns all tasks belonging to the current batch---in `torrent.vol1` this means `mux.01` and `mux.02`.
If called from an episode task, `batchItems()` will return the same tasks as `item()`, since an episode task only corresponds to one single episode.
For instance, if you were to call `mux.batchItems()` from `torrent.01`, you would get a list containing a single task: `[mux.01]`.

As far as the `Torrent` task goes, one important aspect that differentiates the batch tasks from the episode tasks is the need to define a top level folder for the batch torrent (using the `into` function)---thus the need to configure the batch tasks separately.
However, it is also possible to define both episode and batch tasks in one go, by using the `alltasks` block.
To define batch-specific behavior, you can use the `isBatch` boolean:

```kotlin
subs {
    episodes("01", "02", "03", "04")
    batches(
            "vol1" to listOf("01", "02"),
            "vol2" to listOf("03", "04")
    )

    mux {
        from("$episode/premux.mkv")
        from("$episode/merged.ass")
        out("$episode/My Show - $episode.mkv")
    }

    alltasks {
        val batchNames = mapOf(
                "vol1" to "Vol. 1",
                "vol2" to "Vol. 2"
        )

        torrent {
            from(mux.batchItems())

            if (isBatch) {
                val batchName = batchNames[batch]
                into("My Show - $batchName")
                out("$batch/My Show - $batchName.torrent")
            } else {
                out("$episode/My Show - $episode.torrent")
            }
        }

        nyaa {
            from(torrent.item())
            username("username")
            password("password")
            category(NyaaCategories.ANIME_ENGLISH)
            torrentDescription("Description goes here")
        }
    }
}
```

Here, we rely on the fact that `mux.batchItems()` returns the same task as `mux.item()` for episode tasks.
Note also the use of `torrent.item()`---not `torrent.batchItems()`---in the `nyaa` task, since in e.g. `nyaa.vol1` we want to upload the torrent generated by `torrent.vol1`, not the two torrents generated by `torrent.01` and `torrent.02`.

When configuring both episode tasks and batch tasks together, you can also use the `entry` property which is an alias for `episode` or `batch` depending on the task type.
The `episodes` property is a list of the episodes belonging to the current task.

## Flexible Configuration With External Properties

While it is in principle possible to configure the tasks using only the `build.gradle.kts` file, it can get a bit cumbersome.
For instance, you may wish to use different values in certain tasks depending on the episode or batch, or you may want to use a different filename for a Blu-ray release than for a TV release.

External properties make it easy to define and override values on an episode/batch and release basis.
You define these in a separate file, using the following basic syntax:

```
release.entry.property=value
```

where `release` is set using the `subs.release` property (defaults to "default"), `entry` is the episode or batch name for the task the property is being accessed from, and `property` is the name used to access the property.
You can use a wildcard `*` to match parts of releases, entries or property names.
The group syntax `{a,b,c}` and range syntax `{01..06}` is also available.
You may leave out the release or both the release and the entry; missing parts will be interpreted as `*`, matching all values for the respective parts.

```
# equivalent to *.*.group=PAS
group=PAS

# define names for batches
vol1.name=Vol. 1
vol2.name=Vol. 2

# match all batch names that start with "vol"
vol*.torrentFile=My Show - $name.torrent

# empty version by default, override for episode 02 TV
version=
TV.02.version=v2

# define properties using groups
{01,03,05}.OP=OP1.ass
{02,04,06}.OP=OP2.ass

# define properties using ranges
{01..03}.ED=ED1.ass
{04..06}.ED=ED2.ass
```

You can use the following syntax for property values:

* `a|b|c` - a list containing `[a,b,c]`
* `{01..12}` - a list containing `[01,02,03,...,12]`
* `vol{1,2,3}` - a list containing `[vol1,vol2,vol3]`
* `01/typesetting-*.ass` - a list containing all files matching the pattern
* `!value` - the raw value `value`, without any processing

Further, you can reference other properties using `$propertyName`.
Values such as `$episode`, `$batch`, `$entry` and `$currentTask` are also available.
See [Template Syntax](#template-syntax) for more information on how property values and files are parsed.

Below is a more complete example.

```
group=PAS
show=Carole & Tuesday
showkey=ct

episodes={01..24}|12.5
batches=vol1|vol2
vol1.episodes={01..12}|12.5
vol2.episodes={13..24}
vol1.name=01-12
vol2.name=13-24

# source files
dialogue=$episode/$showkey $episode dialogue.ass
typesetting=$episode/$showkey $episode ts-*.ass
OP=songs/OP.ass
ED=songs/ED.ass
chapters=$episode/$showkey $episode qc.ass
video=$episode/$showkey$episode_premux.mkv
fonts=$episode/fonts

# output files
base=[$group] $show
filebase=$base - $episode$version [$mux.crc]
vol*.filebase=$base - $name

muxed=$filebase.mkv
torrent=$filebase.torrent

version=
TV.03.version=v2

# song shifts
01.opsync=0:03:28.20
02.opsync=0:02:11.34

01.edsync=0:20:53.52
02.edsync=0:21:48.90
```

Additionally, we may want to store more sensitive information in a separate file:

```
nyaauser=username
# disable globbing with ! (allows for special characters in password)
nyaapass=!password
```

You use the `readProperties` function to tell SubKit where to look for properties.
You can provide multiple files, and if any file is missing SubKit will emit a warning but not fail---so files with sensitive information do not necessarily need to be present to run tasks that don't need them.

To access these properties from the build script you can use the functions `get` and `getList`, which return a single string and a list of strings, respectively.
You can additionally use `getAs<Type>` and `getListAs<Type>` to cast values as a specific type (e.g. `Int` for integers).
Further, a `getFile` function is provided which takes a filename and returns the contents of that file with e.g. variables and other expressions parsed.

```kotlin
subs {
    // specify filename to read properties from
    readProperties("sub.properties", "private.properties")

    // set the release to the "release" argument specified
    // as -Prelease=value when running gradlew,
    // or default to "TV" if unspecified.
    // see Kotlin Primer section.
    release(arg("release") ?: "TV")

    // read episodes from properties
    episodes(getList("episodes"))

    // read batch information from properties;
    // see Subs.getMap documentation
    batches(getMap("batches", "episodes"))


    merge {
        from(get("dialogue")) {
            incrementLayer(10)
        }

        from(getList("typesetting"))

        from(get("OP")) {
            syncTo(getAs<Duration>("opsync"))
        }

        from(get("ED")) {
            syncTo(getAs<Duration>("edsync"))
        }
    }

    chapters {
        from(get("chapters"))
    }

    mux {
        from(get("video"))

        from(merge.item())

        chapters(chapters.item())

        attach(get("fonts")) {
            // case insensitive
            includeExtensions("ttf", "otf")
        }

        out(get("muxed"))
    }

    alltasks {
        torrent {
            from(mux.batchItems())

            if (isBatch) {
                into(get("filebase"))
            }

            out(get("torrent"))
        }

        nyaa {
            from(torrent.item())
            username(get("nyaauser"))
            password(get("nyaapass"))
            category(NyaaCategories.ANIME_ENGLISH)
            torrentDescription(getFile("$episode/description.txt"))
        }
    }
}
```

See the documentation on [Subs](docs/subkt/myaa.subkt.tasks/-subs/index.md) and [Task extension funcions](docs/subkt/myaa.subkt.tasks/-sub-task/index.md) for more information on ways to access external properties.
Note that the `entry` value is only available inside a task, so if you run e.g. `get` outside of a task, you cannot find properties that have entries specified, unless you manually provide an `entry` argument to the `get` call.

## Template Syntax

As briefly touched upon in the previous section, you can refer to variables using the `$variable` syntax from property values retrieved with `get` and related functions, as well as in files retrieved with `getFile`.
However, this is not all that is possible; SubKt makes use of Apache Velocity to parse the input, giving you access to e.g. variable management, expressions, if/else conditionals.
See the [User Guide](http://velocity.apache.org/engine/devel/user-guide.html) for an in-depth description of Velocity; we will cover some key points below.

In property values and template files retrieved with `getFile` you have access to the following variables:

* `$entry`, `$batch`, `$episode` - the current entry (episode/batch) of the task being run
* `$episodes` - a list of episodes corresponding to the current entry
* `$release` - the current release (set through `subs.release`)
* Tasks - e.g. `$nyaa.nyaaUrl` returns the `nyaaUrl` property of the `nyaa` task of the same entry (episode/batch) as the current task
* Properties loaded with `readProperties`

Consider, for instance, generating the description for a torrent as well as a blog post for an episode.
The structure of the descriptions may be largely the same for each episode: A title with the show name and episode, followed by a release picture, a staff list, and finally, for the blog post, a link to the torrent.
We can achieve this using `getFile` and properties.

We set up our tasks as follows:

```
nyaa {
    from(torrent.item())
    torrentDescription(getFile("description.txt"))
    // rest of the settings...
}

val wordpress by task<HTTP> {
    // settings for uploading to wordpress...

    form.put("content", getFile("description.txt"))
}
```

And in our `description.txt` we have:

```
# $show - $episode

![]($releasepic)

Staff list:
#include("$episode/staff_${episode}.txt")


#if($currentTask == "wordpress")
[Download here!]($nyaa.nyaaUrl)
#end
```

Note how we only include the download link if `getFile` was called from the `wordpress` task.

The descripion file references some external properties (`$show` and `$releasepic`).
In addition, it includes an episode-specific file (here containing a staff list) using the `#include` directive. (Note that Velocity will not parse the contents of this file---use `#parse` instead if you need to include a template file.)

In a `sub.properties` file, included using `subs.readProperties`, we may have something like

```
show=My Show
01.releasepic=https://i.imgur.com/i7mxCYg.png
```

And our `01/staff_01.txt` file might look something like

```
* TL: Someone
* Edit: Someone else
* Encode: Blah
```

And so, upon running the `wordpress.01` task, the resulting description ends up being:

```
# My Show - 01

![](https://i.imgur.com/i7mxCYg.png)

Staff list:
* TL: Someone
* Edit: Someone else
* Encode: Blah

[Download here!](https://nyaa.si/view/...)
```

## A Note on Task Properties

If you skim through the documentation, you may notice that most variables such as `Mux.title` or `Torrent.trackers` are of types like `Property<T>`, `ListProperty<T>` or `Provider<T>` for some type `T` (e.g. `String` or `Int`).
The value of a `Property` or a `Provider` cannot be accessed directly---in fact, it may not even be known yet.
To access a `Property` or a `Provider`, you must use the `Provider.get` method, at which point the current value will be calculated and returned.

Functions such as `Subs.get`, `Subs.getList`, `Subs.getFile` etc. also return `Provider` objects rather than returning raw strings or lists.
This is vital, as the retrieved property may depend on the output of a task that has not run yet (since tasks are configured before they are run).
By passing `Provider` objects instead, the values do not get evaluated until they are actually needed.
If you do need the actual value rather than a `Provider`, you can use e.g. `getFile("file.txt").get()` to immediately evaluate the `Provider`.

A `Property` cannot be set directly using normal assignment syntax (`=`).
Instead, you set a `Property` using the `Property.set` function:

```kotlin
mux {
    title.set("My Show")
}
```

However, for convenience, SubKt also overrides `invoke` for `Property` objects, so that you can set them using function invocation syntax:

```kotlin
mux {
    title("My Show") // equivalent to title.set
}
```

When setting a `Property` you can provide either a raw value (e.g. a string), a `Provider` that evaluates to the required type (e.g. a `Provider<String>`), or a function that returns the required type.

```kotlin
mux {
    title("My Show") // passing a string directly
    title(get("show")) // passing a Provider<String>
    title { "My Show" } // passing a function that returns a string
}
```

A function will be treated much like a `Provider`, and its value will not be evaluated until needed.

Similarly, you can use function invocation syntax to add items to a `ListProperty`:

```kotlin
torrent {
    trackers("a", "b", "c") // pass items as arguments
    trackers(listOf("d", "e")) // pass a list (Iterable)
    trackers(getList("trackers")) // pass a Provider<List<String>>
    trackers { listOf("i", "j") } // pass a function that returns a list
    trackers.add("k")
    trackers.addAll("l", "m", "n")

    println(trackers.get())
    // Output: [a, b, c, d, e, f, g, h, i, j, k, l, m, n]
}
```

For `MapProperty` objects, the following syntaxes are available:

```kotlin
val http by task<HTTP> {
    // pass pairs of String/String or String/Provider<String>
    form(
        "name" to "Aoi Miyamori",
        "title" to get("show")
    )

    // pass a Map
    form(mapOf("x" to "hello", "y" to "test"))

    // pass a function returning a Map
    form { mapOf("description" to "This is the description") }

    println(form.get())
    // Output: {name=Aoi Miyamori, title=SHIROBAKO, x=hello, y=test, description=This is the description}
}
```

Finally, you can use function invocation syntax to add files to a `ConfigurableFileCollection`; however, keep in mind that doing so will *remove any files added previously*, as `invoke` calls `setFrom`, which *sets* the files, rather than `from`, which *adds* files.

Sometimes you may find yourself needing to transform a `Provider` without actually evaluating it.
This can be done using the `map` function of `Provider` objects:

```kotlin
// create a provider of type Provider<String>
val stringProvider = project.provider {
    "1234"
}

// convert to Provider<Int> - but toInt() is not actually run until intProvider.get() is called
val intProvider = stringProvider.map { it.toInt() }
```

See [Kotlin Primer](#kotlin-primer) for more information on the syntax used.
You can read more about properties and providers in [the Gradle documentation](https://docs.gradle.org/current/userguide/lazy_configuration.html).

## Per-Task Variables (Or: Avoiding Having to Declare the Same Directory Structure Five Times Over)

You may find yourself in a situation where you have multiple files in different locations that you want to include in a single torrent, and that you additionally wish to copy to different locations.
For instance, you might have:

* Muxed files output by a set of `Mux` tasks
* Some bonus files inside a `batch/bonus` directory
* A README file

Let's say you wish to (1) create a torrent of these files, (2) upload them to an FTP server, and (3) copy them to a downloads directory for local seeding.
A naive approach may look like like thus:

```kotlin
batchtasks {
    torrent {
        from(mux.batchItems())
        from("$batch/bonus") {
            into("bonus")
        }
        from("$batch/README")

        into("My Show - Vol. 1")

        // configure torrent task
        // ...
    }

    ftp {
        from(mux.batchItems())
        from("$batch/bonus") {
            into("bonus")
        }
        from("$batch/README")

        into("/uploads/My Show - Vol. 1")

        // configure ftp task
        // ...
    }

    val copy by task<SubCopy> {
        from(mux.batchItems())
        from("$batch/bonus") {
            into("bonus")
        }
        from("$batch/README")

        into("/home/user/downloads/My Show - Vol. 1")
    }
}
```

However, this is incredibly tedious and repetitive.
Ideally we would like to define the file structure once and store it in a variable, which we can then apply inside each of these tasks.

The good news: Gradle provides a way to store a file copying specification as a variable out of the box, using the `project.copySpec` function.
The bad news: Since each episode requires copying different files, you can't simply create one variable with a single copying specification---you need to define multiple specifications, one for each episode.

This is where per-task values come in.
Using the `value` function, you can define a group of values---just like how the `task` function defines a group of tasks---which can then be accessed using the `item()` and `batchItems()` functions, just like task groups.
Let's see how this would work for our `copySpec` example:

```kotlin
val copySpecs = value {
    project.copySpec {
        from(mux.batchItems())
        from("$batch/bonus") {
            into("bonus")
        }
        from("$batch/README")

        into("My Show - Vol. 1")
    }
}
```

The function we pass to `value` will be executed once for each task in the current context (so e.g. for each batch inside `batchtasks`, or for each episode if used directly in a `subs` block).
Inside the `value` function, we have access to variables like `episode` and `batch`, and the `item` and `items` functions will automatically return the correct items corresponding to the current entry.

We can then apply this `copySpec` to our tasks using the `with` function:

```kotlin
batchtasks {
    val copySpecs = value {
        project.copySpec {
            from(mux.batchItems())
            from("$batch/bonus") {
                into("bonus")
            }
            from("$batch/README")

            into("My Show - Vol. 1")
        }
    }

    torrent {
        with(copySpecs.item())

        // configure torrent task
        // ...
    }

    ftp {
        with(copySpecs.item())

        into("/uploads")

        // configure ftp task
        // ...
    }

    val copy by task<SubCopy> {
        with(copySpecs.item())

        into("/home/user/downloads")
    }
}
```

Much nicer.

## Kotlin Primer

SubKt is written in Kotlin, which also means all build scripts are written in Kotlin as well.
While there are better resources for learning Kotlin, such as the [official documentation](https://kotlinlang.org/docs/reference/basic-syntax.html) and [Learn Kotlin by Example](https://play.kotlinlang.org/byExample/overview), we will here cover some basic topics that may be relevant to writing SubKt build scripts.

### Data Structures

Kotlin does not have built-in literals for creating lists and maps (dictionaries).
Instead, you use the functions `listOf`/`mutableListOf` to create a list and `mapOf`/`mutableMapOf` to create a map:

```kotlin
// immutable list - cannot be changed
val myList = listOf(1, 2, 3, 4)

// mutable list - can be changed e.g. using the add function
val myMutableList = mutableListOf("hello", "test")


val myMap = mapOf(
    "key1" to "value1",
    "key2" to "value2"
)

println(myMap["key1"])
// Output: value1
```

One note regarding the syntax for creating maps:
`mapOf` is a function that takes a list of `Pair` objects.
The syntax `x to y` is equivalent to writing `Pair(x, y)`.
Thus, the above map could equivalenty be written as

```kotlin
val myMap = mapOf(
    Pair("key1", "value1"),
    Pair("key2", "value2")
)
```

### Nullable Types

Kotlin distinguishes between variables that can be assigned `null`---so-called "nullable" types---and variables that can never be `null`.
Nullable types are written by adding a question mark after the type name: `String?` is a nullable string which may contain either an actual string or the value `null`, while a variable of type `String` may never contain `null`.

One example of a function that returns a nullable type is the `subs.arg` function which has return type `String?`: In the event that the requested argument is not set, the function will return `null`.

You are severely limited in what you can do with a variable with a nullable type.
For instance, if we declare a nullable integer and try to add another number, Kotlin will complain about addition with a nullable type (since the variable could possibly be `null`, and addition with `null` doesn't mean anything):

```kotlin
>>> val a: Int? = 3
>>> a + 5
error: operator call corresponds to a dot-qualified call 'a.plus(5)' which is not allowed on a nullable receiver 'a'.
a + 5
  ^
```

There are a few ways to deal with nullable types.
The best way is to manually check if the variable is `null`, and handle

```kotlin
>>> if (a != null) {
...   println(a + 5)
... } else { /* handle null case */ }
8
```

The special operator `?:` allows you to consisely define a default value if a variable or expression is `null`:

```kotlin
// these two statements are equivalent: if a is null, b is assigned 10
val b = a ?: 10
val b = if (a == null) 10 else a
```

`b` here is automatically assigned the non-nullable type `Int`, since Kotlin knows that it cannot be `null`.

If you know for sure that a variable is not `null`, you can force it to a non-nullable type using the operator `!!`.
This will result in a runtime error if it turns out that the variable was, in fact, `null` after all.

```kotlin
val b: Int = a!!
```

### Functions

A function in Kotlin is defined using the `fun` keyword:

```kotlin
fun add(a: Int, b: Int): Int {
    return a + b
}

println(add(10, 5))
// Output: 15
```

Additionally, you can define lambdas, or anonymous functions, using braces:

```kotlin
val add = { a: Int, b: Int ->
    a + b
}
```

In a lambda, there is no need for an explicit `return` statement: The last expression (line) of the lambda will be taken to be the return value.
In this case, our `add` variable has the type `(Int, Int) -> Int`: A function that takes two `Int` arguments and returns an `Int`.

If the type of the arguments of a lambda can be inferred from the context, there is no need to explicitly specify the type.
Further, if a lambda only takes a single argument, you don't need to specify the arguments at all, and `it` will be used as the name of the argument:

```kotlin
val incrementByTen: (Int) -> Int = {
    it + 10
}

println(incrementByTen(5))
// Output: 15
```

In a lot of places you will find functions that accept other functions as arguments.
This may look as follows:

```kotlin
fun increment(value: Int, callback: (Int) -> Unit) {
    callback(value + 5)
}
```

This function takes one integer as well as one function.
The function argument should take one integer as input, and return nothing (`Unit` is Kotlin's equivalent to `void` in many other languages).
We can call this function as:

```kotlin
increment(10, {
    println(it)
})
```

However, one feature of Kotlin is that if you are passing a lambda as the last argument of a function, you can pass it *outside* the parentheses:

```kotlin
increment(10) {
    println(it)
}
```

These three calls are all equivalent:

```kotlin
myFunction({
    println(it)
})

myFunction() {
    println(it)
}

myFunction {
    println(it)
}
```

## Example: Publishing to WordPress

The provided `HTTP` task allows you to issue various HTTP(S) requests.
Here we will be using it to craft requests for creating release posts on a WordPress blog.
This specific example assumes the use of the [Application Passwords](https://wordpress.org/plugins/application-passwords/) plugin for authenticating requests.

We will create two tasks: One for uploading a release picture to the WordPress site, and another that creates the actual posts, which includes the aformentioned release picture.

We'll begin by setting up our external properties:

```
bloghost=blog.example.com
bloguser=username
# the password generated by Application Passwords
blogpass=password

blogpic=$episode/wordpress_releasepic_${episode}.png
blogtitle=$show - $episode
```

To upload the release image we will make use of WordPress's REST API.
The endpoint we need to access is `/wp-json/wp/v2/media`, to which we will make a `POST` request with the image as the request body, and the `Content-Disposition` header set to attachment.
We will also need to use so-called basic authentication, as required by Application Passwords, to authenticate the requests---this is done by setting the `basicAuthUser` and `basicAuthPass` properties.

```kotlin
val uploadReleaseImage by task<HTTP> {
    host(get("bloghost"))
    endpoint("/wp-json/wp/v2/media")
    method("POST")
    attachment(get("blogpic"))
    basicAuthUser(get("bloguser"))
    basicAuthPass(get("blogpass"))
}
```

The task will take care of setting the `Content-Disposition` header if the `attachment` property is set.
The response can be accessed through `uploadReleaseImage.responseJson`.
We will make use of it in a file called `blogpost.txt`, which contains the contents of our release post:

```
#set($img = $uploadReleaseImage.responseJson["source_url"])
<a href="$img"><img src="$img" /></a>

#include("$episode/blogpost_${episode}.txt")

[Download here!]($nyaa.nyaaUrl)
```

The file retrieves the uploaded file from the `uploadReleaseImage` task, includes an episode-specific description, and finally provides a download link.

In order for this to work properly we need to explicitly define `uploadReleaseImage` and `nyaa` as dependencies, as Gradle cannot automatically find the task references in the `blogpost.txt` file.
Other than that, our post publishing task is very similar to `uploadReleaseImage`, apart from the endpoint and message body being different.

```kotlin
val publishWordpress by task<HTTP> {
    dependsOn(nyaa.item(), uploadReleaseImage.item())

    host(get("bloghost"))
    endpoint("/wp-json/wp/v2/posts")
    method("POST")
    basicAuthUser(get("bloguser"))
    basicAuthPass(get("blogpass"))

    form(
        "title" to get("blogtitle"),
        "content" to getFile("blogpost.txt"),
        "status" to "draft"
    )

    doLast {
        println(responseJson.asJsonObject["link"].asString)
    }
}
```

We set the status to "draft", so we can double-check the post before publishing it.
If you wanted to publish it straight away regardless, you could instead set the status to "publish".
As a final step, after the task has finished, we also retrieve the "link" field of the response and print it, providing us quick access to the created post.

## Example: Moving Repeat Task Configurations to an External Function

Sometimes you may find yourself repeating the same configuration in multiple tasks of the same type.
Consider for example setting up two FTP tasks, one to transfer the muxed files and one to transfer the torrent file:

```kotlin
val uploadFiles by task<FTP> {
    host(get("ftphost"))
    port(getAs<Int>("ftpport"))
    username(get("ftpuser"))
    password(get("ftppass"))
    useSsl(false)
    overwriteIf(OverwriteStrategy.DIFFERENT_SIZE_OR_SOURCE_NEWER)
    certificateVerificationMode(CertificateMode.ACCEPT_ALL)

    from(mux.item())
    into("/uploads")
}

val uploadTorrent by task<FTP> {
    host(get("ftphost"))
    port(getAs<Int>("ftpport"))
    username(get("ftpuser"))
    password(get("ftppass"))
    useSsl(false)
    overwriteIf(OverwriteStrategy.DIFFERENT_SIZE_OR_SOURCE_NEWER)
    certificateVerificationMode(CertificateMode.ACCEPT_ALL)

    from(torrent.item())
    into("/torrents")
}
```

One way to deal with the repetition is to define a separate function:

```kotlin
fun FTP.configure() {
    host(get("ftphost"))
    port(getAs<Int>("ftpport"))
    username(get("ftpuser"))
    password(get("ftppass"))
    useSsl(false)
    overwriteIf(OverwriteStrategy.DIFFERENT_SIZE_OR_SOURCE_NEWER)
    certificateVerificationMode(CertificateMode.ACCEPT_ALL)
}
```

Note the `FTP` in `fun FTP.configure`, indicating that the *receiver*---the `this` reference inside the function---should be an `FTP` task instance.
See [Extensions](https://kotlinlang.org/docs/reference/extensions.html) and [Function literals with receiver](https://kotlinlang.org/docs/reference/lambdas.html#function-literals-with-receiver) for more information.

Now we can simply call this `configure` function from both of the `FTP` tasks:

```kotlin
val uploadFiles by task<FTP> {
    configure()

    from(mux.item())
    into("/uploads")
}

val uploadTorrent by task<FTP> {
    configure()

    from(torrent.item())
    into("/torrents")
}
```

## Advanced Usage: Developing in IntelliJ IDEA

You may find it helpful to develop your build scripts in JetBrains's [IntelliJ IDEA](https://www.jetbrains.com/idea/), which provides syntax highlighting, autocompletion, interactive error checking, and more.
Importing a SubKt project into IntelliJ is simple.
Ensure that the Gradle and Kotlin plugins are installed, and then:

![](https://raw.githubusercontent.com/Myaamori/SubKt/master/docs/img/intellij_import.png)

Select "Open or Import", or "File" > "Open" if you already have a project open.

![](https://raw.githubusercontent.com/Myaamori/SubKt/master/docs/img/intellij_pick_build.png)

Navigate to the `build.gradle.kts` file for the project.

![](https://raw.githubusercontent.com/Myaamori/SubKt/master/docs/img/intellij_open_project.png)

Select "Open as Project".

![](https://raw.githubusercontent.com/Myaamori/SubKt/master/docs/img/intellij_gradle_refresh.png)

If the Gradle panel is not already open, open it by clicking on the tab in the top right.
You should find a list of available tasks in the "Tasks" > "other" category for the project.
If you add new tasks, click on the reload button for IntelliJ to rescan the project.

## Advanced Usage: Custom Tasks

You are, of course, not restricted to only what built-in tasks are capable of doing---you can define your own tasks with completely custom behavior.
The easiest way to do this is to create a new task of type `DefaultSubTask`, and adding code to run when the task is executed using `doFirst`/`doLast`.
Using this approach, you must explicitly specify your dependencies up-front, as they cannot be detected automatically.
For instance, say you wish to create a task to remove all comment lines from an ASS file.
One way is to define a task that opens some input file, parses it using `ASSFile`, removes the lines in question, and finally writes the result to some output file.

```kotlin
val removeComments by task<DefaultSubTask> {
    // must declare dependency explicitly
    dependsOn(merge.item())

    doLast {
        // get output of merge task
        val file = project.files(merge.item()).singleFile

        // parse ASS and remove comments
        val assFile = myaa.subkt.ass.ASSFile(file)
        assFile.events.lines.removeAll { it.comment }

        // write output
        outputFile = project.file("$episode/nocomments_$episode.ass")
        outputFile.writeText(assFile.serialize().joinToString(""))

        // save output file reference so it's accessible elsewhere
        extra["outputFile"] = outputFile
    }
}

mux {
    // ...

    // get outputFile from removeComments task
    from(removeComments.item().map { it.extra["outputFile"]!! })

    // ...
}
```

Note that we have to manually handle the output file of the task, as `DefaultSubTask` does not define any outputs.

Alternatively, you could write your own task type.
This works the same as writing any Gradle task type, but for better interoperability with the rest of SubKt you should make sure to inherit from `DefaultSubTask` or `SubTask`.

For instance, if we were to define our task for removing comments as its own type, it might look something like

```kotlin
open class RemoveComments : DefaultSubTask() {
    @get:InputFiles
    val from = project.objects.fileCollection()

    @get:OutputFiles
    val out = project.objects.fileCollection()

    @TaskAction
    fun run() {
        val file = from.singleFile
        val assFile = myaa.subkt.ass.ASSFile(file)
        assFile.events.lines.removeAll { it.comment }

        val outputFile = out.singleFile
        outputFile.writeText(assFile.serialize().joinToString(""))
    }
}
```

We can then define a task of this type as normal.

```kotlin
val removeComments by task<RemoveComments> {
    from(merge.item())
    out("$episode/nocomments_$episode.ass")
}

mux {
    // ...

    from(removeComments.item())

    // ...
}
```

Since our `RemoveComments` task has explicitly defined inputs and outputs, Gradle the outputs can be automatically detected when passed as input to e.g. `mux.from`, and Gradle will be able to skip the task if it's rerun with the same inputs.

See [Build Script Basics](https://docs.gradle.org/current/userguide/tutorial_using_tasks.html), [Authoring Tasks](https://docs.gradle.org/current/userguide/more_about_tasks.html), [Developing Custom Gradle Task Types](https://docs.gradle.org/current/userguide/custom_tasks.html) and [Lazy Configuration](https://docs.gradle.org/current/userguide/lazy_configuration.html) in the Gradle documentation for more information.

## Advanced Usage: Using Pure Gradle

If you're familiar with Gradle already and would rather skip all the abstraction layers, you can declare tasks using `project.tasks.register` and related functions as normal.
For example, the following code generates tasks for different episodes much like using task groups would, using pure Gradle.

```kotlin
for (episode in (1..12).map { "%02d".format(it) }) {
    val merge = tasks.register<Merge>("merge.$episode") {
        from("$episode/dialogue.ass")
        from("$episode/typesetting.ass")
    }

    val mux = tasks.register<Mux>("mux.$episode") {
        from("$episode/video.mkv")
        from(merge)
        out("$episode/muxed.mkv")
    }
}
```

For this usage there is no need to actually apply the `myaa.subkt` plugin; you can simply define the `myaa:subkt` package as a buildscript dependency.
Note that doing so means you will not have access to functionality provided by the `subs` object, such as external properties and task groups.
You will also need to handle e.g. batching manually.
