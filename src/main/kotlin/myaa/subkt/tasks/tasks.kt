package myaa.subkt.tasks

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.jcraft.jsch.*
import com.turn.ttorrent.common.TorrentSerializer
import com.turn.ttorrent.common.creation.MetadataBuilder
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.features.auth.Auth
import io.ktor.client.features.auth.providers.basic
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.forms.*
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.content.ByteArrayContent
import io.ktor.content.TextContent
import io.ktor.http.*
import io.ktor.utils.io.core.writeFully
import kotlinx.coroutines.runBlocking
import myaa.subkt.ass.assTime
import myaa.subkt.tasks.PropertyTask.TaskProperty
import org.apache.commons.net.ftp.FTPClient
import org.apache.commons.net.ftp.FTPSClient
import org.apache.commons.net.io.CopyStreamAdapter
import org.apache.commons.net.util.TrustManagerUtils
import org.apache.velocity.context.AbstractContext
import org.gradle.api.DefaultTask
import org.gradle.api.Task
import org.gradle.api.file.*
import org.gradle.api.internal.file.copy.CopyAction
import org.gradle.api.internal.file.copy.CopySpecInternal
import org.gradle.api.internal.provider.DefaultProvider
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.HasMultipleValues
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.*
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.bundling.AbstractArchiveTask
import org.gradle.api.tasks.bundling.Zip
import org.gradle.internal.logging.progress.ProgressLoggerFactory
import org.gradle.kotlin.dsl.*
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream
import java.io.OutputStream
import java.lang.reflect.Method
import java.net.Socket
import java.time.Duration
import java.util.*
import javax.inject.Inject
import javax.net.ssl.SSLSocket
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.declaredMemberProperties


/**
 * Context for defining groups of objects such as tasks.
 *
 * Provides convenience functions for defining tasks and other objects
 * for all entries associated with this context.
 */
abstract class ItemGroupContext {
    /**
     * The [Subs] instance all tasks created in this context should be associated with.
     */
    protected abstract val subs: Subs

    /**
     * A list of entries (episodes or batches) this context should generate tasks for.
     */
    protected abstract val entries: Provider<out Iterable<String>>

    /**
     * True if [entry] corresponds to a batch entry.
     */
    protected abstract fun isBatch(entry: String): Boolean

    /**
     * The episodes associated with [entry].
     */
    protected abstract fun episodes(entry: String): List<String>

    /**
     * Create and/or configure tasks of type [T] for all entries in [entries].
     *
     * @param action A closure operating on a [Task]. Called once for
     * each entry in the current context.
     */
    operator fun <T : Task> TaskGroup<T>.invoke(action: T.() -> Unit) {
        entries.get().forEach { entry ->
            this.registerItemMaybe(entry, isBatch(entry), episodes(entry)).configure(action)
        }
    }

    /**
     * The map to store [TaskGroup] objects created in this context.
     */
    protected abstract val taskGroups: MutableMap<String, TaskGroup<*>>

    /**
     * Create a new task group, or returns the task group with the given name if it already exists.
     *
     * @sample myaa.subkt.tasks.samples.itemGroupContextTaskSample1
     * @param name The name of the task group.
     * @param klass The type of task associated with the task group.
     */
    fun <T : Task> task(name: String, klass: KClass<T>) =
            taskGroups.computeIfAbsent(name) { TaskGroup(name, subs, klass) } as TaskGroup<T>

    /**
     * Creates a new task group, or returns the task group with the given name if it already exists,
     * and configures a task for each entry in the current context using the given closure.
     *
     * @sample myaa.subkt.tasks.samples.itemGroupContextTaskSample2
     * @param name The name of the task group.
     * @param klass The type of task associated with the task group.
     * @param action A closure operating on a [Task]. Called once for
     * each entry in the current context.
     */
    fun <T : Task> task(name: String, klass: KClass<T>, action: T.() -> Unit) =
            task(name, klass).also { it.invoke(action) }

    /**
     * Creates a new task group, or returns the task group with the given name if it already exists.
     *
     * @sample myaa.subkt.tasks.samples.itemGroupContextTaskSample3
     * @param name The name of the task group.
     * @param T The type of task associated with the task group.
     */
    inline fun <reified T : Task> task(name: String) = task(name, T::class)

    /**
     * Creates a new task group, or returns the task group with the given name if it already exists,
     * and configures a task for each entry in the current context using the given closure.
     *
     * @sample myaa.subkt.tasks.samples.itemGroupContextTaskSample4
     * @param name The name of the task group.
     * @param T The type of task associated with the task group.
     * @param action A closure operating on a [Task]. Called once for
     * each entry in the current context.
     */
    inline fun <reified T : Task> task(name: String, noinline action: T.() -> Unit) =
            task(name, T::class, action)

    /**
     * Returns a delegate that when accessed returns a task group with the same name
     * as the property it is bound to. Optionally configures one task for each entry
     * in the current context using the given closure.
     *
     * @sample myaa.subkt.tasks.samples.itemGroupContextTaskDelegateSample
     * @param T The type of task associated with the task group.
     * @param action A closure operating on a [Task]. Called once for
     * each entry in the current context.
     */
    inline fun <reified T : Task> task(noinline action: (T.() -> Unit)? = null) =
            TaskCreator(this, T::class, action)

    /**
     * Creates a new task group with the name given by the string the function is invoked on,
     * or returns the task group with the given name if it already exists,
     * and configures a task for each entry in the current context using the given closure.
     *
     * @sample myaa.subkt.tasks.samples.itemGroupContextStringTaskSample
     * @param T The type of task associated with the task group.
     * @param action A closure operating on a [Task]. Called once for
     * each entry in the current context.
     */
    inline operator fun <reified T : Task> String.invoke(noinline action: T.() -> Unit) =
            task(this, T::class, action)

    /**
     * Returns a delegate that when accessed returns a task group with the same name
     * as the property it is bound to. Also configures one task for each entry
     * in the current context using the given closure.
     *
     * @sample myaa.subkt.tasks.samples.itemGroupContextTaskClassDelegateSample
     * @param T The type of task associated with the task group.
     * @param action A closure operating on a [Task]. Called once for
     * each entry in the current context.
     */
    operator fun <T : Task> KClass<T>.invoke(action: T.() -> Unit) =
            TaskCreator(this@ItemGroupContext, this, action)

    /**
     * Creates a [ValueGroup], evaluating the given closure immediately for each entry
     * in the given context.
     *
     * @param T The type of the item contained in the [ValueGroup].
     * @param action A closure operating on a [ValueClosure]. Called once for
     * each entry in the current context.
     */
    fun <T> value(action: ValueClosure<*>.() -> T) =
            ValueGroup(subs, action).also { group ->
                entries.get().forEach {
                    group.registerItemMaybe(it, isBatch(it), episodes(it))
                }
            }

    /**
     * Creates a [ProviderGroup] that evaluates the given closure lazily, returning a
     * [Provider] when an item is requested for a given entry.
     * The closure is evaluated by running [Provider.get] on the returned provider.
     *
     * @param T The type of the item contained in the [ProviderGroup].
     * @param action A closure operating on a [ValueClosure].
     */
    fun <T> provider(action: ValueClosure<*>.() -> T) =
            ProviderGroup(subs, action).also { group ->
                entries.get().forEach {
                    group.registerItemMaybe(it, isBatch(it), episodes(it))
                }
            }
}

/**
 * Delegate for creation of tasks using property delegate syntax.
 * See [ItemGroupContext.task].
 */
class TaskCreator<T : Task>(
        /**
         * The context this delegate belongs to.
         */
        val context: ItemGroupContext,
        /**
         * The type of the task to create.
         */
        val klass: KClass<T>,
        /**
         * An optional closure for configuring the task.
         */
        val initAction: (T.() -> Unit)? = null
) {
    /**
     * Delegate which always returns the constant [item].
     */
    class WrapperDelegate<T>(val item: T) {
        operator fun getValue(receiver: Any?, property: KProperty<*>) = item
    }

    operator fun provideDelegate(receiver: Any?, prop: KProperty<*>):
            WrapperDelegate<TaskGroup<T>> {
        val taskGroup = context.task(prop.name, klass)

        if (initAction != null) {
            with(context) {
                taskGroup(initAction)
            }
        }

        return WrapperDelegate(taskGroup)
    }
}

/**
 * A group of items of type [T]. Has convenience functions
 * for getting all items belonging to the same entry as a certain task,
 * or all items corresponding to a specified list of entries.
 */
abstract class ItemGroup<T>(
        /**
         * The [Subs] instance this [ItemGroup] is associated with.
         */
        val subs: Subs
) {
    private val items = mutableMapOf<String, T>()

    /**
     * Get all items corresponding to the specified entries.
     *
     * @param entries A list of entries to get the items for.
     * @sample myaa.subkt.tasks.samples.itemGroupItemsSample1
     */
    fun batchItems(entries: Iterable<String>) = entries.map { items.getValue(it) }

    /**
     * Get all items corresponding to the specified entries.
     *
     * @param entries A provider for a list of entries to get the items for.
     * @sample myaa.subkt.tasks.samples.itemGroupItemsSample1
     */
    fun batchItems(entries: Provider<out Iterable<String>>) =
            entries.get().map(::item)

    /**
     * Get all items corresponding to the episodes of the given task.
     *
     * @sample myaa.subkt.tasks.samples.itemGroupItemsSample2
     */
    fun batchItems(task: Task) = batchItems(task.episodes)

    /**
     * Get the item corresponding to the specified entry.
     *
     * @sample myaa.subkt.tasks.samples.itemGroupItemSample1
     */
    fun item(entry: String) = items[entry]
            ?: error("Attempted to access entry $entry of $this but no such entry existed; " +
                    "available entries: ${items.keys.sorted().joinToString(", ")}")

    /**
     * Get the item of the same the entry as the given task.
     *
     * @sample myaa.subkt.tasks.samples.itemGroupItemSample2
     */
    fun item(task: Task) = item(task.entry)

    /**
     * Register a new item or return the item if it already exists.
     */
    fun registerItemMaybe(entry: String, isBatch: Boolean, episodes: List<String>) =
            items.computeIfAbsent(entry) { createItem(entry, isBatch, episodes) }

    /**
     * Returns a new item of type [T].
     */
    protected abstract fun createItem(entry: String, isBatch: Boolean, episodes: List<String>): T
}

/**
 * A group of tasks of the same type.
 */
class TaskGroup<T : Task>(
        /**
         * The name of this task group.
         */
        val name: String,
        subs: Subs,
        /**
         * The class object of the task type for this group.
         */
        val klass: KClass<T>
) : ItemGroup<TaskProvider<T>>(subs) {
    /**
     * Registers a new task for the given entry, or returns an existing task if it exists.
     * The returned task will following the naming scheme [name].[entry].
     *
     * @param entry The entry for this task, i.e. the episode or the batch identifier.
     * @param isBatch True if this corresponds to a batch task.
     * @param episodes The episodes that correspond to this entry. Usually a single-element
     * list containing [entry] if [isBatch] is false.
     */
    override fun createItem(entry: String, isBatch: Boolean, episodes: List<String>):
            TaskProvider<T> {
        val taskName = "$name.$entry.${subs.release.get()}"
        val newTask = subs.project.tasks.register(taskName, klass.java)
        newTask.configure {
            it.extra["taskGroup"] = this
            it.extra["isBatch"] = isBatch
            it.extra["episodes"] = episodes
            it.extra["release"] = subs.release.get()
            it.extra["entry"] = entry
            it.extra["currentTask"] = name
            if (isBatch) {
                it.extra["batch"] = entry
            } else {
                it.extra["episode"] = entry
            }
        }
        return newTask
    }

    override fun toString() = "task group '$name'"
}

/**
 * True if this task is a batch task.
 */
val Task.isBatch
    get() = extra["isBatch"] as Boolean

/**
 * The same as [entry] if this is an episode task; error otherwise.
 */
val Task.episode
    get() = extra["episode"] as String

/**
 * The same as [entry] if this is a batch task; error otherwise.
 */
val Task.batch
    get() = extra["batch"] as String

/**
 * The entry (batch or episode) this task corresponds to.
 */
val Task.entry
    get() = extra["entry"] as String

/**
 * The release this task was generated for.
 */
val Task.release
    get() = extra["release"] as String

/**
 * The name of this task.
 */
val Task.currentTask
    get() = extra["currentTask"] as String

/**
 * The episodes this task corresponds to. A single-item list containing
 * [episode] if this is an episode task; a list of the episodes for
 * the batch given by [batch] otherwise.
 */
val Task.episodes
    get() = extra["episodes"] as List<String>

/**
 * The [TaskGroup] instance this task belongs to.
 */
val <T : Task> T.taskGroup
    get() = extra["taskGroup"] as TaskGroup<T>

class ValueClosure<T>(
        /**
         * The entry this value corresponds to.
         */
        val entry: String,
        /**
         * Whether this entry is a batch entry.
         */
        val isBatch: Boolean,
        /**
         * The episodes corresponding to this entry.
         */
        val episodes: List<String>,
        /**
         * The item group this value belongs to.
         */
        val taskGroup: ItemGroup<T>
) {
    /**
     * The current release.
     */
    val release = taskGroup.subs.release.get()

    /**
     * Alias of [entry].
     */
    val batch = entry

    /**
     * Alias of [entry].
     */
    val episode = entry

    /**
     * Gets the items from the given item group that correspond to [episodes].
     */
    fun <U> ItemGroup<U>.batchItems() = batchItems(episodes)

    /**
     * Gets the item from the given item group that corresponds to [entry].
     */
    fun <U> ItemGroup<U>.item() = item(entry)

    /**
     * [AbstractContext] implementation for getting properties in a [ValueClosure] context.
     *
     * Will look for variables in the following places:
     * 1. The properties of the associated [ValueClosure]
     * 2. The loaded [SubProperties] properties, searched using the current entry and release
     * 3. All tasks in the current project
     */
    inner class ClosureContext : BaseContext() {
        private fun findProp(name: String) =
                this@ValueClosure::class.declaredMemberProperties
                        .find { it.name == name }?.getter?.call(this@ValueClosure)

        override fun doGet(key: String) =
                findProp(key) ?:
                        try { taskGroup.subs.get(key, entry = entry, context = this).orNull }
                        catch (e: NoSuchElementException) { null } ?:
                        taskGroup.subs.project.tasks.findByName(
                                "$key.$entry.${taskGroup.subs.release.get()}")
    }

    /**
     * Evaluates a string. See [Task.evaluateTemplate].
     */
    fun evaluateTemplate(expression: String) =
            taskGroup.subs.evaluateTemplate(expression, entry = entry, context = ClosureContext())

    /**
     * Evaluates a string. See [Task.evaluate].
     */
    fun evaluate(expression: String) =
            taskGroup.subs.evaluate(expression, entry = entry, context = ClosureContext())

    /**
     * Searches for the given property. Can return null. See [Task.getRawMaybe].
     */
    fun getRawMaybe(propertyName: String) =
            taskGroup.subs.getRawMaybe(propertyName, entry = entry)


    /**
     * Returns true if the given property exists. See [Task.propertyExists].
     */
    fun propertyExists(propertyName: String) =
            taskGroup.subs.propertyExists(propertyName, entry = entry)

    /**
     * Searches for the given property. See [Task.getRaw].
     */
    fun getRaw(propertyName: String) =
            taskGroup.subs.getRaw(propertyName, entry = entry)

    /**
     * Searches for the given property and returns it as a list. See [Task.getList].
     */
    fun getList(propertyName: String) =
            taskGroup.subs.getList(propertyName, entry = entry, context = ClosureContext())

    /**
     * Searches for the given property and returns it as a list of the given type.
     * See [Task.getListAs].
     */
    inline fun <reified T> getListAs(propertyName: String) =
            taskGroup.subs.getListAs<T>(propertyName, entry = entry, context = ClosureContext())

    /**
     * Searches for the given property and returns it as a single element. See [Task.get].
     */
    fun get(propertyName: String) =
            taskGroup.subs.get(propertyName, entry = entry, context = ClosureContext())

    /**
     * Searches for the given property and returns it as a single element of the given type.
     * See [Task.getAs].
     */
    inline fun <reified T> getAs(propertyName: String) =
            taskGroup.subs.getAs<T>(propertyName, entry = entry, context = ClosureContext())

    /**
     * Processes the given file. See [Task.getFile].
     */
    fun getFile(filename: String) =
            taskGroup.subs.getFile(filename, entry = entry, context = ClosureContext())

    /**
     * Processes the given file. See [Task.getFile].
     */
    fun getFile(filename: Provider<String>) =
            taskGroup.subs.getFile(filename, entry = entry, context = ClosureContext())
}

/**
 * [ItemGroup] that keeps track of simple values of type [T].
 * The closure is evaluated immediately for each entry.
 */
class ValueGroup<T>(subs: Subs, val action: ValueClosure<T>.() -> T) :
        ItemGroup<T>(subs) {
    override fun createItem(entry: String, isBatch: Boolean, episodes: List<String>): T =
        ValueClosure(entry, isBatch, episodes, this).action()
}

/**
 * [ItemGroup] that keeps track of providers for values of type [T].
 * A [Provider] is generated for each entry, and the given closure
 * is evaluated when the provider's value is requested.
 */
class ProviderGroup<T>(subs: Subs, val action: ValueClosure<*>.() -> T) :
        ItemGroup<Provider<T>>(subs) {
    override fun createItem(entry: String, isBatch: Boolean, episodes: List<String>): Provider<T> =
            subs.project.provider {
                ValueClosure(entry, isBatch, episodes, this).action()
            }
}

/**
 * Returns a [Property] with a default value set.
 */
inline fun <reified T> Task.defaultProperty(default: T): Property<T> =
        project.objects.property<T>().convention(default)

/**
 * Returns a [ConfigurableFileCollection] containing a single file `taskName.extension`
 * located in the build directory.
 */
fun Task.outputFile(extension: String): ConfigurableFileCollection =
        project.objects.fileCollection()
                .from(project.layout.buildDirectory.file("$name.$extension"))

/**
 * Mixin task interface providing convenience functions for accessing [ItemGroup] instances.
 */
interface SubTask : Task {
    /**
     * Gets the items from the given item group that correspond to [episodes].
     */
    fun <T> ItemGroup<T>.batchItems() = batchItems(this@SubTask.episodes)

    /**
     * Gets the item from the given item group that corresponds to [entry].
     */
    fun <T> ItemGroup<T>.item() = item(this@SubTask.entry)
}

/**
 * Parent task type that automatically keeps track of and stores
 * properties in JSON format.
 *
 * Make use of by declaring a delegated property which delegates to
 * a [TaskProperty] instance.
 */
abstract class PropertyTask : DefaultTask(), SubTask {
    @get:Internal
    protected val propertyFile: File =
            project.layout.buildDirectory.file("$name.cache.json").get().asFile

    private val properties = try {
        Gson().fromJson(propertyFile.reader(), MutableMap::class.java)
                as MutableMap<String, Any>?
    } catch (e: FileNotFoundException) { null } ?: mutableMapOf()

    /**
     * Delegate for reading properties from a JSON file associated with this task.
     */
    inner class TaskProperty<T : Any>(val default: () -> T) {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
            return properties.computeIfAbsent(property.name) { default() } as T
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
            properties[property.name] = value
        }
    }

    protected abstract fun run()

    private fun writeProperties() {
        propertyFile.parentFile.mkdirs()
        propertyFile.writer().use {
            Gson().toJson(properties, it)
        }
    }

    @TaskAction
    protected fun doTask() {
        run()
        writeProperties()
    }
}

/**
 * Task to create a torrent file from a set of files.
 * A predefined task instance can be accessed through [Subs.torrent].
 *
 * Note that if more than one file is added to the torrent,
 * a root directory must be specified using the [into] function.
 *
 * @sample myaa.subkt.tasks.samples.torrentSample
 */
open class Torrent : AbstractArchiveTask(), SubTask {
    /**
     * A list of trackers to add to the torrent.
     */
    @get:Input
    val trackers = project.objects.listProperty<String>().convention(listOf())

    /**
     * If true, mark torrent as private. Optional.
     */
    @get:Input
    @get:Optional
    val private = project.objects.property<Boolean>()

    /**
     * String identifying the program that created the torrent. Optional.
     */
    @get:Input
    @get:Optional
    val createdBy = project.objects.property<String>()

    /**
     * Comment to add to the torrent file. Optional.
     */
    @get:Input
    @get:Optional
    val comment = project.objects.property<String>()

    /**
     * Manually specified piece length. Optional.
     */
    @get:Input
    @get:Optional
    val pieceLength = project.objects.property<Int>()

    /**
     * The location to save the torrent file.
     * Defaults to an automatically generated file in the build directory.
     */
    @get:Internal
    val out = outputFile("torrent")

    init {
        val f = out.elements.map { it.single().asFile }
        archiveFileName.set(f.map { it.name })
        destinationDirectory.set(project.layout.dir(f.map {
            project.file(it.parent ?: ".")
        }))
    }

    override fun createCopyAction() = CopyAction { stream ->
        val builder = MetadataBuilder()

        trackers.get().forEach { tracker ->
            builder.newTier().addTracker(tracker)
        }

        private.orNull?.let {
            if (it) {
                builder.doPrivate()
            } else {
                builder.doPublic()
            }
        }

        createdBy.orNull?.let {
            builder.setCreatedBy(it)
        }

        comment.orNull?.let {
            builder.setComment(it)
        }

        pieceLength.orNull?.let {
            builder.setPieceLength(it)
        }

        val files = mutableListOf<Pair<RelativePath, File>>()
        stream.process {
            if (!it.isDirectory) {
                logger.lifecycle("${it.path} [${it.file.path}]")
                files.add(it.relativePath to it.file)
            }
        }

        val root = try {
            files.map { it.first.segments[0] }.distinct().single()
        } catch (e: IllegalArgumentException) {
            ""
        }

        if (root == "" && files.size > 1) {
            error("more than one file added, but no root set, or conflicting roots. " +
                    "please specify a root using into()")
        }

        val processedPaths = files.map { (path, file) ->
            val p = when (root) {
                "" -> path
                else -> RelativePath(path.isFile, *path.segments.drop(1).toTypedArray())
            }

            p to file
        }

        builder.setDirectoryName(root)

        processedPaths.forEach { (path, file) ->
            builder.addFile(file, path.pathString)
        }

        archiveFile.get().asFile.writeBytes(TorrentSerializer().serialize(builder.build()))

        WorkResults.didWork(true)
    }
}

/**
 * Task for uploading a torrent file to nyaa.si.
 * A predefined task instance can be accessed through [Subs.nyaa].
 *
 * @sample myaa.subkt.tasks.samples.nyaaSample
 */
open class Nyaa : PropertyTask() {
    /**
     * Torrent categories on Nyaa.
     */
    enum class NyaaCategories(val category: String) {
        ANIME_AMV("1_1"),
        ANIME_ENGLISH("1_2"),
        ANIME_NONENGLISH("1_3"),
        ANIME_RAW("1_4"),
        AUDIO_LOSSLESS("2_1"),
        AUDIO_LOSSY("2_2"),
        LITERATURE_ENGLISH("3_1"),
        LITERATURE_NONENGLISH("3_2"),
        LITERATURE_RAW("3_3"),
        LIVEACTION_ENGLISH("4_1"),
        LIVEACTION_IDOL("4_2"),
        LIVEACTION_NONENGLISH("4_3"),
        LIVEACTION_RAW("4_4"),
        PICTURES_GRAPHICS("5_1"),
        PICTURES_PHOTOS("5_2"),
        SOFTWARE_APPLICATIONS("6_1"),
        SOFTWARE_GAMES("6_2"),

        S_ART_ANIME("1_1"),
        S_ART_DOUJINSHI("1_2"),
        S_ART_GAMES("1_3"),
        S_ART_MANGA("1_4"),
        S_ART_PICTURES("1_5"),
        S_REALLIFE_PHOTOBOOKS("2_1"),
        S_REALLIFE_VIDEOS("2_2")
    }

    private data class NyaaResponse(
            val errors: Any?,
            val hash: String?,
            val id: Int?,
            val magnet: String?,
            val name: String?,
            val url: String?
    )

    /**
     * The API endpoint. Don't change unless you know what you're doing.
     * Defaults to `api/upload`.
     */
    @get:Input
    val endpoint = defaultProperty("api/upload")

    /**
     * The hostname. Don't change unless you know what you're doing.
     * Defaults to `nyaa.si`.
     */
    @get:Input
    val host = defaultProperty("nyaa.si")

    /**
     * If true, uses HTTPS to connect; HTTP otherwise.
     * Don't change unless you know what you're doing.
     * Defaults to `true`.
     */
    @get:Input
    val https = defaultProperty(true)

    /**
     * The port to connect to. Don't change unless you know what you're doing.
     * Defaults to `443` if [https] is true; `80` otherwise.
     */
    @get:Input
    @get:Optional
    val port = project.objects.property<Int>()

    /**
     * The username for posting the torrent.
     */
    @get:Input
    val username = project.objects.property<String>()

    /**
     * The password for posting the torrent.
     */
    @get:Input
    val password = project.objects.property<String>()

    /**
     * What category to post the torrent to.
     * Defaults to [NyaaCategories.ANIME_ENGLISH].
     */
    @get:Input
    val category = defaultProperty(NyaaCategories.ANIME_ENGLISH)

    /**
     * The name (title) of the torrent. By default lets Nyaa choose a title
     * based on the torrent file.
     */
    @get:Input
    val torrentName = defaultProperty("")

    /**
     * If true, post the torrent as anonymous.
     * Defaults to `false`.
     */
    @get:Input
    val anonymous = defaultProperty(false)

    /**
     * If true, post the torrent as hidden.
     * Defaults to `true`.
     */
    @get:Input
    val hidden = defaultProperty(true)

    /**
     * If true, marks the torrent as a complete release.
     * Defaults to `false`.
     */
    @get:Input
    val complete = defaultProperty(false)

    /**
     * If true, marks the torrent as a remake.
     * Defaults to `false`.
     */
    @get:Input
    val remake = defaultProperty(false)

    /**
     * If true, post the torrent as trusted, if using a trusted account.
     * Defaults to `true`.
     */
    @get:Input
    val trusted = defaultProperty(true)

    /**
     * The torrent information (e.g. website or IRC channel).
     * Defaluts to empty.
     */
    @get:Input
    val information = defaultProperty("")

    /**
     * The torrent description. Defaults to empty.
     */
    @get:Input
    val torrentDescription = defaultProperty("")

    /**
     * The torrent file to upload.
     */
    @get:InputFiles
    val from = project.objects.fileCollection()

    @get:OutputFiles
    protected val out = super.propertyFile

    /**
     * The magnet link of the uploaded torrent.
     * Only available if the upload succeeded.
     */
    @get:Internal
    var nyaaMagnet by TaskProperty { "" }
        private set

    /**
     * The hash of the uploaded torrent.
     * Only available if the upload succeeded.
     */
    @get:Internal
    var nyaaHash by TaskProperty { "" }
        private set

    /**
     * The Nyaa ID of the uploaded torrent.
     * Only available if the upload succeeded.
     */
    @get:Internal
    var nyaaId by TaskProperty { -1 }
        private set

    /**
     * The URL of the uploaded torrent.
     * Only available if the upload succeeded.
     */
    @get:Internal
    var nyaaUrl by TaskProperty { "" }
        private set

    /**
     * The name (title) of the uploaded torrent.
     * Only available if the upload succeeded.
     */
    @get:Internal
    var nyaaName by TaskProperty { "" }
        private set

    override fun run() {
        val torrentFile = from.singleFile

        val fields = mapOf(
                "name" to torrentName.get(),
                "category" to category.get().category,
                "anonymous" to anonymous.get(),
                "hidden" to hidden.get(),
                "complete" to complete.get(),
                "remake" to remake.get(),
                "trusted" to trusted.get(),
                "information" to information.get(),
                "description" to torrentDescription.get()
        )

        val client = HttpClient {
            expectSuccess = false

            install(JsonFeature) {
                serializer = GsonSerializer()
            }

            install(Auth) {
                basic {
                    username = this@Nyaa.username.get()
                    password = this@Nyaa.password.get()
                    sendWithoutRequest = true
                }
            }
        }

        val data = runBlocking {
            val response = client.submitFormWithBinaryData<HttpResponse>(
                    scheme = if (https.get()) "https" else "http",
                    host = host.get(),
                    port = port.orNull ?: if (https.get()) 443 else 80,
                    path = endpoint.get(),
                    formData = formData {
                        append("torrent_data", Gson().toJson(fields))
                        append("torrent", torrentFile.name) {
                            writeFully(torrentFile.readBytes())
                        }
                    }
            )

            val data = response.receive<NyaaResponse>()

            if (!response.status.isSuccess()) {
                error("couldn't upload torrent: ${data.errors}")
            } else {
                data
            }
        }

        nyaaMagnet = data.magnet!!
        nyaaUrl = data.url!!
        nyaaId = data.id!!
        nyaaHash = data.hash!!
        nyaaName = data.name!!

        logger.quiet("Uploaded torrent: $nyaaUrl [$nyaaName]")
    }
}

/**
 * Task for uploading a torrent file to anidex.info.
 * A predefined task instance can be accessed through [Subs.anidex].
 *
 * @sample myaa.subkt.tasks.samples.anidexSample
 */
open class Anidex : PropertyTask() {
    /**
     * Torrent categories on Nyaa.
     */
    enum class AnidexCategories(val categoryId: Int) {
        ANIME_SUB(1),
        ANIME_RAW(2),
        ANIME_DUB(3),
        LA_SUB(4),
        LA_RAW(5),
        LIGHT_NOVEL(6),
        MANGA_TL(6),
        MANGA_RAW(8),
        MUSIC_LOSSY(9),
        MUSIC_LOSSLESS(10),
        MUSIC_VIDEO(11),
        GAMES(12),
        APPLICATIONS(13),
        PICTURES(14),
        AV(15),
        OTHER(16)
    }

    enum class AnidexLanguage(val langId: Int) {
        ENGLISH(1),
        JAPANESE(2),
        POLISH(3),
        SERBOCROATIAN(4),
        DUTCH(5),
        ITALIAN(6),
        RUSSIAN(7),
        GERMAN(8),
        HUNGARIAN(9),
        FRENCH(10),
        FINNISH(11),
        VIETNAMESE(12),
        GREEK(13),
        BULGARIAN(14),
        SPANISH_SPAIN(15),
        PORTUGUESE_BRAZIL(16),
        PORTUGUESE_PORTUGAL(17),
        SWEDISH(18),
        ARABIC(19),
        DANISH(20),
        CHINESE_SIMPLIFIED(21),
        BENGALI(22),
        ROMANIAN(23),
        CZECH(24),
        MONGOLIAN(25),
        TURKISH(26),
        INDONESIAN(27),
        KOREAN(28),
        SPANISH_LATAM(29),
        PERSIAN(30),
        MALAYSIAN(31)
    }

    /**
     * The API endpoint. Don't change unless you know what you're doing.
     * Defaults to `api`.
     */
    @get:Input
    val endpoint = defaultProperty("api/")

    /**
     * The hostname. Don't change unless you know what you're doing.
     * Defaults to `anidex.info`.
     */
    @get:Input
    val host = defaultProperty("anidex.info")

    /**
     * If true, uses HTTPS to connect; HTTP otherwise.
     * Don't change unless you know what you're doing.
     * Defaults to `true`.
     */
    @get:Input
    val https = defaultProperty(true)

    /**
     * The port to connect to. Don't change unless you know what you're doing.
     * Defaults to `443` if [https] is true; `80` otherwise.
     */
    @get:Input
    @get:Optional
    val port = project.objects.property<Int>()

    /**
     * The API key for posting the torrent.
     */
    @get:Input
    val apiKey = project.objects.property<String>()

    /**
     * What category to post the torrent to.
     * Defaults to [AnidexCategories.ANIME_SUB].
     */
    @get:Input
    val category = defaultProperty(AnidexCategories.ANIME_SUB)

    /**
     * The name (title) of the torrent. By default lets Anidex choose a title
     * based on the torrent file.
     */
    @get:Input
    @get:Optional
    val torrentName = project.objects.property<String>()

    /**
     * The group to post the torrent as.
     * Defaults to `0`, i.e. individual/no group.
     */
    @get:Input
    val group = defaultProperty(0)

    /**
     * The language of the torrent.
     * Defaults to [AnidexLanguage.ENGLISH].
     */
    @get:Input
    val lang = defaultProperty(AnidexLanguage.ENGLISH)

    /**
     * If true, marks the torrent as a batch torrent.
     * Defaults to `false`.
     */
    @get:Input
    val batch = defaultProperty(false)

    /**
     * If true, marks the torrent as an R18+ torrent.
     * Defaults to `false`.
     */
    @get:Input
    val r18 = defaultProperty(false)

    /**
     * If true, marks the torrent as a remake.
     * Defaults to `false`.
     */
    @get:Input
    val remake = defaultProperty(false)

    /**
     * If true, marks the torrent as hidden.
     * Defaults to `true`.
     */
    @get:Input
    val hidden = defaultProperty(true)

    /**
     * If true, auto-submit torrent to TokyoToshokan.
     * A TokyoToshokan API key must have been provided in the upload settings.
     * Defaults to false.
     */
    @get:Input
    val ttApi = defaultProperty(false)

    /**
     * The torrent description. Defaults to empty.
     */
    @get:Input
    @get:Optional
    val torrentDescription = project.objects.property<String>()

    /**
     * The torrent file to upload.
     */
    @get:InputFiles
    val from = project.objects.fileCollection()

    @get:OutputFiles
    protected val out = super.propertyFile

    /**
     * The URL of the uploaded torrent.
     * Only available if the upload succeeded.
     */
    @get:Internal
    var anidexUrl by TaskProperty { "" }
        private set

    override fun run() {
        val torrentFile = from.singleFile

        val client = HttpClient {
            expectSuccess = false
        }

        val data = runBlocking {
            val response = client.submitFormWithBinaryData<HttpResponse>(
                    scheme = if (https.get()) "https" else "http",
                    host = host.get(),
                    port = port.orNull ?: if (https.get()) 443 else 80,
                    path = endpoint.get(),
                    formData = formData {
                        // TODO: ktor doesn't quote Content-Disposition parameters,
                        // resulting in anidex not reading the filename correctly,
                        // thinking it doesn't end in .torrent, and rejecting
                        // the request
                        // https://github.com/ktorio/ktor/issues/1691
                        val quotedFilename = torrentFile.name
                                .replace("\"", "\\\"").let { "\"$it\"" }
                        append("file", quotedFilename) {
                            writeFully(torrentFile.readBytes())
                        }
                        append("subcat_id", category.get().categoryId)
                        append("group_id", group.get())
                        append("api_key", apiKey.get())
                        append("lang_id", lang.get().langId)

                        torrentName.orNull?.let {
                            append("torrent_name", it)
                        }

                        torrentDescription.orNull?.let {
                            append("description", it)
                        }

                        if (batch.get()) {
                            append("batch", 1)
                        }

                        if (r18.get()) {
                            append("\u0068\u0065\u006e\u0074\u0061\u0069", 1)
                        }

                        if (remake.get()) {
                            append("reencode", 1)
                        }

                        if (hidden.get()) {
                            append("private", 1)
                        }

                        if (ttApi.get()) {
                            append("tt_api", 1)
                        }
                    }
            )

            val data = response.receive<String>()

            if (!response.status.isSuccess()) {
                error("couldn't upload torrent: $data")
            } else {
                data
            }
        }

        anidexUrl = data

        logger.quiet("Uploaded torrent: $anidexUrl")
    }
}

/**
 * Task for sending general HTTP requests.
 * Data should be sent using one of [json], [body] and [form].
 * The response can be retrieved from [responseData] or [responseJson].
 *
 * @sample myaa.subkt.tasks.samples.httpSample
 */
open class HTTP : PropertyTask(), SubTask {
    /**
     * The HTTP method. Defaults to GET.
     */
    @get:Input
    val method = defaultProperty("GET")

    /**
     * The hostname or IP address.
     */
    @get:Input
    val host = project.objects.property<String>()

    /**
     * If true, use HTTPS; HTTP otherwise.
     * Defaults to true.
     */
    @get:Input
    val https = defaultProperty(true)

    private val _protocol = https.map {
        if (it) URLProtocol.HTTPS else URLProtocol.HTTP
    }

    /**
     * The port to use. Defaults to the default port for the protocol used.
     */
    @get:Input
    @get:Optional
    val port = project.objects.property<Int>()

    /**
     * A username for https://username:password@example.com style authentication.
     */
    @get:Input
    @get:Optional
    val urlUser = project.objects.property<String>()

    /**
     * A password for https://username:password@example.com style authentication.
     */
    @get:Input
    @get:Optional
    val urlPass = project.objects.property<String>()

    /**
     * The endpoint to request. Defaults to /.
     */
    @get:Input
    val endpoint = defaultProperty("/")

    private val _parameters = project.objects.mapProperty<String, String>()

    /**
     * Query parameters.
     */
    @get:Input
    @get:Optional
    val parameters: Provider<Map<String, String>> = _parameters

    /**
     * Add a query parameter.
     */
    fun parameter(name: String, value: Provider<String>) {
        _parameters.put(name, value)
    }

    /**
     * Add a query parameter.
     */
    fun parameter(name: String, value: String) {
        _parameters.put(name, value)
    }

    private val _headers = project.objects.mapProperty<String, String>()

    /**
     * HTTP headers.
     */
    @get:Input
    @get:Optional
    val headers: Provider<Map<String, String>> = _headers

    /**
     * Add an HTTP header.
     */
    fun header(name: String, value: Provider<String>) {
        _headers.put(name, value)
    }

    /**
     * Add an HTTP header.
     */
    fun header(name: String, value: String) {
        _headers.put(name, value)
    }

    /**
     * The user agent.
     */
    @get:Input
    @get:Optional
    val userAgent = project.objects.property<String>()

    /**
     * The content type. Only used if sending data using [body].
     */
    @get:Input
    @get:Optional
    val contentType = project.objects.property<ContentType>()

    /**
     * Username for basic authentication.
     */
    @get:Input
    @get:Optional
    val basicAuthUser = project.objects.property<String>()

    /**
     * Password for basic authentication.
     */
    @get:Input
    @get:Optional
    val basicAuthPass = project.objects.property<String>()

    /**
     * JSON data to send.
     */
    @get:Input
    @get:Optional
    val json = project.objects.mapProperty<String, Any>()

    /**
     * Plain text data to send.
     */
    @get:Input
    @get:Optional
    val body = project.objects.property<String>()

    /**
     * URL encoded form data to send.
     */
    @get:Input
    @get:Optional
    val form = project.objects.mapProperty<String, String>()

    /**
     * Binary file data to send.
     */
    @get:InputFiles
    @get:Optional
    val attachment = project.objects.fileCollection()

    @get:OutputFiles
    protected val out = super.propertyFile

    /**
     * The response.
     */
    @get:Internal
    var responseData: String by TaskProperty { "" }

    /**
     * The response as JSON.
     */
    @get:Internal
    val responseJson: JsonElement by lazy { JsonParser.parseString(responseData) }

    override fun run() {
        val client = HttpClient {
            expectSuccess = false

            basicAuthUser.flatMap { user ->
                basicAuthPass.map { pass ->
                    Pair(user, pass)
                }
            }.orNull?.let { (user, pass) ->
                install(Auth) {
                    basic {
                        username = user
                        password = pass
                        sendWithoutRequest = true
                    }
                }
            }
        }

        val gson = GsonBuilder()
                .registerTypeHierarchyAdapter(Provider::class.java, ProviderSerializer)
                .create()

        runBlocking {
            val response = client.request<HttpResponse> {
                url {
                    host = this@HTTP.host.get()
                    port = this@HTTP.port.getOrElse(DEFAULT_PORT)
                    protocol = _protocol.get()
                    user = this@HTTP.urlUser.orNull
                    password = this@HTTP.urlPass.orNull
                    encodedPath = endpoint.get().encodeURLPath()
                    _parameters.get().forEach { (k, v) ->
                        parameters.append(k, v)
                    }
                }

                _headers.get().forEach { (k, v) ->
                    headers.append(k, v)
                }

                userAgent.orNull?.let {
                    userAgent(it)
                }

                method = HttpMethod.parse(this@HTTP.method.get())

                this@HTTP.body.orNull?.let {
                    body = TextContent(
                            it, contentType = contentType.getOrElse(ContentType.Text.Plain))
                }

                json.get().takeUnless { it.isEmpty() }?.let {
                    body = TextContent(gson.toJson(it),
                            contentType = ContentType.Application.Json)
                }

                form.get().takeUnless { it.isEmpty() }?.let { f ->
                    body = FormDataContent(Parameters.build {
                        f.forEach { (k, v) ->
                            append(k, v)
                        }
                    })
                }

                attachment.singleOrNull()?.let { file ->
                    headers.append(
                            HttpHeaders.ContentDisposition,
                            ContentDisposition.Attachment.withParameter(
                                    ContentDisposition.Parameters.FileName, file.name).toString())
                    body = ByteArrayContent(file.readBytes(),
                            contentType = contentType.getOrElse(ContentType.Application.OctetStream))
                }
            }

            responseData = response.receive()
            if (!response.status.isSuccess()) {
                error("request failed: $responseData")
            }
        }
    }
}

/**
 * The strategy for overwriting existing files.
 */
enum class OverwriteStrategy {
    /**
     * Never overwrite existing files.
     */
    NEVER,

    /**
     * Overwrite if the source file is newer.
     */
    SOURCE_NEWER,

    /**
     * Overwrite if the source file has a different size.
     */
    DIFFERENT_SIZE,

    /**
     * Overwrite if the source file is newer or has a different size.
     */
    DIFFERENT_SIZE_OR_SOURCE_NEWER,

    /**
     * Always overwrite.
     */
    ALWAYS
}

abstract class AbstractTransferTask<T> : AbstractCopyTask(), SubTask {
    protected data class FileDetails(val size: Long, val modified: Long)

    /**
     * The overwriting strategy to use when encountering existing files with the same name.
     * Defaults to [OverwriteStrategy.DIFFERENT_SIZE].
     */
    @get:Input
    val overwriteIf = defaultProperty(OverwriteStrategy.DIFFERENT_SIZE)

    @get:Inject
    protected abstract val progressLoggerFactory: ProgressLoggerFactory

    protected abstract fun createClient(): T

    protected abstract fun makedir(client: T, path: String)

    protected abstract fun upload(client: T, file: InputStream, dest: String,
                                  callback: (Long) -> Unit): Boolean

    protected abstract fun stat(client: T, file: String): FileDetails?

    fun makedirs(client: T, path: String) {
        tailrec fun mkdirs(path: String, remaining: List<String>) {
            makedir(client, path)

            if (remaining.isNotEmpty()) {
                mkdirs(path + remaining[0], remaining.drop(1))
            }
        }

        val parts = path.split(Regex("(?<=/)(?!\$)"))
        mkdirs(parts[0], parts.drop(1))
    }

    final override fun createCopyAction() = CopyAction { stream ->
        val destDir = rootSpec.resolveDestDir()

        val client = createClient()
        makedirs(client, destDir)
        val condition = overwriteIf.get()

        val filesToWrite = mutableListOf<Pair<File, String>>()
        val logger = progressLoggerFactory.newOperation(this::class.java)
        logger.start("Uploading files", "Preparing directories...")
        stream.process { details ->
            val destPath = destDir + "/" + details.path
            if (details.isDirectory) {
                makedirs(client, destPath)
            } else {
                val localFile = details.file
                val overwrite = condition == OverwriteStrategy.ALWAYS || run {
                    stat(client, destPath)?.let { (size, modified) ->
                        val localNewer = modified < localFile.lastModified()
                        val differentSize = size != localFile.length()

                        when (condition) {
                            OverwriteStrategy.SOURCE_NEWER -> localNewer
                            OverwriteStrategy.DIFFERENT_SIZE -> differentSize
                            OverwriteStrategy.DIFFERENT_SIZE_OR_SOURCE_NEWER ->
                                localNewer || differentSize
                            else -> false
                        }
                    } ?: true
                }

                if (overwrite) {
                    filesToWrite.add(localFile to destPath)
                }
            }
        }

        // do upload
        filesToWrite.withIndex().forEach { (i, pair) ->
            val (file, dest) = pair

            val success = file.inputStream().use {
                upload(client, it, dest) { progress ->
                    val percent = progress * 100 / file.length()
                    logger.progress(
                            "${file.name} ($percent%) [${i + 1}/${filesToWrite.size}]")
                }
            }

            if (!success) {
                error("could not upload ${file.path} -> $dest")
            }
        }

        logger.completed()
        WorkResults.didWork(true)
    }

    /**
     * [CopySpec] that captures the destination directory.
     */
    inner class DestDirRootSpec(rootSpec: CopySpecInternal) : CopySpecInternal by rootSpec {
        private var destDir: Any = "."

        override fun into(destPath: Any): CopySpec {
            destDir = destPath
            return this
        }

        fun resolveDestDir() = resolveDestDir(destDir)

        private fun resolveDestDir(destDir: Any): String =
                when (destDir) {
                    is String -> destDir
                    is Provider<*> -> resolveDestDir(destDir.get())
                    else -> error("can't convert type to destination directory: ${destDir::class}")
                }
    }

    override fun createRootSpec() = DestDirRootSpec(super.createRootSpec())

    override fun getRootSpec() = super.getRootSpec() as AbstractTransferTask<*>.DestDirRootSpec
}

private class SSLSessionReuseFTPSClient(implicit: Boolean) : FTPSClient(implicit) {
    // adapted from: https://trac.cyberduck.io/changeset/10760

    override fun _prepareDataSocket_(socket: Socket) {
        if (socket is SSLSocket) {
            // Control socket is SSL
            val session = (_socket_ as SSLSocket).session
            if (session.isValid) {
                val context = session.sessionContext
                val sessionHostPortCache = context.javaClass.getDeclaredField("sessionHostPortCache")
                sessionHostPortCache.isAccessible = true
                val cache = sessionHostPortCache.get(context)
                val method: Method = cache.javaClass.getDeclaredMethod("put", Any::class.java, Any::class.java)
                method.setAccessible(true)
                method.invoke(cache, String.format("%s:%s", socket.getInetAddress()
                        .hostName, socket.getPort().toString())
                        .toLowerCase(Locale.ROOT), session)
                method.invoke(cache, String.format("%s:%s", socket.getInetAddress()
                        .hostAddress, socket.getPort().toString())
                        .toLowerCase(Locale.ROOT), session)
            } else {
                throw error("Invalid SSL Session")
            }
        }
    }
}

/**
 * Task for uploading files via FTP.
 * A predefined task instance can be accessed through [Subs.ftp].
 *
 * @sample myaa.subkt.tasks.samples.ftpSample
 */
abstract class FTP : AbstractTransferTask<FTPClient>() {
    /**
     * The strategy for verifying certificates when using FTPS.
     */
    enum class CertificateMode {
        /**
         * Use the default Java trust manager.
         */
        DEFAULT,

        /**
         * Verify that the certificate has not expired, but do not perform any further checks.
         */
        VERIFY_NOT_EXPIRED,

        /**
         * Accept all certificates.
         */
        ACCEPT_ALL
    }

    /**
     * The username for logging in to the FTP server.
     */
    @get:Input
    val username = project.objects.property<String>()

    /**
     * The password for logging in to the FTP server.
     */
    @get:Input
    val password = project.objects.property<String>()

    /**
     * The hostname or IP address of the FTP server.
     */
    @get:Input
    val host = project.objects.property<String>()

    /**
     * The port of the FTP server.
     */
    @get:Input
    val port = project.objects.property<Int>()

    /**
     * Whether to encrypt the connection (FTPS). Defaults to true.
     */
    @get:Input
    val useSsl = defaultProperty(true)

    /**
     * Whether to use implicit mode for the connection. Defaults to false.
     */
    @get:Input
    val implicitSsl = defaultProperty(false)

    /**
     * If true, the same SSL session as the control channel will be used for data connections.
     * Required for some servers, e.g. `vsftpd` with `require_ssl_reuse` enabled,
     * but requires a hacky workaround for Java clients. Try disabling if you get strange issues.
     * Defaults to true.
     */
    @get:Input
    val sslSessionReuse = defaultProperty(true)

    /**
     * The certificate verification strategy to use for SSL connections.
     * Defaults to [CertificateMode.VERIFY_NOT_EXPIRED].
     */
    @get:Input
    val certificateVerificationMode = defaultProperty(CertificateMode.VERIFY_NOT_EXPIRED)

    override fun createClient(): FTPClient {
        val client = if (!useSsl.get()) {
            FTPClient()
        } else if (sslSessionReuse.get()) {
            SSLSessionReuseFTPSClient(implicitSsl.get())
        } else {
            FTPSClient(implicitSsl.get())
        }

        if (client is FTPSClient) {
            client.trustManager = when (certificateVerificationMode.get()) {
                CertificateMode.VERIFY_NOT_EXPIRED ->
                    TrustManagerUtils.getValidateServerCertificateTrustManager()
                CertificateMode.DEFAULT -> TrustManagerUtils.getDefaultTrustManager(null)
                CertificateMode.ACCEPT_ALL -> TrustManagerUtils.getAcceptAllTrustManager()
            }
        }

        //client.addProtocolCommandListener(PrintCommandListener(System.out))
        client.connect(host.get(), port.get())
        client.enterLocalPassiveMode()
        client.login(username.get(), password.get())

        if (client is FTPSClient) {
            client.execPBSZ(0)
            client.execPROT("P")
        }

        client.controlKeepAliveTimeout = 300
        client.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE)
        return client
    }

    override fun makedir(client: FTPClient, path: String) {
        if (stat(client, path) == null && !client.makeDirectory(path)) {
            error("Could not create directory: $path")
        }
    }

    override fun stat(client: FTPClient, file: String): FileDetails? =
            client.mlistFile(file)?.let { remoteFile ->
                FileDetails(remoteFile.size, remoteFile.timestamp.timeInMillis)
            }

    override fun upload(client: FTPClient, file: InputStream, dest: String, callback: (Long) -> Unit): Boolean {
        client.copyStreamListener = object : CopyStreamAdapter() {
            override fun bytesTransferred(totalBytesTransferred: Long,
                                          bytesTransferred: Int,
                                          streamSize: Long) {
                callback(totalBytesTransferred)
            }
        }

        return client.storeFile(dest, file)
    }
}

/**
 * Common interface for tasks that connect to SSH.
 */
interface SSHTask {
    /**
     * The hostname or IP address of the SSH server.
     */
    @get:Input
    val host: Property<String>

    /**
     * The port of the SSH server.
     * May also be specified using `Port` in the SSH config file.
     */
    @get:Input
    @get:Optional
    val port: Property<Int>

    /**
     * The username for logging in to the SSH server.
     * May also be specified using `User` in the SSH config file.
     */
    @get:Input
    @get:Optional
    val username: Property<String>

    /**
     * The password for logging in to the SSH server.
     * Not recommended; prefer using an identity file instead.
     */
    @get:Input
    @get:Optional
    val password: Property<String>

    /**
     * The private identity key file for logging in to the SSH server.
     * May also be specified using `IdentityFile` in the SSH config file.
     */
    @get:Input
    @get:Optional
    val identity: Property<String>

    /**
     * The SSH config file. Contains per-host settings such as
     * username, host address and identity file.
     * Defaults to ~/.ssh/config.
     */
    @get:Input
    @get:Optional
    val config: Property<String>

    /**
     * The known hosts file. SFTP will refuse to connect unless the host is
     * present in this file.
     * Defaults to ~/.ssh/known_hosts.
     *
     * JSch will prefer RSA fingerprints. If your known_hosts file contains
     * an ECDSA fingerprint and SFTP fails to recognize the fingerprint,
     * try running
     *
     * ```
     * ssh-keyscan -H -t rsa HOST_HERE >> ~/.ssh/known_hosts
     * ```
     *
     * to add an RSA fingerprint. See https://stackoverflow.com/a/44777270.
     */
    @get:Input
    @get:Optional
    val knownHosts: Property<String>

    /**
     * The timeout in milliseconds after which to abort the connection.
     * Defaults to 15000.
     */
    @get:Input
    @get:Optional
    val connectionTimeout: Property<Int>

    fun createSession(): Session
}

private class SSHTaskImpl(objects: ObjectFactory) : SSHTask {
    override val host = objects.property<String>()

    override val port = objects.property<Int>()

    override val username = objects.property<String>()

    override val password = objects.property<String>()

    override val identity = objects.property<String>()

    override val config = objects.property<String>()
            .convention("${System.getProperty("user.home")}/.ssh/config"
                    .takeIf { File(it).exists() })

    override val knownHosts = objects.property<String>()
            .convention("${System.getProperty("user.home")}/.ssh/known_hosts"
                    .takeIf { File(it).exists() })

    override val connectionTimeout = objects.property<Int>().convention(15000)

    override fun createSession(): Session {
        val jsch = JSch()

        knownHosts.orNull?.let {
            jsch.setKnownHosts(it)
        }

        identity.orNull?.let {
            jsch.addIdentity(it)
        }

        config.orNull?.let {
            jsch.configRepository = OpenSSHConfig.parseFile(it)
        }

        val session = jsch.getSession(username.orNull, host.get(), port.getOrElse(22))

        session.timeout = connectionTimeout.get()
        session.connect()

        return session
    }
}

/**
 * Task for uploading files via SFTP (SSH).
 * A predefined task instance can be accessed through [Subs.sftp].
 *
 * @sample myaa.subkt.tasks.samples.sftpSample
 */
abstract class SFTP @Inject constructor(objects: ObjectFactory) :
        AbstractTransferTask<ChannelSftp>(), SSHTask by SSHTaskImpl(objects) {

    override fun createClient(): ChannelSftp {
        val session = createSession()

        val channel = session.openChannel("sftp") as ChannelSftp
        channel.connect()

        return channel
    }

    override fun makedir(client: ChannelSftp, path: String) {
        if (stat(client, path) == null) {
            client.mkdir(path)
        }
    }

    override fun stat(client: ChannelSftp, file: String): FileDetails? =
            try {
                client.stat(file)?.let { remoteFile ->
                    FileDetails(remoteFile.size, remoteFile.mTime.toLong() * 1000)
                }
            } catch (e: SftpException) {
                if (e.id == 2) null else throw e
            }

    override fun upload(client: ChannelSftp, file: InputStream, dest: String, callback: (Long) -> Unit): Boolean {
        client.put(file, dest, object : SftpProgressMonitor {
            var total = 0L

            override fun count(count: Long): Boolean {
                total += count
                callback(total)
                return true
            }

            override fun end() {}

            override fun init(op: Int, src: String?, dest: String?, max: Long) {}
        })
        return true
    }
}

/**
 * Task for executing commands on a remote shell via SSH.
 *
 * @sample myaa.subkt.tasks.samples.sshExecSample
 */
open class SSHExec @Inject constructor(objects: ObjectFactory) :
        DefaultSubTask(), SSHTask by SSHTaskImpl(objects) {
    /**
     * The command to execute on the remote server.
     */
    @get:Input
    val command = project.objects.property<String>()

    /**
     * The environment variables to use for the process.
     */
    @get:Input
    val environment = project.objects.mapProperty<String, String>()

    /**
     * The standard input stream for the process executing the command.
     * Defaults to null (no input).
     */
    @get:Internal
    var standardInput: InputStream? = null

    /**
     * The output stream to consume standard output from the process executing the command.
     * Defaults to [System.out].
     */
    @get:Internal
    var standardOutput: OutputStream = System.out

    /**
     * The output stream to consume standard error from the process executing the command.
     * Defaults to [System.err].
     */
    @get:Internal
    var errorOutput: OutputStream = System.err

    @TaskAction
    fun run() {
        val session = createSession()
        val channel = session.openChannel("exec") as ChannelExec

        environment.get().forEach { (k, v) ->
            channel.setEnv(k, v)
        }

        channel.inputStream = standardInput
        channel.setErrStream(errorOutput)
        channel.setCommand(command.get())

        channel.connect()

        channel.inputStream.use { stream ->
            generateSequence { stream.readNBytes(1024).takeIf { it.isNotEmpty() } }
                    .forEach { standardOutput.write(it) }
        }

        if (channel.exitStatus != 0) {
            error("ssh command failed")
        }
    }
}


/**
 * Wrapper task for [DefaultTask] which implements [SubTask], giving access to
 * [SubTask.item] and [SubTask.batchItems] for convenience.
 */
open class DefaultSubTask : DefaultTask(), SubTask

/**
 * Wrapper task for [Copy] which implements [SubTask], giving access to
 * [SubTask.item] and [SubTask.batchItems] for convenience.
 */
open class SubCopy : Copy(), SubTask

/**
 * Wrapper task for [Exec] which implements [SubTask], giving access to
 * [SubTask.item] and [SubTask.batchItems] for convenience.
 */
open class SubExec : Exec(), SubTask

/**
 * Wrapper task for [Sync] which implements [SubTask], giving access to
 * [SubTask.item] and [SubTask.batchItems] for convenience.
 */
open class SubSync : Sync(), SubTask

/**
 * Wrapper task for [Zip] which implements [SubTask], giving access to
 * [SubTask.item] and [SubTask.batchItems] for convenience.
 */
open class SubZip : Zip(), SubTask

/**
 * Set the property to the given value.
 */
operator fun <T> Property<T>.invoke(value: T) = set(value)

/**
 * Set the property to the result of the given [Provider], evaluated lazily.
 */
operator fun <T> Property<T>.invoke(provider: Provider<T>) = set(provider)

/**
 * Set the property to the result of the given closure, evaluated lazily.
 */
operator fun <T> Property<T>.invoke(call: () -> T) = set(DefaultProvider(call))

/**
 * Set the file property to the output of the given task, evaluated lazily.
 * Only succeeds if the task outputs exactly one file.
 */
operator fun <T : FileSystemLocation> FileSystemLocationProperty<T>
        .invoke(provider: TaskProvider<*>) =
        fileProvider(provider.map { it.outputs.files.singleFile })

/**
 * Sets the file property to the given file.
 */
operator fun <T : FileSystemLocation> FileSystemLocationProperty<T>.invoke(file: File) = set(file)

/**
 * Sets the file collection to the given items.
 */
operator fun ConfigurableFileCollection.invoke(files: Any) = setFrom(files)

/**
 * Adds all specified items to the list property.
 */
operator fun <T> HasMultipleValues<T>.invoke(vararg items: T) = addAll(*items)

/**
 * Adds all specified items to the list property.
 */
operator fun <T> HasMultipleValues<T>.invoke(items: Iterable<T>) = addAll(items)

/**
 * Adds the output of the given [Provider] to the list property, evaluated lazily.
 */
operator fun <T> HasMultipleValues<T>.invoke(items: Provider<out Iterable<T>>) = addAll(items)

/**
 * Adds the output of the given closure to the list property, evaluated lazily.
 */
operator fun <T> HasMultipleValues<T>.invoke(call: () -> Iterable<T>) =
        addAll(DefaultProvider(call))

/**
 * Adds all specified key/value pairs to the map property.
 */
inline operator fun <K, reified V> MapProperty<K, V>.invoke(vararg pairs: Pair<K, Any>) =
        pairs.forEach { (k, v) ->
            when (v) {
                is Provider<*> -> put(k, v as Provider<V>)
                else -> put(k, v as V)
            }
        }

/**
 * Adds all items in the specified map to the map property.
 */
operator fun <K, V> MapProperty<K, V>.invoke(map: Map<K, V>) = putAll(map)

/**
 * Adds the items of the output of the given [Provider] to the map property, evaluated lazily.
 */
operator fun <K, V> MapProperty<K, V>.invoke(map: Provider<out Map<K, V>>) = putAll(map)

/**
 * Adds the items of the output of the given closure to the map property, evaluated lazily.
 */
operator fun <K, V> MapProperty<K, V>.invoke(call: () -> Map<K, V>) = putAll(DefaultProvider(call))

/**
 * Converts a string to the specified type.
 *
 * Supports the following formats:
 * * [String]
 * * [Int]
 * * [Long]
 * * [Float]
 * * [Double]
 * * [Regex]
 * * [Boolean] - supports the values `true` and `false`
 * * [Duration] - supports times in ASS format
 *
 * @param T The type to convert to.
 */
inline fun <reified T> String.asType(): T =
        when (T::class) {
            String::class -> this
            Int::class -> toInt()
            Long::class -> toLong()
            Float::class -> toFloat()
            Double::class -> toDouble()
            Regex::class -> toRegex()
            Boolean::class -> toBoolean()
            Duration::class -> assTime
            else -> error("no conversion available from String to ${T::class}")
        } as T

/**
 * Convenience property that upon use automatically instantiates and returns a
 * [TaskGroup] of type [Torrent] with the name `torrent`.
 */
val Subs.torrent
    get() = task<Torrent>("torrent")

/**
 * Convenience property that upon use automatically instantiates and returns a
 * [TaskGroup] of type [Nyaa] with the name `nyaa`.
 */
val Subs.nyaa
    get() = task<Nyaa>("nyaa")

/**
 * Convenience property that upon use automatically instantiates and returns a
 * [TaskGroup] of type [Anidex] with the name `anidex`.
 */
val Subs.anidex
    get() = task<Anidex>("anidex")

/**
 * Convenience property that upon use automatically instantiates and returns a
 * [TaskGroup] of type [FTP] with the name `ftp`.
 */
val Subs.ftp
    get() = task<FTP>("ftp")

/**
 * Convenience property that upon use automatically instantiates and returns a
 * [TaskGroup] of type [SFTP] with the name `sftp`.
 */
val Subs.sftp
    get() = task<SFTP>("sftp")
