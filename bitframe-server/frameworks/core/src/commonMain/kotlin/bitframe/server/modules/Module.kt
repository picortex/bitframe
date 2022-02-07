package bitframe.server.modules

import bitframe.actors.modal.HasId
import bitframe.server.actions.Action

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