package bitframe

import bitframe.actor.Savable
import bitframe.dao.Condition
import bitframe.dao.Query
import koncurrent.Later
import kotlinx.collections.interoperable.List

class DaoFile<out T : Savable>(val config: DaoFileConfig<@UnsafeVariance T>) : Dao<T> {
    override fun create(input: @UnsafeVariance T): Later<out T> {
        TODO("Not yet implemented")
    }

    override fun update(obj: @UnsafeVariance T): Later<out T> {
        TODO("Not yet implemented")
    }

    override fun load(uid: String): Later<out T> {
        TODO("Not yet implemented")
    }

    override fun loadOrNull(uid: String): Later<out T?> {
        TODO("Not yet implemented")
    }

    override fun execute(query: Query): Later<out List<T>> {
        TODO("Not yet implemented")
    }

    override fun delete(uid: String): Later<out T> {
        TODO("Not yet implemented")
    }

    override fun all(condition: Condition<*>?): Later<out List<T>> {
        TODO("Not yet implemented")
    }
}