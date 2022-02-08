package bitframe.daos

import bitframe.daos.conditions.Condition
import bitframe.daos.config.DaoConfig
import kotlinx.collections.interoperable.List
import bitframe.daos.exceptions.EntityNotFoundException
import later.Later
import later.await
import later.later

interface Dao<out T : Any> {

    val config: DaoConfig<@UnsafeVariance T>

    fun create(input: @UnsafeVariance T): Later<out T>

    fun update(obj: @UnsafeVariance T): Later<out T>

    /**
     * Loads an entity by its uid and returns it
     * @throws [EntityNotFoundException] when the object is not found
     */
    fun load(uid: String): Later<out T>

    fun loadOrNull(uid: String): Later<out T?> = config.scope.later {
        try {
            load(uid).await()
        } catch (err: Throwable) {
            null
        }
    }

    fun delete(uid: String): Later<out T>

    fun all(condition: Condition<String, Any>? = null): Later<out List<T>>
}