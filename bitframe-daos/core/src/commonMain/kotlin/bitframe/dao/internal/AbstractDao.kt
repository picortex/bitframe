package bitframe.dao.internal

import bitframe.Dao
import koncurrent.Later
import koncurrent.later.catch
import koncurrent.later.then

abstract class AbstractDao<out T : Any> : Dao<T> {
    override fun loadOrNull(uid: String): Later<out T?> = load(uid).then { it as? T }.catch { null }
}