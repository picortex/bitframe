package bitframe.dao.internal

import bitframe.Dao
import bitframe.actor.Savable
import koncurrent.Later
import koncurrent.later.catch

abstract class AbstractDao<out T : Savable> : Dao<T> {
    override fun loadOrNull(uid: String): Later<out T?> = load(uid).then { it as? T }.catch { null }

    override fun delete(uid: String): Later<out T> = load(uid).andThen {
        update(it.copySavable(uid, deleted = true) as T)
    }
}