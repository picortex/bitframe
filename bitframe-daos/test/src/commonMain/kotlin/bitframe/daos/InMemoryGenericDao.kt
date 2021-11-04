package bitframe.daos

import bitframe.daos.conditions.Condition
import later.Later

abstract class InMemoryGenericDao<I, D>(val config: InMemoryGenericDaoConfig<D>) : Dao<I, D> {

    private val items = config.items

    private val scope = config.scope

    abstract fun convert(input: I): D

    override fun create(input: I): Later<D> {
        TODO("Not yet implemented")
    }

    override fun update(obj: D): Later<D> {
        TODO("Not yet implemented")
    }

    override fun load(uid: String): Later<D> {
        TODO("Not yet implemented")
    }

    override fun delete(uid: String): Later<D> {
        TODO("Not yet implemented")
    }

    override fun all(condition: Condition<String, Any>?): Later<List<D>> {
        TODO("Not yet implemented")
    }
}