package bitframe.server

import bitframe.server.controllers.AuthenticationController
import bitframe.server.http.HttpRoute
import bitframe.server.rest.pathV1
import io.ktor.http.*

class AuthenticationModule(
    private val controller: AuthenticationController
) : Module {

    override val name = "authentication"

    private val config get() = controller.signInService.config

    override val actions: List<Action> = listOf(
        Action("sign in", mapOf(), HttpRoute(HttpMethod.Post, config.pathV1.signIn) {
            controller.signIn(it)
        }),
        Action("change-password", mapOf(), HttpRoute(HttpMethod.Post, config.pathV1.changePassword) {
            controller.changePassword(it)
        })
    )
}