package bitframe

import bitframe.http.HttpRequest
import bitframe.http.HttpResponse
import bitframe.http.HttpRoute
import io.ktor.http.*

open class Sandbox(val component: UnderTest) {

    constructor(application: Application) : this(ApplicationUnderTest(application))
    constructor(module: Module) : this(ModuleUnderTest(module))
    constructor(route: HttpRoute) : this(RouteUnderTest(route))

    fun request(request: HttpRequest): HttpResponse {
        val route = when (component) {
            is ApplicationUnderTest<*> -> component.application.modules.flatMap {
                it.routes
            }.find {
                it.path == request.path && it.method == request.method
            }
            is ModuleUnderTest<*> -> component.module.routes.find {
                it.path == request.path && it.method == request.method
            }
            is RouteUnderTest<*> -> component.route
        } ?: return HttpResponse(status = HttpStatusCode.NotFound)

        return try {
            val handler = route.handler
            handler(request)
        } catch (cause: Throwable) {
            HttpResponse(HttpStatusCode.InternalServerError, cause.message)
        }
    }

    fun get(path: String) = request(HttpRequest(HttpMethod.Get, path, mapOf(), null))

    fun post(path: String) = request(HttpRequest(HttpMethod.Post, path, mapOf(), null))
}