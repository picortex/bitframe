package bitframe.http

import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import koncurrent.Later
import koncurrent.later.await
import response.Error
import response.Status
import response.responseOf

data class HttpRoute(
    val method: HttpMethod,
    val path: String,
    val handler: (HttpRequest) -> Later<HttpResponse>
) {
    fun info() = mapOf(
        "method" to method.value,
        "path" to path
    )

    override fun toString() = "${method.value} $path"
}