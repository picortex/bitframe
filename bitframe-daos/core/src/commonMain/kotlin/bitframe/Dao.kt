package bitframe

import bitframe.dao.Condition
import bitframe.dao.Query
import bitframe.dao.exceptions.EntityNotFoundException
import kotlinx.collections.interoperable.List
import koncurrent.Later

interface Dao<out T : Any> {
    fun create(input: @UnsafeVariance T): Later<@UnsafeVariance T>

    fun update(obj: @UnsafeVariance T): Later<@UnsafeVariance T>

    /**
     * Loads an entity by its uid and returns it
     * @throws [EntityNotFoundException] when the object is not found
     */
    fun load(uid: String): Later<@UnsafeVariance T>

    fun loadOrNull(uid: String): Later<@UnsafeVariance T?>

    fun execute(query: Query): Later<List<@UnsafeVariance T>>

    fun delete(uid: String): Later<@UnsafeVariance T>

    fun all(condition: Condition<*>? = null): Later<List<@UnsafeVariance T>>
}