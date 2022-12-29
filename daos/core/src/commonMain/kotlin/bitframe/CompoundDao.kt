package bitframe

import bitframe.actor.Savable
import bitframe.dao.Condition
import bitframe.dao.Query
import bitframe.dao.exceptions.EntityNotFoundException
import bitframe.dao.exceptions.MissingDaoException
import bitframe.dao.internal.AbstractDao
import koncurrent.Later
import koncurrent.FailedLater
import koncurrent.Laters
import koncurrent.later.filterSuccessValues
import kotlinx.collections.interoperable.toInteroperableList

class CompoundDao<T : Savable>(val config: CompoundDaoConfig<T>) : AbstractDao<T>() {

    private val daos by lazy { config.daos }

    override fun create(input: @UnsafeVariance T): Later<T> {
        return daos[input::class]?.create(input) ?: FailedLater(MissingDaoException(input::class, this))
    }

    override fun update(obj: @UnsafeVariance T): Later<T> {
        return daos[obj::class]?.update(obj) ?: FailedLater(MissingDaoException(obj::class, this))
    }

    private inline fun <R> inAllDaos(
        actions: Dao<T>.() -> Later<R>
    ) = Laters(*daos.values.map { it.actions() }.toTypedArray())

    override fun load(uid: String): Later<T> = inAllDaos {
        load(uid)
    }.then {
        it.filterSuccessValues().first()
    }

    override fun delete(uid: String): Later<T> = inAllDaos {
        delete(uid)
    }.then {
        it.filterSuccessValues().firstOrNull() ?: throw EntityNotFoundException(uid)
    }.andThen {
        daos[it::class]?.delete(uid) ?: FailedLater(MissingDaoException(it::class, this))
    }

    override fun execute(query: Query) = inAllDaos {
        execute(query)
    }.then { list ->
        list.filterSuccessValues().flatten().toInteroperableList()
    }

    override fun all(condition: Condition<*>?) = inAllDaos {
        all(condition)
    }.then { list ->
        list.filterSuccessValues().flatten().toInteroperableList()
    }
}