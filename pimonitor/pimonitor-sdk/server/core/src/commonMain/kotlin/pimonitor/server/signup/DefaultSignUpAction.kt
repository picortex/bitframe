package pimonitor.server.signup

import bitframe.server.Action
import bitframe.server.http.HttpRoute
import io.ktor.http.*

fun DefaultSignUpAction(
    controller: SignUpController,
    name: String = "Sign Up",
    path: String = "/api/authentication/sign-up"
) = Action(name, mapOf(), HttpRoute(HttpMethod.Post, path) { req ->
    controller.signUp(req)
})