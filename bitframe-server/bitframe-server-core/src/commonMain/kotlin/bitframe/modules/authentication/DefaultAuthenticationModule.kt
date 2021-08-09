package bitframe.modules.authentication

import bitframe.actions.Action
import bitframe.http.HttpResponse
import bitframe.http.HttpRoute
import io.ktor.http.*
import io.ktor.http.HttpMethod.Companion.Post

open class DefaultAuthenticationModule : AuthenticationModule {
    override val name = "authentication"
    override val actions: List<Action> = listOf(
        Action("login", mapOf(), HttpRoute(Post, "/login") {
            HttpResponse(HttpStatusCode.OK)
        })
    )
}