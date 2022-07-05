package bitframe

import bitframe.dao.Condition
import bitframe.dao.Query
import bitframe.dao.exceptions.EntityNotFoundException
import kotlinx.collections.interoperable.List
import koncurrent.Later

interface Dao<out T : Any> {
    fun create(input: @UnsafeVariance T): Later<out T>

    fun update(obj: @UnsafeVariance T): Later<out T>

    /**
     * Loads an entity by its uid and returns it
     * @throws [EntityNotFoundException] when the object is not found
     */
    fun load(uid: String): Later<out T>

    fun loadOrNull(uid: String): Later<out T?>

    fun execute(query: Query): Later<out List<T>>

    fun delete(uid: String): Later<out T>

    fun all(condition: Condition<*>? = null): Later<out List<T>>
}