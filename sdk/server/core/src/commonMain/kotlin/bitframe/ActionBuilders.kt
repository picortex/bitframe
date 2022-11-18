package bitframe

import bitframe.annotations.BitframeServerDsl
import bitframe.http.HttpRequest
import bitframe.http.HttpResponse
import bitframe.http.HttpRoute
import io.ktor.http.*
import koncurrent.Later

@BitframeServerDsl
inline fun post(
    path: String,
    name: String = path,
    params: Map<String, Any?> = mapOf(),
    noinline handler: (HttpRequest) -> Later<HttpResponse>
) = Action(name, params, HttpRoute(method = HttpMethod.Post, path, handler))