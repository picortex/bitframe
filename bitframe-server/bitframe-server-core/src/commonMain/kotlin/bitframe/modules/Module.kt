package bitframe.modules

import bitframe.actions.Action

interface Module {
    val name: String
    val actions: List<Action>

    fun info() = mapOf(
        "name" to name,
        "actions" to actions.map { it.info() }
    )
}