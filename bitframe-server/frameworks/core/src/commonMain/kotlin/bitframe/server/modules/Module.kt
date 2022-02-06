package bitframe.server.modules

import bitframe.modal.HasId
import bitframe.server.actions.Action
import bitframe.server.http.HttpResponse
import bitframe.server.http.HttpRoute
import io.ktor.http.*
import kotlin.jvm.JvmOverloads
import kotlin.reflect.KClass

interface Module {
    val name: String
    val actions: List<Action>

    fun info() = mapOf(
        "name" to name,
        "actions" to actions.map { it.info() }
    )

    companion object {
        inline operator fun <reified D : HasId> invoke(
            config: ModuleConfiguration<D>
        ): Module = ModuleImpl(config)
    }
}