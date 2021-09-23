package bitframe.server.modules.authentication.actions

import bitframe.authentication.LoginConundrum
import bitframe.server.actions.Action
import bitframe.server.http.HttpResponse
import bitframe.server.http.HttpRoute
import bitframe.server.http.compulsoryBody
import bitframe.server.modules.authentication.AuthenticationController
import duality.Result
import duality.stringify
import duality.toFailure
import io.ktor.http.*

@Suppress("FunctionName")
fun DefaultSignInAction(
    controller: AuthenticationController,
    name: String = "sign-in",
    path: String = "/api/authentication/sign-in"
) = Action(name, mapOf(), HttpRoute(HttpMethod.Post, path) {
    try {
        HttpResponse(
            status = HttpStatusCode.OK,
            body = Result.stringify(
                serializer = LoginConundrum.serializer(),
                res = controller.signIn(it.compulsoryBody())
            )
        )
    } catch (err: Throwable) {
        HttpResponse(
            status = HttpStatusCode.InternalServerError,
            body = Result.stringify(LoginConundrum.serializer(), err.toFailure())
        )
    }
})