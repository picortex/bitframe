package bitframe.server.modules.authentication.services.signin

import bitframe.server.actions.Action
import bitframe.server.http.HttpRoute
import bitframe.server.modules.authentication.controllers.AuthenticationController
import io.ktor.http.*

@Suppress("FunctionName")
fun SignInAction(
    controller: AuthenticationController,
    name: String = "sign-in",
    path: String = "/api/authentication/sign-in"
) = Action(name, mapOf(), HttpRoute(HttpMethod.Post, path) {
    controller.signIn(it.body)
})