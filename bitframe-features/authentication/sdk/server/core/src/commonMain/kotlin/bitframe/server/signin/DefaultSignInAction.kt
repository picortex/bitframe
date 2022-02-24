package bitframe.server.signin

import bitframe.server.Action
import bitframe.server.controllers.AuthenticationController
import bitframe.server.http.HttpRoute
import io.ktor.http.*

@Suppress("FunctionName")
fun SignInAction(
    controller: AuthenticationController,
    name: String = "sign-in",
    path: String = "/api/authentication/sign-in"
) = Action(name, mapOf(), HttpRoute(HttpMethod.Post, path) {
    controller.signIn(it)
})