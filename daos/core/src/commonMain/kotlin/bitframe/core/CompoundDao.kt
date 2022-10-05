package bitframe.core

import bitframe.core.exceptions.EntityNotFoundException
import bitframe.core.exceptions.MissingDaoException
import koncurrent.Later
import koncurrent.later
import koncurrent.later.await
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList
@Deprecated("in favour of its quivalent in bitframe.dao")
class CompoundDao<T : Any>(override val config: CompoundDaoConfig<T>) : Dao<T> {

    private val daos by lazy { config.daos }

    private val scope get() = config.scope

    override fun create(input: T): Later<T> {
        return daos[input::class]?.create(input) ?: throw MissingDaoException(input::class, this)
    }

    override fun update(obj: T): Later<T> {
        return daos[obj::class]?.update(obj) ?: throw MissingDaoException(obj::class, this)
    }

    override fun load(uid: String): Later<T> = scope.later {
        daos.values.map {
            it.loadOrNull(uid)
        }.mapNotNull {
            it.await()
        }.firstOrNull() ?: throw EntityNotFoundException(uid)
    }

    override fun delete(uid: String): Later<T> = scope.later {
        val item = load(uid).await()
        daos[item::class]?.delete(uid)?.await() ?: throw EntityNotFoundException(uid)
    }

    override fun execute(query: Query): Later<List<T>> = scope.later {
        daos.values.map { it.execute(query) }.flatMap { it.await() }.toInteroperableList()
    }

    override fun all(condition: Condition<*>?): Later<List<T>> = scope.later {
        daos.values.map { it.all(condition) }.flatMap { it.await() }.toInteroperableList()
    }
}