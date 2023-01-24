package bitframe

import bitframe.http.HttpRequest
import bitframe.http.HttpResponse
import bitframe.http.HttpRoute
import io.ktor.http.*
import io.ktor.http.HttpMethod.Companion.Get
import io.ktor.http.HttpMethod.Companion.Post
import io.ktor.http.HttpMethod.Companion.Put
import kotlinx.serialization.mapper.Mapper
import logging.ConsoleAppender

open class Sandbox(val component: ComponentUnderTest) {
    constructor(module: Module) : this(ModuleUnderTest(module))
    constructor(route: HttpRoute) : this(RouteUnderTest(route))
    constructor(action: Action) : this(ActionUnderTest(action))

    private val console = ConsoleAppender()

    private val routes = when (component) {
        is ApplicationUnderTest<Any?, Any?> -> component.application.modules.flatMap {
            it.actions.mapEndpoint { a -> a.route }
        }
        is ModuleUnderTest<Any?> -> component.module.actions.mapEndpoint { it.route }
        is ActionUnderTest -> listOf(component.action.route)
        is RouteUnderTest -> listOf(component.route)
    }

    suspend fun request(request: HttpRequest): HttpResponse {
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

    suspend fun get(path: String) = request(HttpRequest(Get, path, mapOf(), mapOf(), null))

    suspend fun post(
        path: String,
        headers: Map<String, String> = mapOf(),
        body: String = "{}"
    ) = request(HttpRequest(Post, path, headers, mapOf(), body))

    suspend fun post(
        path: String,
        headers: Map<String, String> = mapOf(),
        body: Map<String, Any?>
    ) = post(path, headers, Mapper.encodeToString(body))

    suspend fun put(
        path: String,
        headers: Map<String, String> = mapOf(),
        body: String = "{}"
    ) = request(HttpRequest(Put, path, headers, mapOf(), body))
}