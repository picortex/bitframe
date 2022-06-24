package bitframe.dao.internal

import bitframe.Dao
import koncurrent.Fulfilled
import koncurrent.Later
import koncurrent.Rejected

abstract class AbstractDao<out T : Any> : Dao<T> {
    override fun loadOrNull(uid: String): Later<@UnsafeVariance T?> = Later { res, rej ->
        try {
            load(uid).complete {
                when (it) {
                    is Fulfilled -> res(it.value)
                    is Rejected -> res(null)
                }
            }
        } catch (err: Throwable) {
            res(null)
        }
    }
}