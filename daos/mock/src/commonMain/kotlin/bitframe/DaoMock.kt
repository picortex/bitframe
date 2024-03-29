@file:OptIn(InternalSerializationApi::class)

package bitframe

import bitframe.actor.Savable
import bitframe.dao.*
import bitframe.dao.exceptions.EntityNotFoundException
import kollections.toIList
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.serializer
import koncurrent.Later

class DaoMock<D : Savable>(val config: DaoMockConfig<D>) : Dao<D> {

    private val items get() = config.items

    override fun create(input: D) = Later(config.executor) { resolve, _ ->
        val nextId = "${config.prefix}-${items.size + 1}"
        val output = input.copy(uid = nextId, deleted = false) as D
        items[nextId] = output
        resolve(output)
    }

    override fun update(obj: D) = Later(config.executor) { resolve, _ ->
        items[obj.uid] = obj
        resolve(obj)
    }

    override fun load(uid: String) = Later(config.executor) { resolve, reject ->
        val item = items[uid]
        if (item != null) resolve(item) else reject(EntityNotFoundException(uid = uid))
    }

    override fun loadOrNull(uid: String) = Later(config.executor) { resolve, _ ->
        val item = items[uid]
        if (item != null) resolve(item) else resolve(null)
    }

    @OptIn(InternalSerializationApi::class)
    override fun execute(query: Query) = Later(config.executor) { resolve, reject ->
        val conditions = query.statements.filterIsInstance<Condition<Any?>>()
        var results: Collection<D> = items.values
        for (cond in conditions) {
            results = results.filter(cond.asPredicate(config.clazz.serializer()))
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

    override fun delete(uid: String) = load(uid).then {
        val item = it.copy(uid, deleted = true) as D
        items[uid] = it
        item
    }

    override fun all(condition: Condition<Any?>?) = Later(config.executor) { resolve, _ ->
        if (condition == null) {
            resolve(items.values.toIList())
        } else {
            resolve(items.values.filter(condition.asPredicate(config.clazz.serializer())).toIList())
        }
    }
}