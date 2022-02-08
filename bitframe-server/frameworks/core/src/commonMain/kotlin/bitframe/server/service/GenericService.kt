package bitframe.server.service

import bitframe.daos.conditions.Condition
import bitframe.actors.modal.HasId
import kotlinx.collections.interoperable.List
import later.Later

interface GenericService<out T> {

    fun create(input: @UnsafeVariance T): Later<out T>

    fun update(obj: @UnsafeVariance T): Later<out T>

    fun load(uid: String): Later<out T>

    fun loadOrNull(uid: String): Later<out T?>

    fun delete(uid: String): Later<out T>

    fun all(condition: Condition<String, Any>? = null): Later<out List<T>>

    companion object {
        operator fun <T : HasId> invoke(config: GenericServiceConfig<T>): GenericService<T> = GenericServiceImpl(config)
    }
}