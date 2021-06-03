package bitframe

import bitframe.http.HttpRequest
import bitframe.http.HttpResponse
import io.ktor.http.*

open class ApplicationSandbox<A : Application>(val app: A) {
    fun request(request: HttpRequest): HttpResponse {
        val route = app.modules.flatMap {
            it.routes
        }.find {
            it.path == request.path && it.method == request.method
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