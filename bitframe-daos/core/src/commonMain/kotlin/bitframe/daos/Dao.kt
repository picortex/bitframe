package bitframe.daos

import bitframe.daos.conditions.Condition
import later.Later

interface Dao<T> {

    fun create(input: T): Later<T>

    fun update(obj: T): Later<T>

    fun load(uid: String): Later<T>

    fun delete(uid: String): Later<T>

    fun all(condition: Condition<String, Any>? = null): Later<List<T>>
}