package bitframe.core

import bitframe.core.exceptions.EntityNotFoundException
import kotlinx.collections.interoperable.List
import later.Later
import later.await
import later.later

interface Dao<out T : Any> {

    val config: DaoConfig<@UnsafeVariance T>

    fun create(input: @UnsafeVariance T): Later<T>

    fun update(obj: @UnsafeVariance T): Later<T>

    /**
     * Loads an entity by its uid and returns it
     * @throws [EntityNotFoundException] when the object is not found
     */
    fun load(uid: String): Later<T>

    fun loadOrNull(uid: String): Later<T?> = config.scope.later {
        try {
            load(uid).await()
        } catch (err: Throwable) {
            null
        }
    }

    fun delete(uid: String): Later<T>

    fun all(condition: Condition<*>? = null): Later<List<T>>
}