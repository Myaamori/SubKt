package myaa.subkt.tasks

import com.google.gson.*
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.forms.append
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import io.ktor.utils.io.core.writeFully
import kotlinx.coroutines.runBlocking
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.property
import java.awt.Color
import java.lang.reflect.Type
import java.time.ZonedDateTime


object ProviderSerializer : JsonSerializer<Provider<*>> {
    override fun serialize(src: Provider<*>, typeOfSrc: Type,
                           context: JsonSerializationContext): JsonElement =
            context.serialize(src.orNull)
}

object ColorSerializer : JsonSerializer<Color> {
    override fun serialize(src: Color, typeOfSrc: Type,
                           context: JsonSerializationContext): JsonElement =
            context.serialize((src.red shl 16) + (src.green shl 8) + src.blue)
}

object ZonedDateTimeSerializer : JsonSerializer<ZonedDateTime> {
    override fun serialize(src: ZonedDateTime, typeOfSrc: Type,
                           context: JsonSerializationContext): JsonElement =
            context.serialize(src.toOffsetDateTime().toString())
}

/**
 * Task for posting to a Discord webhook. Supports normal messages, embeds,
 * and uploading files. A predefined task instance can be accessed through
 * [Subs.discord].
 *
 * @sample myaa.subkt.tasks.samples.discordSample
 */
open class Discord : DefaultTask(), SubTask {
    /**
     * The footer of an embed.
     */
    inner class Footer {
        /**
         * Footer text.
         */
        @get:Input
        @get:Optional
        val text = project.objects.property<String>()

        /**
         * URL of footer icon (only supports HTTP(S)).
         */
        @get:Input
        @get:Optional
        val iconUrl = project.objects.property<String>()

        /**
         * A proxied URL of footer icon.
         */
        @get:Input
        @get:Optional
        val proxyIconUrl = project.objects.property<String>()
    }

    /**
     * The image of an embed.
     */
    inner class Image {
        /**
         * Source URL of image (only supports HTTP(S)).
         */
        @get:Input
        @get:Optional
        val url = project.objects.property<String>()

        /**
         * A proxied URL of the image.
         */
        @get:Input
        @get:Optional
        val proxyUrl = project.objects.property<String>()

        /**
         * Height of image.
         */
        @get:Input
        @get:Optional
        val height = project.objects.property<Int>()

        /**
         * Width of image.
         */
        @get:Input
        @get:Optional
        val width = project.objects.property<Int>()
    }

    /**
     * The thumbnail of an embed.
     */
    inner class Thumbnail {
        /**
         * Source URL of thumbnail (only supports HTTP(S)).
         */
        @get:Input
        @get:Optional
        val url = project.objects.property<String>()

        /**
         * A proxied URL of the thumbnail.
         */
        @get:Input
        @get:Optional
        val proxyUrl = project.objects.property<String>()

        /**
         * Height of thumbnail.
         */
        @get:Input
        @get:Optional
        val height = project.objects.property<Int>()

        /**
         * Width of thumbnail.
         */
        @get:Input
        @get:Optional
        val width = project.objects.property<Int>()
    }

    /**
     * The video of an embed.
     */
    inner class Video {
        /**
         * Source URL of video.
         */
        @get:Input
        @get:Optional
        val url = project.objects.property<String>()

        /**
         * Height of video.
         */
        @get:Input
        @get:Optional
        val height = project.objects.property<Int>()

        /**
         * Width of video.
         */
        @get:Input
        @get:Optional
        val width = project.objects.property<Int>()
    }

    /**
     * The provider of an embed.
     */
    inner class Provider {
        /**
         * Name of provider.
         */
        @get:Input
        @get:Optional
        val name = project.objects.property<String>()

        /**
         * URL of provider.
         */
        @get:Input
        @get:Optional
        val url = project.objects.property<String>()
    }

    inner class Author {
        /**
         * Name of author.
         */
        @get:Input
        @get:Optional
        val name = project.objects.property<String>()

        /**
         * URL of author.
         */
        @get:Input
        @get:Optional
        val url = project.objects.property<String>()

        /**
         * URL of author icon (only supports HTTP(S)).
         */
        @get:Input
        @get:Optional
        val iconUrl = project.objects.property<String>()

        /**
         * A proxied URL of author icon.
         */
        @get:Input
        @get:Optional
        val proxyIconUrl = project.objects.property<String>()
    }

    inner class Field {
        /**
         * Name of the field (required).
         */
        @get:Input
        val name = project.objects.property<String>()

        /**
         * Value of the field (required).
         */
        @get:Input
        val value = project.objects.property<String>()

        /**
         * Whether or not this field should display inline.
         */
        @get:Input
        @get:Optional
        val inline = project.objects.property<Boolean>()
    }

    /**
     * An embed capable of rendering rich text, images and the like.
     */
    inner class Embed {
        /**
         * The title of this embed.
         */
        @get:Input
        @get:Optional
        val title = project.objects.property<String>()

        /**
         * The description of this embed.
         */
        @get:Input
        @get:Optional
        val description = project.objects.property<String>()

        /**
         * The URL of this embed.
         */
        @get:Input
        @get:Optional
        val url = project.objects.property<String>()

        /**
         * Timestamp of embed content.
         */
        @get:Input
        @get:Optional
        val timestamp = project.objects.property<ZonedDateTime>()

        /**
         * The color of the embed.
         */
        @get:Input
        @get:Optional
        val color = project.objects.property<Color>()

        /**
         * The footer of this embed.
         */
        @get:Nested
        @get:Optional
        var footer: Footer? = null
            private set

        /**
         * The image of this embed.
         */
        @get:Nested
        @get:Optional
        var image: Image? = null
            private set

        /**
         * The thumbnail of this embed.
         */
        @get:Nested
        @get:Optional
        var thumbnail: Thumbnail? = null
            private set

        /**
         * The video of this embed.
         */
        @get:Nested
        @get:Optional
        var video: Video? = null
            private set

        /**
         * The provider of this embed.
         */
        @get:Nested
        @get:Optional
        var provider: Provider? = null
            private set

        /**
         * The author of this embed.
         */
        @get:Nested
        @get:Optional
        var author: Author? = null
            private set

        private val _fields = mutableListOf<Field>()

        /**
         * The fields of this embed.
         */
        @get:Nested
        val fields: List<Field> = _fields

        /**
         * Set the footer of this embed.
         *
         * @param action A closure acting on a [Footer] object.
         */
        fun footer(action: Footer.() -> Unit) {
            footer = Footer().apply(action)
        }

        /**
         * Set the image of this embed.
         *
         * @param action A closure acting on an [Image] object.
         */
        fun image(action: Image.() -> Unit) {
            image = Image().apply(action)
        }

        /**
         * Set the thumbnail of this embed.
         *
         * @param action A closure acting on a [Thumbnail] object.
         */
        fun thumbnail(action: Thumbnail.() -> Unit) {
            thumbnail = Thumbnail().apply(action)
        }

        /**
         * Set the video of this embed.
         *
         * @param action A closure acting on a [Video] object.
         */
        fun video(action: Video.() -> Unit) {
            video = Video().apply(action)
        }

        /**
         * Set the provider of this embed.
         *
         * @param action A closure acting on a [Provider] object.
         */
        fun provider(action: Provider.() -> Unit) {
            provider = Provider().apply(action)
        }

        /**
         * Set the author of this embed.
         *
         * @param action A closure acting on an [Author] object.
         */
        fun author(action: Author.() -> Unit) {
            author = Author().apply(action)
        }

        /**
         * Add a field to this embed.
         *
         * @param action A closure acting on a [Field] object.
         */
        fun field(action: Field.() -> Unit) {
            _fields.add(Field().apply(action))
        }
    }

    /**
     * The webhook to post to (required).
     */
    @get:Input
    val webhook = project.objects.property<String>()

    /**
     * Message contents (up to 2000 characters). At least one of [content], [attachment]
     * or [embed] is required.
     */
    @get:Input
    @get:Optional
    val content = project.objects.property<String>()

    /**
     * Override the default username of the webhook.
     */
    @get:Input
    @get:Optional
    val username = project.objects.property<String>()

    /**
     * Override the default avatar of the webhook.
     */
    @get:Input
    @get:Optional
    val avatarUrl = project.objects.property<String>()

    /**
     * `true` if this is a TTS message.
     */
    @get:Input
    @get:Optional
    val tts = project.objects.property<Boolean>()

    /**
     * A file to send with the message. At least one of [attachment], [content]
     * or [embed] is required.
     */
    @get:Input
    @get:Optional
    val attachment = project.objects.fileProperty()

    private val _embeds = mutableListOf<Embed>()

    /**
     * A list of [Embed] objects currently added to this message.
     */
    @get:Nested
    val embeds: List<Embed> = _embeds

    /**
     * Add an embed to this message (up to 10 allowed).
     * At least one of [embed], [content] or [attachment] is required.
     *
     * @param action A closure acting on an [Embed] object.
     */
    fun embed(action: Embed.() -> Unit) {
        _embeds.add(Embed().apply(action))
    }

    @TaskAction
    fun run() {
        val client = HttpClient {
            expectSuccess = false

            install(JsonFeature) {
                serializer = GsonSerializer()
            }
        }


        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeHierarchyAdapter(
                        org.gradle.api.provider.Provider::class.java,
                        ProviderSerializer)
                .registerTypeAdapter(Color::class.java, ColorSerializer)
                .registerTypeAdapter(ZonedDateTime::class.java, ZonedDateTimeSerializer)
                .create()

        val json = gson.toJson(mapOf(
                "embeds" to embeds.takeIf { it.isNotEmpty() },
                "content" to content.orNull,
                "username" to username.orNull,
                "avatar_url" to avatarUrl.orNull,
                "tts" to tts.orNull
        ))

        runBlocking {
            val response = client.submitFormWithBinaryData<HttpResponse>(
                    url = webhook.get() + "?wait=true",
                    formData = formData {
                        attachment.orNull?.asFile?.let { file ->
                            append("file", file.name) {
                                writeFully(file.readBytes())
                            }
                        }

                        append("payload_json", json)
                    }
            )

            if (!response.status.isSuccess()) {
                val error = response.receive<Map<String, Any>>()
                error("could not post to webhook: ${error["message"]}")
            }
        }
    }
}

/**
 * Convenience property that upon use automatically instantiates and returns a
 * [TaskGroup] of type [Discord] with the name `discord`.
 */
val Subs.discord
    get() = task<Discord>("discord")
