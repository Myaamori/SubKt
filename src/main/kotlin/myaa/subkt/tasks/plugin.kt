package myaa.subkt.tasks

import org.apache.velocity.app.VelocityEngine
import org.apache.velocity.context.AbstractContext
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.UnknownDomainObjectException
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.*
import java.io.File
import java.io.FileNotFoundException
import java.io.StringWriter
import java.lang.UnsupportedOperationException
import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import kotlin.math.max

private fun convertGlob(glob: String, wildcard: String = "*") =
        Regex(Regex.escape(glob).replace(wildcard, "\\E.*\\Q"))

private fun convertPattern(pattern: String): Regex {
    val expanded = expandRanges(pattern)
    val groups = expanded.replace(Regex("""\{([^\}]*)\}""")) {
        val content = it.groupValues[1].replace(",", "\\E|\\Q")
        "\\E(\\Q$content\\E)\\Q"
    }

    val padded = when (groups.count { it == '.' }) {
        2 -> groups
        1 -> "*.$groups"
        0 -> "*.*.$groups"
        else -> error("malformed property: $pattern")
    }

    return Regex("\\Q" + padded.replace("*", "\\E.*\\Q") + "\\E")
}

/**
 * Reads a list of properties of the form `release.entry.property=value`, which can
 * later be looked up using the [match] method.
 *
 * Properties may contain wildcards, which will match any sequence of characters.
 * For instance, a property defined as `TV.episode*.name=value` will be found
 * with a `match("name", entry="episode01", release="TV")` call.
 * Groups (e.g. `{01,03,04}.op=OP1.ass`) and ranges (e.g. `{01..12}.cour=1`) are also supported.
 *
 * Unspecified release and entry components are equivalent to wildcards.
 * In other words, `name=value` is equivalent to `*.*.name=value`, while
 * `01.name=value` is equivalent to `*.01.name=value`.
 *
 * Properties are ordered, and the value of the last property matching the
 * [match] query will be returned. Thus, later values will override earlier values.
 *
 * Lines starting with `#` will be interpreted as comments and ignored.
 */
class SubProperties {
    private val patterns = mutableListOf<Pair<Regex, String>>()

    fun parse(f: File) {
        val ptrns = f.readLines().map { it.trimStart() }.withIndex()
                .filterNot { (_, line) -> line.isEmpty() || line.startsWith('#') }
                .map { (i, line) ->
                    val (prop, value) = line.split('=', limit = 2).also {
                        if (it.size != 2) {
                            error("malformed line in ${f.name}, line ${i + 1}: $line")
                        }
                    }
                    convertPattern(prop) to value
                }
        patterns.addAll(ptrns)
    }

    /**
     * Add or override existing properties from the build script.
     *
     * @param name The name of the property to add.
     * @param entry The entry/episode for which to add the property. May contain a range expression.
     * @param value The value of the property.
     */
    fun add(name: String, entry: String? = null, value: String) {
        val propName = if (entry != null) "$entry.$name" else name
        patterns.add(convertPattern(propName) to value)
    }

    /**
     * Finds the last property entry that matches the given property, entry and release.
     */
    fun match(property: String, entry: String = "", release: String = "") =
            patterns.findLast {  (pattern, _) ->
                pattern.matchEntire("$release.$entry.$property") != null
            }?.second
}

/**
 * Simple base implementation of a Velocity [AbstractContext].
 */
abstract class BaseContext : AbstractContext() {
    private val stack = LinkedList<String>()
    private var internalFields = mutableMapOf<String, Any?>()

    protected abstract fun doGet(key: String): Any?

    override fun internalPut(key: String, value: Any?) =
            internalFields.put(key, value)

    override fun internalGet(key: String): Any? {
        if (key in stack) {
            stack.add(key)
            val seq = stack.dropWhile { it != key }
            error("Recursive property dependency detected: ${seq.joinToString(" -> ")}")
        }
        stack.add(key)
        val result = internalFields[key] ?: doGet(key)
        stack.removeLast()
        return result
    }

    override fun internalGetKeys() = throw UnsupportedOperationException()

    override fun internalRemove(key: String?) =
            internalFields.remove(key)

    override fun internalContainsKey(key: String?) = false
}

private fun range(a: String, b: String): List<String> {
    val leading = max(
            if (a.startsWith('0')) a.length else 1,
            if (b.startsWith('0')) b.length else 1
    )
    return (a.toInt() .. b.toInt()).map { "%0${leading}d".format(it) }
}

private val bracketRegex = Regex("""\{([^}]+)\}""")
private val rangeRegex = Regex("""(\d+)\.\.(\d+)""")

// OVA{01..03} -> OVA{01,02,03}
// OVA{01..02}{02..03} -> OVA{01,02}{02,03}
// OVA{01..02,04,06..08} -> OVA{01,02,04,06,07,08}
private fun expandRanges(s: String) =
        bracketRegex.replace(s) { match ->
            val parts = match.groupValues[1].split(',').flatMap {
                rangeRegex.matchEntire(it)?.let { rangeMatch ->
                    val (a, b) = rangeMatch.destructured
                    range(a, b)
                } ?: listOf(it)
            }
            "{" + parts.joinToString(",") + "}"
        }

private val groupRegex = Regex("(.*?)\\{([^}]*)\\}(.*)")

// OVA{01,02,03}{04,05} -> OVA0104, OVA0105, OVA0204, OVA0205, OVA0304, OVA0305
private fun expandGroups(s: String): List<String> =
        groupRegex.matchEntire(s)?.let { match ->
            val (head, group, tail) = match.destructured
            group.split(",").map { "$head$it$tail" }
                    .flatMap { expandGroups(it) }
        } ?: listOf(s)

/**
 * Finds paths matching the given string. `%` and `*` are interpreted as wildcards.
 *
 * Returns the original string if no matching path was found.
 */
fun Project.globPath(glob: String): List<String> {
    val p = Path.of(glob.replace("*", "%"))
    val projectRoot = layout.projectDirectory.asFile.toPath()
    val globbed = globPath(p.root ?: projectRoot, p.subpath(0, p.nameCount))
    return globbed.map {
        if (p.isAbsolute) it else projectRoot.relativize(it)
    }.map { it.toString().replace("\\", "/") }
}

private fun Project.globPath(head: Path, tail: Path): List<Path> {
    val glob = tail.getName(0).toString()
    if (glob.contains('%')) {
        val pattern = convertGlob(tail.getName(0).toString(), "%")
        return Files.newDirectoryStream(head) { pattern.matches(it.fileName.toString()) }
                .use { matchPath ->
                    when {
                        tail.nameCount > 1 ->
                            matchPath
                                    .filter { it.toFile().isDirectory }
                                    .flatMap { globPath(it, tail.subpath(1, tail.nameCount)) }
                        else -> matchPath.toList()
                    }
                }
    } else {
        return when {
            tail.nameCount > 1 ->
                globPath(head.resolve(glob), tail.subpath(1, tail.nameCount))
            else -> listOf(head.resolve(glob))
        }
    }
}

/**
 * Simple subset of Bash globs. Supports basic brace expansion using
 * expressions like `{01..10}` and `{a,b,c}`, as well as wildcards.
 *
 * @sample myaa.subkt.tasks.samples.globSample
 */
fun Project.glob(s: String) =
        expandGroups(expandRanges(s)).flatMap { group ->
            when {
                // don't glob unless item contains wildcard (kills repeated /)
                group.find { it in "*%" } != null ->
                    globPath(group).takeUnless { it.isEmpty() }
                else -> null
            } ?: listOf(group)
        }


/**
 * [AbstractContext] implementation for getting properties in a task context.
 *
 * Will look for variables in the following places:
 * 1. The extra properties of the task
 * 2. The loaded [SubProperties] properties, searched using the current entry and release
 * 3. The current task, if the variable matches the name of the task
 * 4. The dependencies of the current task
 */
class TaskContext(val task: Task) : BaseContext() {
    override fun doGet(key: String) =
            task.extra.properties[key] ?:
                    try { task.taskGroup.subs.getList(key, entry = task.entry, context = this).orNull }
                    catch (e: NoSuchElementException) { null }
                            ?.singleOrNull() ?:
                    task.takeIf { it.taskGroup.name == key } ?:
                    try {
                        val taskName = "$key.${task.entry}.${task.release}"
                        val depTask = task.project.tasks[taskName]
                        if (!depTask.state.executed) {
                            error("Attempting to access unfinished task $taskName from ${task.name}. " +
                                    "Have you added it as a dependency to the current task?")
                        }
                        depTask
                    } catch (e: UnknownDomainObjectException) { null }
}

/**
 * Like [evaluate] but only processes the template syntax, without globbing.
 */
fun Task.evaluateTemplate(expression: String) =
        taskGroup.subs.evaluateTemplate(expression, entry = entry, context = TaskContext(this))

/**
 * Evaluates a string using [Velocity](https://velocity.apache.org/engine/2.2/user-guide.html),
 * splits it on `|`, and processes it with [glob].
 *
 * This function is run in a task context, meaning that task-specific values such as
 * [entry], [currentTask] and [isBatch] are available.
 *
 * @sample myaa.subkt.tasks.samples.taskEvaluateSample
 * @param expression The expression to evaluate.
 */
fun Task.evaluate(expression: String) =
        taskGroup.subs.evaluate(expression, entry = entry, context = TaskContext(this))

/**
 * Searches for the given property in the [Subs] object's [SubProperties] instance,
 * and returns the raw string, possibly null.
 *
 * This function is run in a task context, using the [entry] and [release] values for this task.
 *
 * @param propertyName The property to find.
 */
fun Task.getRawMaybe(propertyName: String) =
        taskGroup.subs.getRawMaybe(propertyName, entry = entry)

/**
 * Returns true if the given property exists in the [Subs] object's [SubProperties] instance
 * for the given context.
 *
 * This function is run in a task context, using the [entry] and [release] values for this task.
 *
 * @param propertyName The property to find.
 */
fun Task.propertyExists(propertyName: String) =
        taskGroup.subs.propertyExists(propertyName, entry = entry)

/**
 * Searches for the given property in the [Subs] object's [SubProperties] instance,
 * and returns the raw string.
 * Raises an error if not found.
 *
 * This function is run in a task context, using the [entry] and [release] values for this task.
 *
 * @sample myaa.subkt.tasks.samples.taskGetRawSample
 * @param propertyName The property to find.
 */
fun Task.getRaw(propertyName: String) =
        taskGroup.subs.getRaw(propertyName, entry = entry)

/**
 * Searches for the given property in the [Subs] object's [SubProperties] instance,
 * and evaluates its value using [evaluate].
 *
 * This function is run in a task context, using the [entry] and [release] values for this task.
 *
 * @sample myaa.subkt.tasks.samples.taskGetListSample
 * @param propertyName The property to find.
 */
fun Task.getList(propertyName: String) =
        taskGroup.subs.getList(propertyName, entry = entry, context = TaskContext(this))

/**
 * Searches for the given property in the [Subs] object's [SubProperties] instance,
 * evaluates its value using [evaluate], and casts the list elements to the given type
 * using [String.asType].
 *
 * This function is run in a task context, using the [entry] and [release] values for this task.
 *
 * @sample myaa.subkt.tasks.samples.taskGetListAsSample
 * @param propertyName The property to find.
 */
inline fun <reified T> Task.getListAs(propertyName: String) =
        taskGroup.subs.getListAs<T>(propertyName, entry = entry, context = TaskContext(this))

/**
 * Searches for the given property in the [Subs] object's [SubProperties] instance,
 * evaluates its value using [evaluate], and returns a single string, assuming
 * that the resulting list contains only one element.
 *
 * This function is run in a task context, using the [entry] and [release] values for this task.
 *
 * @sample myaa.subkt.tasks.samples.taskGetSample
 * @param propertyName The property to find.
 */
fun Task.get(propertyName: String) =
        taskGroup.subs.get(propertyName, entry = entry, context = TaskContext(this))

/**
 * Searches for the given property in the [Subs] object's [SubProperties] instance,
 * evaluates its value using [evaluate], and returns a single string, cast to
 * the given type using [String.asType], assuming that the resulting list
 * contains only one element.
 *
 * This function is run in a task context, using the [entry] and [release] values for this task.
 *
 * @sample myaa.subkt.tasks.samples.taskGetAsSample
 * @param propertyName The property to find.
 */
inline fun <reified T> Task.getAs(propertyName: String) =
        taskGroup.subs.getAs<T>(propertyName, entry = entry, context = TaskContext(this))

/**
 * Reads the specified file and processes it using
 * [Velocity](https://velocity.apache.org/engine/2.2/user-guide.html).
 *
 * This function is run in a task context, using the [entry] and [release] values for this task.
 *
 * @sample myaa.subkt.tasks.samples.taskGetFileSample
 * @param filename The path to the file to read.
 */
fun Task.getFile(filename: String) =
        taskGroup.subs.getFile(filename, entry = entry, context = TaskContext(this))

/**
 * Reads the specified file and processes it using
 * [Velocity](https://velocity.apache.org/engine/2.2/user-guide.html).
 *
 * This function is run in a task context, using the [entry] and [release] values for this task.
 *
 * @sample myaa.subkt.tasks.samples.taskGetFileSample
 * @param filename A provider returning the path to the file to read.
 */
fun Task.getFile(filename: Provider<String>) =
        taskGroup.subs.getFile(filename, entry = entry, context = TaskContext(this))

/**
 * Central object that keeps track of episodes, batches, tasks and user-loaded properties.
 * For tasks to be generated correctly, [episodes] and optionally [batches] should be set.
 * Set [release] if you wish to be able to differentiate between different releases
 * when looking up properties.
 *
 * Any tasks created directly in the context of [Subs] will generate one task per episode
 * specified in [episodes].
 * Batch tasks can be generated from the context of [batchtasks].
 * Tasks created in the context of [alltasks] will generate tasks for all episodes
 * as well as all batches.
 *
 * @sample myaa.subkt.tasks.samples.subsSample
 */
open class Subs(val project: Project) : ItemGroupContext() {
    override val subs
        get() = this

    /**
     * A [SubProperties] instance for looking up properties using [get] and related functions..
     */
    val properties = SubProperties()

    /**
     * A list of all episodes. Must be set for per-episode tasks to be generated.
     */
    val episodes = project.objects.listProperty<String>()

    /**
     * A map of batches to associated episodes. Must be set for per-batch tasks to be generated.
     */
    val batches = project.objects.mapProperty<String, Iterable<String>>()

    /**
     * The current release. Defaults to "default".
     */
    val release = project.objects.property<String>().convention("default")

    override val entries
        get() = episodes

    override fun isBatch(entry: String) = false

    override fun episodes(entry: String) = listOf(entry)

    override val taskGroups = mutableMapOf<String, TaskGroup<*>>()

    private inner class InnerContext(override val entries: Provider<out Iterable<String>>) :
            ItemGroupContext() {
        constructor(entries: Iterable<String>) : this(project.provider { entries })

        override val subs: Subs
            get() = this@Subs

        override val taskGroups = this@Subs.taskGroups

        override fun isBatch(entry: String) = entry in batches.get()

        override fun episodes(entry: String) = batches.getting(entry).orNull?.toList()
                ?: listOf(entry)
    }

    /**
     * Creates a context for generating batch tasks.
     *
     * @param action A closure operating on a [ItemGroupContext] entry.
     */
    fun batchtasks(action: ItemGroupContext.() -> Unit) =
            InnerContext(batches.keySet()).action()

    /**
     * Creates a context for generating both episode and batch tasks.
     *
     * @param action A closure operating on a [ItemGroupContext] entry.
     */
    fun alltasks(action: ItemGroupContext.() -> Unit) =
            InnerContext(episodes.get() + batches.keySet().get()).action()

    /**
     * Creates a context for generating tasks for the specified entries.
     *
     * @param entries A list of entries to generate tasks for.
     * @param action A closure operating on a [ItemGroupContext] entry.
     */
    fun tasks(entries: Iterable<String>, action: ItemGroupContext.() -> Unit) =
            InnerContext(entries).action()

    /**
     * Creates a context for generating tasks for the specified entries.
     *
     * @param entries Entries to generate tasks for.
     * @param action A closure operating on a [ItemGroupContext] entry.
     */
    fun tasks(vararg entries: String, action: ItemGroupContext.() -> Unit) =
            InnerContext(entries.toList()).action()

    /**
     * Creates a context for generating tasks for the specified entries.
     *
     * @param entries A provider for a list of entries to generate tasks for.
     * @param action A closure operating on a [ItemGroupContext] entry.
     */
    fun tasks(entries: Provider<out Iterable<String>>, action: ItemGroupContext.() -> Unit) =
            InnerContext(entries).action()

    private val engine = VelocityEngine().also {
        val p = Properties()
        p.setProperty("resource.loader.file.path", project.layout.projectDirectory.toString())
        it.init(p)
    }

    /**
     * [AbstractContext] implementation for getting properties in a [Subs] context.
     *
     * Will look for variables in the following places:
     * 1. The properties of the associated [Subs] instance
     * 2. The loaded [SubProperties] properties, searched using the specified release
     */
    inner class SubContext(val entry: String) : BaseContext() {
        private val extraProperties = mapOf(
                "release" to release,
                "episode" to entry,
                "batch" to entry,
                "entry" to entry
        )

        override fun doGet(key: String) =
                extraProperties[key] ?:
                        try { this@Subs.getList(key, entry = entry, context = this).orNull }
                        catch (e: NoSuchElementException) { null }
                                ?.singleOrNull()
    }

    /**
     * Processes the given string with Velocity using the given [AbstractContext].
     */
    private fun processTemplateString(s: String, entry: String = "",
                              context: AbstractContext? = null): String {
        val processed = StringWriter().also { w ->
            engine.evaluate(context ?: SubContext(entry), w, "inline", s)
        }
        return processed.toString()
    }

    /**
     * Processes the given file with Velocity using the given [AbstractContext].
     */
    private fun processFile(filename: String, entry: String = "",
                    context: AbstractContext? = null): String {
        val processed = StringWriter().also { w ->
            engine.mergeTemplate(filename, "UTF-8", context ?: SubContext(entry), w)
        }
        return processed.toString()
    }

    /**
     * Like [evaluate] but only processes the template syntax, without globbing.
     */
    fun evaluateTemplate(expression: String, entry: String = "", context: AbstractContext? = null) =
            project.provider {
                processTemplateString(expression, entry = entry, context = context)
            }

    /**
     * Evaluates a string using [Velocity](https://velocity.apache.org/engine/2.2/user-guide.html),
     * splits it on `|`, and processes it with [glob].
     *
     * This function is run outside of a task context, meaning that task-specific values such as
     * [entry], [currentTask] and [isBatch] are *not* available.
     *
     * @sample myaa.subkt.tasks.samples.subsEvaluateSample
     * @param expression The expression to evaluate.
     * @param entry Optional manually specified entry for property lookup.
     */
    fun evaluate(expression: String, entry: String = "", context: AbstractContext? = null) =
            project.provider {
                if (expression.startsWith('!')) {
                    expression.drop(1).split("|")
                } else {
                    val evaluated = evaluateTemplate(
                            expression, entry = entry, context = context).get()
                    evaluated.split("|").flatMap {
                        project.glob(it)
                    }
                }
            }

    /**
     * Searches for the given property in the [Subs] object's [SubProperties] instance,
     * and returns the raw string, possibly null.
     *
     * This function is run outside of a task context, using only [release] for lookup
     * unless an entry is manually specified.
     *
     * @param propertyName The property to find.
     * @param entry Optional manually specified entry for property lookup.
     */
    fun getRawMaybe(propertyName: String, entry: String = "") =
            properties.match(propertyName, entry = entry, release = release.get())

    /**
     * Returns true if the given property exists in the [Subs] object's [SubProperties] instance
     * for the given context.
     *
     * This function is run outside of a task context, using only [release] for lookup
     * unless an entry is manually specified.
     *
     * @sample myaa.subkt.tasks.samples.subsPropertyExistsSample
     * @param propertyName The property to find.
     * @param entry Optional manually specified entry for property lookup.
     */
    fun propertyExists(propertyName: String, entry: String = "") =
            getRawMaybe(propertyName, entry = entry) != null

    /**
     * Searches for the given property in the [Subs] object's [SubProperties] instance,
     * and returns the raw string.
     * Raises an error if not found.
     *
     * This function is run outside of a task context, using only [release] for lookup
     * unless an entry is manually specified.
     *
     * @sample myaa.subkt.tasks.samples.subsGetRawSample
     * @param propertyName The property to find.
     * @param entry Optional manually specified entry for property lookup.
     */
    fun getRaw(propertyName: String, entry: String = "") =
            getRawMaybe(propertyName, entry = entry)
                    ?: throw NoSuchElementException("no match for property name ${release.get()}.$entry.$propertyName")

    /**
     * Searches for the given property in the [Subs] object's [SubProperties] instance,
     * and evaluates its value using [evaluate].
     *
     * This function is run outside of a task context, using only [release] for lookup
     * unless an entry is manually specified.
     *
     * @sample myaa.subkt.tasks.samples.subsGetListSample
     * @param propertyName The property to find.
     * @param entry Optional manually specified entry for property lookup.
     */
    fun getList(propertyName: String, entry: String = "",
                context: AbstractContext? = null): Provider<List<String>> =
            project.provider<String> {
                getRaw(propertyName, entry = entry)
            }.flatMap { evaluate(it, entry = entry, context = context) }

    /**
     * Searches for the given property in the [Subs] object's [SubProperties] instance,
     * evaluates its value using [evaluate], and casts the list elements to the given type
     * using [String.asType].
     *
     * This function is run outside of a task context, using only [release] for lookup
     * unless an entry is manually specified.
     *
     * @sample myaa.subkt.tasks.samples.subsGetListAsSample
     * @param propertyName The property to find.
     * @param entry Optional manually specified entry for property lookup.
     */
    inline fun <reified T> getListAs(propertyName: String, entry: String = "",
                                     context: AbstractContext? = null) =
            getList(propertyName, entry = entry, context = context)
                    .map { list -> list.map { it.asType<T>() } }

    /**
     * Searches for the given property in the [Subs] object's [SubProperties] instance,
     * evaluates its value using [evaluate], and returns a single string, assuming
     * that the resulting list contains only one element.
     *
     * This function is run outside of a task context, using only [release] for lookup
     * unless an entry is manually specified.
     *
     * @sample myaa.subkt.tasks.samples.subsGetSample
     * @param propertyName The property to find.
     * @param entry Optional manually specified entry for property lookup.
     */
    fun get(propertyName: String, entry: String = "", context: AbstractContext? = null) =
            getList(propertyName, entry = entry, context = context).map { it.single() }

    /**
     * Searches for the given property in the [Subs] object's [SubProperties] instance,
     * evaluates its value using [evaluate], and returns a single string, cast to
     * the given type using [String.asType], assuming that the resulting list
     * contains only one element.
     *
     * This function is run outside of a task context, using only [release] for lookup
     * unless an entry is manually specified.
     *
     * @sample myaa.subkt.tasks.samples.subsGetAsSample
     * @param propertyName The property to find.
     * @param entry Optional manually specified entry for property lookup.
     */
    inline fun <reified T> getAs(propertyName: String, entry: String = "",
                                 context: AbstractContext? = null) =
            get(propertyName, entry = entry, context = context).map { it.asType<T>() }

    /**
     * Constructs a map by looking up [keys] in the properties, taking
     * its values to be the keys of the map, and then for each such key,
     * looking up the property with the key as its entry and [values]
     * as its property name, and taking the value of that property
     * to be the value associated with that key.
     *
     * Consider e.g. a properties file containing:
     *
     * ```
     * batches=vol{1..3}
     * vol1.episodes={01..04}
     * vol2.episodes={05..08}
     * vol3.episodes={09..12}
     * ```
     *
     * Calling `getMap("batches", "episodes")` would return the following map:
     *
     * ```
     * {vol1=[01, 02, 03, 04], vol2=[05, 06, 07, 08], vol3=[09, 10, 11, 12]}
     * ```
     */
    fun getMap(keys: String, values: String) =
            getList(keys).get().associateWith {
                getList(values, entry = it).get()
            }

    /**
     * Reads the specified file and processes it using
     * [Velocity](https://velocity.apache.org/engine/2.2/user-guide.html).
     *
     * This function is run outside of a task context, using only [release] for lookup
     * unless an entry is manually specified.
     *
     * @sample myaa.subkt.tasks.samples.subsGetFileSample
     * @param filename The path to the file to read.
     * @param entry Optional manually specified entry for property lookup.
     */
    fun getFile(filename: String, entry: String = "", context: AbstractContext? = null) =
            project.provider {
                processFile(filename, entry = entry, context = context)
            }

    /**
     * Reads the specified file and processes it using
     * [Velocity](https://velocity.apache.org/engine/2.2/user-guide.html).
     *
     * This function is run outside of a task context, using only [release] for lookup
     * unless an entry is manually specified.
     *
     * @sample myaa.subkt.tasks.samples.subsGetFileSample
     * @param filename A provider returning the path to the file to read.
     * @param entry Optional manually specified entry for property lookup.
     */
    fun getFile(filename: Provider<String>, entry: String = "", context: AbstractContext? = null) =
            filename.map {
                processFile(it, entry = entry, context = context)
            }

    /**
     * Reads properties from one or more files as described in [SubProperties].
     */
    fun readProperties(vararg file: String) {
        file.forEach {
            try {
                properties.parse(project.file(it))
            } catch (e: FileNotFoundException) {
                project.logger.warn("could not find property file $it")
            }
        }
    }

    /**
     * Reads properties defined in `gradle.properties` or supplied on the
     * command line using `-Pproperty=value`.
     */
    fun arg(argName: String) = project.properties[argName]?.toString()
}

/**
 * Plugin that adds a [Subs] instance as a DSL extension with the name "subs".
 */
class SubPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.extensions.create<Subs>("subs", project)
    }
}
