package pimonitor.authentication.signup

import bitframe.server.actions.Action
import bitframe.server.http.HttpResponse
import bitframe.server.http.HttpRoute
import io.ktor.http.*

fun DefaultSignUpAction(
    controller: SignUpController,
    name: String = "Sign Up",
    path: String = "/api/authentication/sign-up"
) = Action(name, mapOf(), HttpRoute(HttpMethod.Post, path) { req ->
    val res = controller.signUp(req)
    HttpResponse(HttpStatusCode.OK)
})