package bitframe

import bitframe.actor.Savable
import bitframe.dao.Condition
import bitframe.dao.Query
import bitframe.dao.exceptions.EntityNotFoundException
import bitframe.dao.exceptions.MissingDaoException
import bitframe.dao.internal.AbstractDao
import koncurrent.Later
import koncurrent.later.filterFulfilledValues
import kotlinx.collections.interoperable.toInteroperableList

class CompoundDao<out T : Savable>(val config: CompoundDaoConfig<T>) : AbstractDao<T>() {

    private val daos by lazy { config.daos }

    override fun create(input: @UnsafeVariance T): Later<out T> {
        return daos[input::class]?.create(input) ?: Later.reject(MissingDaoException(input::class, this))
    }

    override fun update(obj: @UnsafeVariance T): Later<out T> {
        return daos[obj::class]?.update(obj) ?: Later.reject(MissingDaoException(obj::class, this))
    }

    private inline fun <R> inAllDaos(
        actions: Dao<T>.() -> Later<R>
    ) = Later.all(*daos.values.map { it.actions() }.toTypedArray())

    override fun load(uid: String): Later<out T> = inAllDaos {
        load(uid)
    }.then {
        it.filterFulfilledValues().first()
    }

    override fun delete(uid: String): Later<out T> = inAllDaos {
        delete(uid)
    }.then {
        it.filterFulfilledValues().firstOrNull() ?: throw EntityNotFoundException(uid)
    }.andThen {
        daos[it::class]?.delete(uid) ?: Later.reject(MissingDaoException(it::class, this))
    }

    override fun execute(query: Query) = inAllDaos {
        execute(query)
    }.then { list ->
        list.filterFulfilledValues().flatten().toInteroperableList()
    }

    override fun all(condition: Condition<*>?) = inAllDaos {
        all(condition)
    }.then { list ->
        list.filterFulfilledValues().flatten().toInteroperableList()
    }
}