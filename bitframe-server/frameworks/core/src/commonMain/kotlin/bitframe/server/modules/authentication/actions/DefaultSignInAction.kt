package bitframe.server.modules.authentication.actions

import bitframe.server.actions.Action
import bitframe.server.http.HttpRoute
import bitframe.server.modules.authentication.AuthenticationController
import io.ktor.http.*

@Suppress("FunctionName")
fun DefaultSignInAction(
    controller: AuthenticationController,
    name: String = "sign-in",
    path: String = "/api/authentication/sign-in"
) = Action(name, mapOf(), HttpRoute(HttpMethod.Post, path) { controller.signIn(it.body) })