package bitframe.dao.internal

import bitframe.Dao
import bitframe.actor.Savable
import koncurrent.Later
import koncurrent.later.catch
import koncurrent.later.flatten
import koncurrent.later.then

abstract class AbstractDao<out T : Savable> : Dao<T> {
    override fun loadOrNull(uid: String): Later<out T?> = load(uid).then { it as? T }.catch { null }

    override fun delete(uid: String): Later<out T> = load(uid).flatten {
        update(it.copySavable(uid, deleted = true) as T)
    }
}