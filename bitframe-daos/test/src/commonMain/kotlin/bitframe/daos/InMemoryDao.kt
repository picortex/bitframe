package bitframe.daos

import bitframe.daos.conditions.Condition
import bitframe.modal.HasId
import kotlinx.coroutines.delay
import later.Later
import later.await
import later.later
import kotlin.reflect.KClass

class InMemoryDao<D : HasId>(
    val config: InMemoryDaoConfig<D>
) : Dao<D> {

    constructor(clazz: KClass<D>) : this(InMemoryDaoConfig(clazz))

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

    override fun all(condition: Condition<String, Any>?): Later<List<D>> = scope.later {
        delay(config.simulationTime)
        items.values.toMutableList()
    }
}