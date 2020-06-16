
import myaa.subkt.tasks.*
import myaa.subkt.tasks.Mux.*
import myaa.subkt.tasks.Nyaa.*
import java.awt.Color
import java.time.*

plugins {
    id("myaa.subkt")
}

subs {
    readProperties("sub.properties")
    episodes(getList("episodes"))
    release(arg("release") ?: "TV")
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

        // alternatively, using a merge scripts template:
        // fromMergeTemplate(get("mergetemplate"))
    }

    chapters {
        from(get("chapters"))
    }

    swap {
        from(merge.item())
    }

    mux {
        title(get("title"))

        from(get("premux"))

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
        }

        attach(get("fonts")) {
            includeExtensions("ttf", "otf")
        }

        attach(get("songfonts")) {
            includeExtensions("ttf", "otf")
        }

        out(get("muxfile"))
    }

    alltasks {
        torrent {
            trackers(getList("trackers"))

            from(mux.items()) {
                if (isBatch) {
                    into(get("batchdir"))
                }
            }

            out(get("torrentfile"))
        }

        nyaa {
            from(torrent.item())
            username(get("torrentuser"))
            password(get("torrentpass"))
            category(NyaaCategories.ANIME_ENGLISH)
            hidden(true)
            information(get("torrentinfo"))
            torrentDescription(getFile("torrent_description.txt"))
        }

        // upload with SFTP (configuration read from ~/.ssh/config)
        val uploadFiles by task<SFTP> {
            from(mux.items()) {
                if (isBatch) {
                    into(get("batchdir"))
                }
            }

            into(get("sftpfiledir"))

            host(get("sftphost"))
        }

        val startSeeding by task<SFTP> {
            // upload files to seedbox and publish to nyaa before initiating seeding
            dependsOn(uploadFiles.item(), nyaa.item())

            from(torrent.item())

            into(get("sftptorrentdir"))

            host(get("sftphost"))
        }
    }
}
