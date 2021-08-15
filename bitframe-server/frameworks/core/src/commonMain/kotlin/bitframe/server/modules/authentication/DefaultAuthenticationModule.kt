package bitframe.server.modules.authentication

import bitframe.server.actions.Action
import bitframe.server.http.HttpResponse
import bitframe.server.http.HttpRoute
import io.ktor.http.*
import io.ktor.http.HttpMethod.Companion.Post
import users.user.CreateUserParams

open class DefaultAuthenticationModule(
    default: CreateUserParams,
    val controller: AuthenticationController
) : AuthenticationModule {

    init {
        controller.usersService.createDefaultUserIfNotExist(default)
    }

    override val name = "authentication"

    override val actions: List<Action> = listOf(
        Action("login", mapOf(), HttpRoute(Post, "/login") {
            HttpResponse(HttpStatusCode.OK)
        })
    )
}