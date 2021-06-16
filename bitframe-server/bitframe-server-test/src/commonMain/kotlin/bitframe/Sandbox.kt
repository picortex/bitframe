package bitframe

import bitframe.actions.Action
import bitframe.http.HttpRequest
import bitframe.http.HttpResponse
import bitframe.http.HttpRoute
import io.ktor.http.*
import io.ktor.http.HttpMethod.Companion.Get
import io.ktor.http.HttpMethod.Companion.Post

open class Sandbox(val component: UnderTest) {

    constructor(application: BitframeApplication) : this(ApplicationUnderTest(application))
    constructor(module: Module) : this(ModuleUnderTest(module))
    constructor(route: HttpRoute) : this(RouteUnderTest(route))
    constructor(action: Action) : this(ActionUnderTest(action))

    val routes = when (component) {
        is ApplicationUnderTest<*> -> component.application.modules.flatMap {
            it.actions.map { a -> a.route }
        }
        is ModuleUnderTest<*> -> component.module.actions.map { it.route }
        is ActionUnderTest -> listOf(component.action.route)
        is RouteUnderTest<*> -> listOf(component.route)
    }

    fun request(request: HttpRequest): HttpResponse {
        val route = routes.find {
            it.path == request.path && it.method == request.method
        } ?: return HttpResponse(status = HttpStatusCode.NotFound, "Invalid route ${request.path}")

        return try {
            val handler = route.handler
            handler(request)
        } catch (cause: Throwable) {
            HttpResponse(HttpStatusCode.InternalServerError, cause.message)
        }
    }

    fun get(path: String) = request(HttpRequest(Get, path, mapOf(), null))

    fun post(
        path: String,
        headers: Map<String, String> = mapOf(),
        body: String = "{}"
    ) = request(HttpRequest(Post, path, headers, body))
}