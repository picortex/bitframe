package bitframe

import bitframe.dao.Condition
import bitframe.dao.Query
import bitframe.dao.exceptions.EntityNotFoundException
import kollections.List
import koncurrent.Later

interface Dao<out T : Any> {
    fun create(input: @UnsafeVariance T): Later<T>

    fun update(obj: @UnsafeVariance T): Later<T>

    /**
     * Loads an entity by its uid and returns it
     * @throws [EntityNotFoundException] when the object is not found
     */
    fun load(uid: String): Later<T>

    fun loadOrNull(uid: String): Later<T?>

    fun execute(query: Query): Later<List<T>>

    fun delete(uid: String): Later<T>

    fun all(condition: Condition<Any?>? = null): Later<List<T>>
}