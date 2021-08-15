package bitframe

import bitframe.server.BitframeApplication
import bitframe.server.actions.Action
import bitframe.server.http.HttpRequest
import bitframe.server.http.HttpResponse
import bitframe.server.http.HttpRoute
import bitframe.server.modules.Module
import io.ktor.http.*
import io.ktor.http.HttpMethod.Companion.Get
import io.ktor.http.HttpMethod.Companion.Post
import io.ktor.http.HttpMethod.Companion.Put
import logging.ConsoleAppender
import logging.error

open class Sandbox(val component: UnderTest) {
    constructor(application: BitframeApplication) : this(ApplicationUnderTest(application))
    constructor(module: Module) : this(ModuleUnderTest(module))
    constructor(route: HttpRoute) : this(RouteUnderTest(route))
    constructor(action: Action) : this(ActionUnderTest(action))

    private val console = ConsoleAppender()

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
        } ?: return HttpResponse(status = HttpStatusCode.NotFound, "Invalid route ${request.path}").also {
            console.error("${request.method.value} ${request.path} Not Found")
            console.error("Configured routes for this sandbox are")
            routes.forEach { console.error(it.toString()) }
        }

        return try {
            val handler = route.handler
            handler(request)
        } catch (cause: Throwable) {
            HttpResponse(HttpStatusCode.InternalServerError, cause.message ?: "Unknown Error")
        }
    }

    fun get(path: String) = request(HttpRequest(Get, path, mapOf(), null))

    fun post(
        path: String,
        headers: Map<String, String> = mapOf(),
        body: String = "{}"
    ) = request(HttpRequest(Post, path, headers, body))

    fun put(
        path: String,
        headers: Map<String, String> = mapOf(),
        body: String = "{}"
    ) = request(HttpRequest(Put, path, headers, body))
}