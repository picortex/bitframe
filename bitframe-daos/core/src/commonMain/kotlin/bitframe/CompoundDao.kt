package bitframe

import bitframe.dao.Condition
import bitframe.dao.Query
import bitframe.dao.exceptions.EntityNotFoundException
import bitframe.dao.exceptions.MissingDaoException
import bitframe.dao.internal.AbstractDao
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList
import koncurrent.Later

class CompoundDao<T : Any>(val config: CompoundDaoConfig<T>) : AbstractDao<T>() {

    private val daos by lazy { config.daos }

    override fun create(input: T): Later<@UnsafeVariance T> {
        return daos[input::class]?.create(input) ?: Later.reject(throw MissingDaoException(input::class, this))
    }

    override fun update(obj: T): Later<@UnsafeVariance T> {
        return daos[obj::class]?.update(obj) ?: Later.reject(throw MissingDaoException(obj::class, this))
    }

    override fun load(uid: String): Later<@UnsafeVariance T> = scope.later {
        daos.values.map {
            it.loadOrNull(uid)
        }.mapNotNull {
            it.await()
        }.firstOrNull() ?: throw EntityNotFoundException(uid)
    }

    override fun delete(uid: String): Later<@UnsafeVariance T> = scope.later {
        val item = load(uid).await()
        daos[item::class]?.delete(uid)?.await() ?: throw EntityNotFoundException(uid)
    }

    override fun execute(query: Query): Later<List<T>> = scope.later {
        daos.values.map { it.execute(query) }.flatMap { it.await() }.toInteroperableList()
    }

    override fun all(condition: Condition<*>?): Later<@UnsafeVariance List<T>> = scope.later {
        daos.values.map { it.all(condition) }.flatMap { it.await() }.toInteroperableList()
    }
}