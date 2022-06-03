package bitframe.core

import bitframe.core.exceptions.EntityNotFoundException
import bitframe.core.exceptions.MissingDaoException
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList
import later.Later
import later.await
import later.later

class CompoundDao<T : Any>(override val config: CompoundDaoConfig<T>) : Dao<T> {

    private val daos by lazy { config.daos }

    private val scope get() = config.scope

    override fun create(input: T): Later<out T> {
        return daos[input::class]?.create(input) ?: throw MissingDaoException(input::class, this)
    }

    override fun update(obj: T): Later<out T> {
        return daos[obj::class]?.update(obj) ?: throw MissingDaoException(obj::class, this)
    }

    override fun load(uid: String): Later<out T> = scope.later {
        daos.values.map {
            it.loadOrNull(uid)
        }.mapNotNull {
            it.await()
        }.firstOrNull() ?: throw EntityNotFoundException(uid)
    }

    override fun delete(uid: String): Later<out T> = scope.later {
        val item = load(uid).await()
        daos[item::class]?.delete(uid)?.await() ?: throw EntityNotFoundException(uid)
    }

    override fun execute(query: Query): Later<List<T>> = scope.later {
        daos.values.map { it.execute(query) }.flatMap { it.await() }.toInteroperableList()
    }

    override fun all(condition: Condition<*>?): Later<out List<T>> = scope.later {
        daos.values.map { it.all(condition) }.flatMap { it.await() }.toInteroperableList()
    }
}