package bitframe.core

import bitframe.core.exceptions.EntityNotFoundException
import koncurrent.Later
import koncurrent.later
import koncurrent.later.await
import kotlinx.collections.interoperable.List

@Deprecated("in favour of its quivalent in bitframe.dao")
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

    fun execute(query: Query): Later<List<T>>

    fun delete(uid: String): Later<T>

    fun all(condition: Condition<*>? = null): Later<List<T>>
}