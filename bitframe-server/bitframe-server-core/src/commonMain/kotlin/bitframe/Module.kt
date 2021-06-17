package bitframe

import bitframe.actions.Action
import bitframe.http.HttpRoute

interface Module {
    val name: String
    val actions: List<Action>

    fun info() = mapOf(
        "name" to name,
        "actions" to actions.map { it.info() }
    )
}