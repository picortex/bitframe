package bitframe.modules

import bitframe.actions.Action

open class StaticModule(
    override val name: String,
    vararg actions: Action
) : Module {
    override val actions: List<Action> = actions.map {
        it.copy(route = it.route.copy(path = "/$name${it.route.path}".removeSuffix("/").lowercase()))
    }
}