package bitframe

import bitframe.dao.Condition
import bitframe.dao.Query
import bitframe.dao.exceptions.EntityNotFoundException
import bitframe.dao.exceptions.MissingDaoException
import bitframe.dao.internal.AbstractDao
import koncurrent.*
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList

class CompoundDao<T : Any>(val config: CompoundDaoConfig<T>) : AbstractDao<T>() {

    private val daos by lazy { config.daos }

    override fun create(input: T): Later<@UnsafeVariance T> {
        return daos[input::class]?.create(input) ?: Later.reject(MissingDaoException(input::class, this)) as Later<T>
    }

    override fun update(obj: T): Later<@UnsafeVariance T> {
        return daos[obj::class]?.update(obj) ?: Later.reject(MissingDaoException(obj::class, this)) as Later<T>
    }

    override fun load(uid: String): Later<@UnsafeVariance T> = daos.values.map {
        it.loadOrNull(uid)
    }.then { list ->
        list.firstFulfilledOrNullValue() ?: throw EntityNotFoundException(uid)
    }

    override fun delete(uid: String): Later<@UnsafeVariance T> = daos.values.map {
        it.loadOrNull(uid)
    }.then { list ->
        list.firstFulfilledOrNullValue() ?: throw EntityNotFoundException(uid)
    }.then {
        daos[it::class]?.delete(uid) ?: Later.reject(EntityNotFoundException(uid)) as Later<T>
    }.flatten()

    override fun execute(query: Query): Later<List<T>> = daos.values.map {
        it.execute(query)
    }.then { list ->
        list.filterFulfilledValues().flatten().toInteroperableList()
    }

    override fun all(condition: Condition<*>?): Later<@UnsafeVariance List<T>> = daos.values.map {
        it.all(condition)
    }.then { list ->
        list.filterFulfilledValues().flatten().toInteroperableList()
    }
}