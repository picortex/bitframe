package bitframe

import bitframe.actor.Savable
import bitframe.dao.*
import bitframe.dao.exceptions.EntityNotFoundException
import bitframe.dao.internal.AbstractDao
import koncurrent.Later
import koncurrent.later
import kollections.List
import kollections.toIList
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.serializer
import okio.Path

@OptIn(InternalSerializationApi::class)
class DaoFile<out T : Savable>(val config: DaoFileConfig<@UnsafeVariance T>) : AbstractDao<T>() {

    private val fs = config.fs
    private val dir = config.directory
    private val codec = config.codec

    private fun readObjectFromFile(path: Path): T {
        val json = fs.read(path) { readUtf8() }
        return codec.decodeFromString(config.clazz.serializer(), json)
    }

    private fun readAllObjectsFromDir(): Collection<T> = fs.list(dir).map { readObjectFromFile(it) }

    private fun writeObjectToFile(obj: T, path: Path): T {
        fs.write(path) { writeUtf8(codec.encodeToString(config.clazz.serializer(), obj)) }
        return obj
    }

    private fun folder() = dir.also { if (!fs.exists(it)) fs.createDirectories(it) }

    override fun create(input: @UnsafeVariance T): Later<out T> = config.executor.later {
        val size = fs.list(folder()).size
        val uid = size + 1
        val output = input.copy(uid = "$uid", false) as T
        writeObjectToFile(output, dir / "$uid.json")
    }

    override fun update(obj: @UnsafeVariance T): Later<out T> = config.executor.later {
        val uid = obj.uid
        val output = obj.copy(uid = uid, deleted = obj.deleted) as T
        writeObjectToFile(output, dir / "$uid.json")
    }

    override fun load(uid: String): Later<out T> = config.executor.later {
        val path = dir / "$uid.json"
        if (!fs.exists(path)) throw EntityNotFoundException(uid)
        readObjectFromFile(path)
    }

    override fun execute(query: Query): Later<out List<T>> = Later(config.executor) { resolve, reject ->
        val conditions = query.statements.filterIsInstance<Condition<Any?>>()
        var results: Collection<T> = readAllObjectsFromDir()
        for (cond in conditions) {
            val filtered = results.filter(cond.asPredicate(config.clazz.serializer()))
            results = filtered
        }
        val statements = query.statements - conditions
        statements.forEach { statement ->
            results = when (statement) {
                is Condition<Any?> -> results
                is LimitStatement -> results.take(statement.value)
                is SortStatement -> TODO()
            }
        }
        resolve(results.toIList())
    }


    override fun all(condition: Condition<Any?>?): Later<out List<T>> = Later(config.executor) { resolve, _ ->
        val items = readAllObjectsFromDir()
        if (condition == null) {
            resolve(items.toIList())
        } else {
            resolve(items.filter(condition.asPredicate(config.clazz.serializer())).toIList())
        }
    }
}