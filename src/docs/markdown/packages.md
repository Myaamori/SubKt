# Module subkt

Tasks and utilities for automating fansubbing tasks with Gradle.
See https://github.com/Myaamori/SubKt/ for more information.

# Package myaa.subkt.tasks

This package contains facilities for defining fansubbing-related tasks using Gradle.

Of particular interest are:

* [Subs] - central extension tracking episodes, batches and tasks, and providing access to properties
* [glob] - basic brace expansion and globbing
* [ItemGroupContext] - facilities for creating groups of items (e.g. tasks) for multiple entries
* [ItemGroup] - represents a group of items (e.g. tasks) which can be accessed on an entry basis
* The extension functions to [org.gradle.api.Task]

The package provides the following Gradle tasks:

* [Merge] for merging ASS files
* [Chapters] for generating chapter files
* [Swap] for swapping honorifics and the like
* [ASS] for modifying ASS files
* [Mux] for muxing files using mkvmerge
* [FTP] for uploading files to an FTP server
* [SFTP] for uploading files to an SSH server
* [SSHExec] for executing commands on an SSH server
* [HTTP] for making HTTP(S) requests
* [Torrent] for generating a torrent file
* [Nyaa] for publishing to nyaa.si
* [Anidex] for publishing to anidex.info
* [Discord] for posting messages to a Discord webhook

In addition, simple wrappers of standard Gradle tasks implementing [SubTask] are available for convenience:

* [SubCopy]
* [SubExec]
* [SubSync]
* [SubZip]

# Package myaa.subkt.ass

Facilities for parsing ASS files.
The main point of interest is [ASSFile], which implements the actual parsing of raw ASS files.
Also provided are extensions for converting [java.awt.Color], [java.time.Duration] and [kotlin.Boolean] to and from ASS format.
