package pimonitor.server.signup

import bitframe.server.Action
import bitframe.server.Module
import bitframe.server.http.HttpRoute
import io.ktor.http.*

class SignUpModule(
    val controller: SignUpController
) : Module {
    val basePath = "/api/authentication/sign-up"
    override val name: String = "sign-up"
    override val actions: List<Action> = listOf(
        Action(name, mapOf(), HttpRoute(HttpMethod.Post, "$basePath/individual") { req ->
            controller.signUpAsIndividual(req)
        }),
        Action(name, mapOf(), HttpRoute(HttpMethod.Post, "$basePath/business") { req ->
            controller.signUpAsBusiness(req)
        })
    )
}