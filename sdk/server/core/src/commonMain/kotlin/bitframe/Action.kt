package bitframe

import bitframe.http.HttpRequest
import bitframe.http.HttpResponse
import bitframe.http.HttpRoute
import io.ktor.http.*
import koncurrent.Later

data class Action(
    val name: String,
    val params: Map<String, Any?>,
    val route: HttpRoute
) {
    companion object {
        operator fun invoke(
            name: String,
            params: Map<String, Any?>,
            method: HttpMethod,
            path: String,
            handler: (HttpRequest) -> Later<HttpResponse>
        ) = Action(name, params, HttpRoute(method, path, handler))
    }

    fun info() = mapOf(
        "name" to name,
        "params" to params,
        "route" to route.info()
    )
}