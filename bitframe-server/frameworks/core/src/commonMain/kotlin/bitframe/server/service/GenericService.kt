package bitframe.server.service

import bitframe.daos.conditions.Condition
import bitframe.actors.modal.HasId
import kotlinx.collections.interoperable.List
import later.Later

interface GenericService<T> {

    fun create(input: T): Later<T>

    fun update(obj: T): Later<T>

    fun load(uid: String): Later<T>

    fun loadOrNull(uid: String): Later<T?>

    fun delete(uid: String): Later<T>

    fun all(condition: Condition<String, Any>? = null): Later<List<T>>

    companion object {
        operator fun <T : HasId> invoke(config: GenericServiceConfig<T>): GenericService<T> = GenericServiceImpl(config)
    }
}