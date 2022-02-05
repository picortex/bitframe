package bitframe.daos

import bitframe.daos.conditions.Condition
import bitframe.daos.conditions.toMockFilter
import bitframe.modal.HasId
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.coroutines.delay
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.serializer
import later.Later
import later.await
import later.later
import kotlin.reflect.KClass

class MockDao<D : HasId>(
    val config: MockDaoConfig<D>
) : Dao<D> {

    constructor(clazz: KClass<D>) : this(MockDaoConfig(clazz))

    private val items get() = config.items

    private val scope get() = config.scope

    private val lock get() = config.lock

    override fun create(input: D): Later<D> = scope.later {
        lock.lock(this@MockDao)
        delay(config.simulationTime)
        val nextId = "${config.prefix}-${items.size + 1}"
        val output = input.copy(id = nextId) as D
        items[nextId] = output
        lock.unlock(this@MockDao)
        output
    }

    override fun update(obj: D): Later<D> = scope.later {
        lock.lock(this@MockDao)
        delay(config.simulationTime)
        items[obj.uid] = obj
        lock.unlock(this@MockDao)
        obj
    }

    override fun load(uid: String): Later<D> = scope.later {
        delay(config.simulationTime)
        items[uid] ?: throw RuntimeException("data with uid=$uid is not found in dao")
    }

    override fun loadOrNull(uid: String): Later<D?> = scope.later {
        try {
            load(uid).await()
        } catch (err: Throwable) {
            null
        }
    }

    override fun delete(uid: String): Later<D> = scope.later {
        lock.lock(this@MockDao)
        delay(config.simulationTime)
        val item = load(uid).await()
        items.remove(uid)
        lock.unlock(this@MockDao)
        item
    }

    @OptIn(InternalSerializationApi::class)
    override fun all(condition: Condition<String, Any>?): Later<List<D>> = scope.later {
        lock.lock(this@MockDao)
        delay(config.simulationTime)
        if (condition == null) {
            items.values.toInteroperableList()
        } else {
            items.values.filter(condition.toMockFilter(config.clazz.serializer())).toInteroperableList()
        }.also { lock.unlock(this@MockDao) }
    }
}