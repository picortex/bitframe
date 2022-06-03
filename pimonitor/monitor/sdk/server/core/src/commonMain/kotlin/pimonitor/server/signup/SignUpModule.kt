package pimonitor.server.signup

import bitframe.server.Action
import bitframe.server.Module
import bitframe.server.http.HttpRoute
import io.ktor.http.*
import pimonitor.server.utils.pathV1

class SignUpModule(
    val controller: SignUpController
) : Module {
    override val name: String = "sign-up"
    private val config get() = controller.service.config
    override val actions: List<Action> = listOf(
        Action(name, mapOf(), HttpRoute(HttpMethod.Post, config.pathV1.signUpIndividual) { req ->
            controller.signUpAsIndividual(req)
        }),
        Action(name, mapOf(), HttpRoute(HttpMethod.Post, config.pathV1.signUpBusiness) { req ->
            controller.signUpAsBusiness(req)
        })
    )
}