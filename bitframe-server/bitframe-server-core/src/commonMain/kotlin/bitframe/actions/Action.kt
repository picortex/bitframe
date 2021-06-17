package bitframe.actions

import bitframe.http.HttpRoute

data class Action(
    val name: String,
    val params: Map<String, Any?>,
    val route: HttpRoute
) {
    fun info() = mapOf(
        "name" to name,
        "params" to params,
        "route" to route.info()
    )
}