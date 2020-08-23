package myaa.subkt.tasks.samples

import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import myaa.subkt.ass.*
import myaa.subkt.tasks.*
import myaa.subkt.ass.EventLineAccessor
import myaa.subkt.ass.assTime
import myaa.subkt.tasks.*
import myaa.subkt.tasks.Anidex.*
import myaa.subkt.tasks.Mux.*
import myaa.subkt.tasks.Nyaa.*
import org.gradle.api.NamedDomainObjectProvider
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.kotlin.dsl.invoke
import java.awt.Color
import java.time.Duration

fun Subs.glob(s: String) = project.glob(s)

fun Subs.mergeSample() {
    merge {
        from("dialogue.ass") {
            incrementLayer(10)
        }
        from("OP.ass") {
            syncField(EventLineAccessor.ACTOR)
            syncTo("0:10:24.66".assTime)
        }
        from(glob("typesetting-*.ass"))
        onScriptInfoConflict(ErrorMode.FAIL)
        namespaceStyles(true)
    }
}

fun Merge.mergeFromSample() {
    from("file1.ass", "file2.ass") {
        shiftBy(Duration.ofSeconds(10))
    }
}

fun Merge.mergeScriptInfoSample() {
    scriptInfo {
        playResX = 1920
        playResY = 1080
    }
}

fun Mux.muxAttachSample() {
    attach("fonts") {
        includeExtensions("otf", "ttf") // case insensitive
        include("*.ttf", "*.otf") // case sensitive
    }
}

fun Mux.muxChaptersSample() {
    chapters("chapters.xml") {
        lang("eng")
    }
}

fun Mux.muxFromSample() {
    from("premux.mkv") {
        includeChapters(false)

        tracks {

        }

        attachments {
            // remove all attachments from the source file
            include(false)
        }
    }
}

fun Subs.muxGlobalOptionsSample() {
     mux {
         globalOptions("--segmentinfo", "segmentinfo.xml")
     }
}

fun Mux.muxFileAttachmentsSample() {
    from("video.mkv") {
        attachments {
            // don't include any attachments from the original file
            include(false)
        }
    }
}

fun Mux.muxFileTracksSample() {
    from("video.mkv") {
        tracks {
            include(track.type == TrackType.VIDEO || track.type == TrackType.AUDIO)
        }

        video {
            name("Video")
        }

        audio(0) {
            name("English Audio")
            lang("eng")
        }

        audio(1) {
            name("Japanese Audio")
            lang("jpn")
        }
    }

    from("subtitles.ass") {
        subtitles {
            lang("eng")
            default(true)
            name("English")
        }
    }
}

fun Mux.muxFileFileOptionsSample() {
    from("video.mkv") {
        fileOptions("--no-track-tags")

        tracks {
            fileOptions("--cues", "$id:all")
        }
    }
}

fun Subs.muxSample() {
    mux {
        title("My Show - 01")
        forceCRC("DEADBEEF")

        from("video.mkv") {
            includeChapters(false)

            tracks {
                include(track.type == TrackType.VIDEO)
                name("Video")
                default(true)
            }

            attachments {
                include(false)
            }
        }

        from("audio.aac") {
            tracks {
                name("Audio")
                lang("jpn")
                default(true)
            }
        }

        from("commentary.aac") {
            tracks {
                name("Commentary")
                lang("jpn")
                delay(1000)
            }
        }

        from("subtitles.ass") {
            tracks {
                name("English")
                lang("eng")
                default(true)
            }
        }

        chapters("chapters.txt") {
            lang("eng")
        }

        attach("fonts") {
            includeExtensions("ttf", "otf")
        }

        out("My Show - 01.mkv")
    }
}

fun Subs.chapterSample() {
    swap {
        from("dialogue.ass")
        styles(listOf("Default", "Default-Alt"))
    }
}

fun Subs.assSample() {
    "removeLines"<ASS> {
         from("dialogue.ass")

         ass {
             // remove lines with a start time of greater than five minutes
             events.lines.removeIf { line ->
                 line.start > Duration.ofMinutes(5)
             }
         }
     }
}

fun Subs.discordSample() {
    discord {
        webhook("https://discordapp.com/api/webhooks/...")
        username("Gradle")
        content("This is a message")
        attachment(project.file("attachment.txt"))

        embed {
            title("Embed title")
            description("Embed description")
            color(Color.RED)

            image {
                url("https://i.imgur.com/ecfjrQj.png")
                height(480)
            }
        }
    }
}

fun Subs.torrentSample(mux: TaskGroup<Mux>) {
    torrent {
        trackers("http://tracker.example.com:7777/announce",
                 "udp://tracker.example.info:1337/announce")
        from(mux.batchItems())
        // copy mkv files in the 01/extra directory into a "bonus" directory
        from("01/extra") {
            include("*.mkv")
            into("bonus")
        }
        // root directory of torrent
        into("My Show - 01")
        out("My Show - 01.torrent")
    }
}

fun Subs.nyaaSample(torrent: TaskGroup<Torrent>) {
    nyaa {
        from(torrent.item())
        username("username")
        password("password")
        category(NyaaCategories.ANIME_ENGLISH)
        torrentName("[Group] My Show - 01.mkv")
        torrentDescription("This is the *description*.")
        information("#channel @ irc.rizon.net")
        hidden(true)
    }
}

fun Subs.anidexSample(torrent: TaskGroup<Torrent>) {
    anidex {
        from(torrent.item())
        apiKey("your-key-goes-hre")
        category(AnidexCategories.ANIME_SUB)
        lang(AnidexLanguage.ENGLISH)
        torrentName("[Group] My Show - 01.mkv")
        torrentDescription("This is the *description*.")
        hidden(true)
    }
}

fun Subs.ftpSample(mux: TaskGroup<Mux>) {
    ftp {
        from(mux.batchItems())
        // copy mkv files in the 01/extra directory into a "bonus" directory
        from("01/extra") {
            include("*.mkv")
            into("bonus")
        }
        // directory to copy to
        into("/Downloads/My Show - 01")

        host("ftp.example.com")
        port(980)
        username("username")
        password("password")
        certificateVerificationMode(FTP.CertificateMode.ACCEPT_ALL)
        overwriteIf(OverwriteStrategy.DIFFERENT_SIZE_OR_SOURCE_NEWER)
    }
}

fun Subs.sftpSample(mux: TaskGroup<Mux>) {
    sftp {
        from(mux.batchItems())
        // copy mkv files in the 01/extra directory into a "bonus" directory
        from("01/extra") {
            include("*.mkv")
            into("bonus")
        }
        // directory to copy to
        into("Downloads/My Show - 01")

        host("ssh.example.com")
        // identity file, username and port specified in config file
        // defaults to ~/.ssh/config
        config("sshconfig")
        // file with fingerprints for verifying the host
        // defaults to ~/.ssh/known_hosts
        knownHosts("known_hosts")
        overwriteIf(OverwriteStrategy.ALWAYS)
    }
}

fun Subs.sshExecSample() {
    "sshexec"<SSHExec> {
        host("ssh.example.com")
        // identity file, username and port specified in config file
        // defaults to ~/.ssh/config
        config("sshconfig")
        // file with fingerprints for verifying the host
        // defaults to ~/.ssh/known_hosts
        knownHosts("known_hosts")

        command("ls")
    }
}

fun Subs.httpSample() {
    "http"<HTTP> {
        host("example.com")
        endpoint("/api/v2/post")
        port(8080)
        https(false)
        method("POST")
        json(
                "content" to "This is the content",
                "author" to "Jane Doe"
        )

        // equivalently:
        contentType(ContentType.Application.Json)
        body("{\"content\":\"This is the content\",\"author\":\"Jane Doe\"}")

        doLast {
            println(responseData)
        }
    }
}

fun Project.globSample() {
    glob("{a,b,c}")
    // Output: [a, b, c]

    glob("test{a,b,c}")
    // Output: [testa, testb, testc]

    glob("{1..5}")
    // Output: [1, 2, 3, 4, 5]

    glob("{01..05}")
    // Output: [01, 02, 03, 04, 05]

    glob("test{01..05}hello")
    // Output: [test01hello, test02hello, test03hello, test04hello, test05hello]

    glob("{01,02}/*_qc.ass")
    // Output: [01/eizouken_01_qc.ass, 02/eizouken_02_qc.ass]
}

fun Task.taskEvaluateSample() {
    // from task mux.03
    evaluate("\$currentTask - \$episode - \$merge.out.get()")
    // Output: mux - 03 - build/merge.03.ass
}

fun Task.taskGetRawSample() {
    // from task mux.03
    // property file: TV.03.value=hello{1..3}|test|$episode
    getRawMaybe("value")
    // Output: hello{1..3}|test|$episode
}

fun Task.taskGetListSample() {
    // from task mux.03
    // property file: TV.03.value=hello{1..3}|test|$episode
    getList("value")
    // Output: [hello1, hello2, hello3, test, 03]
}

fun Task.taskGetListAsSample() {
    // from task mux.03
    // property file: TV.03.values={1..5}
    getListAs<Int>("values")
    // Output: [1, 2, 3, 4, 5] (type: List<Int>)
}

fun Task.taskGetSample() {
    // from task mux.03
    // property file: TV.03.value=$release/$episode/show_${episode}.ass
    get("value")
    // Output: TV/03/show_03.ass
}

fun Task.taskGetAsSample() {
    // from task mux.03
    // property file: TV.03.value=10
    getAs<Int>("value")
    // Output: 10 (type: Int)
}

fun Task.taskGetFileSample() {
    getFile("02/release_post.txt")
}

fun Subs.subsEvaluateSample() {
    evaluate("\$release - \$episode - \$merge.out.get()")
    // Output: TV - $episode - $merge.out.get()
}

fun Subs.subsGetRawSample() {
    // property file: TV.*.value=hello{1..3}|test|$episode
    getRaw("value")
    // Output: hello{1..3}|test|$episode
}

fun Subs.subsGetListSample() {
    // property file: TV.*.value=hello{1..3}|test|$episode
    getList("value")
    // Output: [hello1, hello2, hello3, test, $episode]
}

fun Subs.subsGetListAsSample() {
    // property file: TV.*.values={1..5}
    getListAs<Int>("values")
    // Output: [1, 2, 3, 4, 5] (type: List<Int>)
}

fun Subs.subsGetSample() {
    // property file: TV.*.value=$release/$episode/show_${episode}.ass
    get("value")
    // Output: TV/$episode/show_${episode}.ass
}

fun Subs.subsGetAsSample() {
    // property file: TV.*.value=10
    getAs<Int>("value")
    // Output: 10 (type: Int)
}

fun Subs.subsGetFileSample() {
    getFile("info.txt")
}

fun subsSample(subs: NamedDomainObjectProvider<Subs>) {
    subs {
        readProperties("sub.properties")
        // read the release argument specified as -Prelease=value when invoking Gradle,
        // or default to "TV" if not specified.
        release(arg("release") ?: "TV")
        episodes("01", "02", "03", "04", "05", "06", "07", "08", "09")
        batches(
                "vol1" to listOf("01", "02", "03"),
                "vol2" to listOf("04", "05", "06"),
                "vol3" to listOf("07", "08", "09")
        )

        merge {
            from("$episode/file1.ass", "$episode/file2.ass", "$episode/file3.ass")
        }

        mux {
            from("$episode/video.mkv")
            from(merge.item())
            out("$episode/muxed.mkv")
        }

        // per-episode torrents
        torrent {
            from(mux.item())
            out("$episode/$episode.torrent")
        }

        // per-batch torrents
        batchtasks {
            torrent {
                from(mux.batchItems())
                into("My Show - $batch")
                out("$batch/$batch.torrent")
            }
        }

        // alternatively, configure all tasks in one go
        alltasks {
            torrent {
                from(mux.batchItems())
                if (isBatch) {
                    into("My Show - $batch")
                }
                out("$entry/$entry.torrent")
            }
        }
    }
}

fun ItemGroupContext.itemGroupContextTaskSample1() {
    val copyTaskGroup = task("copy", SubCopy::class)
}

fun ItemGroupContext.itemGroupContextTaskSample2(mux: TaskGroup<Mux>) {
    task("copy", SubCopy::class) {
        from(mux.item())
        into("downloads")
    }
}

fun ItemGroupContext.itemGroupContextTaskSample3() {
    val copyTaskGroup = task<SubCopy>("copy")
}

fun ItemGroupContext.itemGroupContextTaskSample4(mux: TaskGroup<Mux>) {
    task<SubCopy>("copy") {
        from(mux.item())
        into("downloads")
    }
}

fun ItemGroupContext.itemGroupContextTaskDelegateSample(mux: TaskGroup<Mux>) {
    val copy by task<SubCopy> {
        from(mux.item())
        into("downloads")
    }
}

fun ItemGroupContext.itemGroupContextTaskClassDelegateSample(mux: TaskGroup<Mux>) {
    val copy by SubCopy::class {
        from(mux.item())
        into("downloads")
    }
}

fun ItemGroupContext.itemGroupContextStringTaskSample(mux: TaskGroup<Mux>) {
    "copy"<SubCopy> {
        from(mux.item())
        into("downloads")
    }
}

fun Subs.itemGroupItemSample1(mux: TaskGroup<Mux>) {
    mux.item("vol1") // returns the mux.vol1 task
}

fun Subs.itemGroupItemSample2(mux: TaskGroup<Mux>, merge: TaskGroup<Merge>) {
    tasks(listOf("vol1")) { // configure tasks for vol1 only
        mux {
            merge.item(this) // returns merge.vol1
        }
    }
}

fun Subs.itemGroupItemsSample1(mux: TaskGroup<Mux>) {
    mux.batchItems(listOf("01", "02", "03", "04")) // returns the mux.01, mux.02, mux.03, mux.04 tasks
}

fun Subs.itemGroupItemsSample2(mux: TaskGroup<Mux>, merge: TaskGroup<Merge>) {
    // assume batch vol1 contains episodes 01, 02, 03, and 04
    tasks(listOf("vol1")) { // configure tasks for vol1 only
        mux {
            merge.batchItems(this) // returns merge.01, merge.02, merge.03, merge.04
        }
    }
}
