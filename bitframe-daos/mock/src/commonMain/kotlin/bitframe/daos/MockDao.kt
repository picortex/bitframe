package bitframe.daos

import bitframe.daos.conditions.Condition
import bitframe.daos.conditions.matching
import bitframe.modal.HasId
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

    override fun create(input: D): Later<D> = scope.later {
        delay(config.simulationTime)
        val nextId = "${config.prefix}-${items.size + 1}"
        val output = input.copy(id = nextId) as D
        items[nextId] = output
        output
    }

    override fun update(obj: D): Later<D> = scope.later {
        delay(config.simulationTime)
        items[obj.uid] = obj
        obj
    }

    override fun load(uid: String): Later<D> = scope.later {
        delay(config.simulationTime)
        items[uid] ?: throw RuntimeException("data with uid=$uid is not found in dao")
    }

    override fun delete(uid: String): Later<D> = scope.later {
        val item = load(uid).await()
        items.remove(uid)
        item
    }

    @OptIn(InternalSerializationApi::class)
    override fun all(condition: Condition<String, Any>?): Later<List<D>> = scope.later {
        delay(config.simulationTime)
        if (condition == null) {
            items.values.toList()
        } else {
            items.values.matching(condition, config.clazz.serializer())
        }
    }
}