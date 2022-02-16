package bitframe.server

import bitframe.server.controllers.AuthenticationController
import bitframe.server.signin.SignInAction
import bitframe.server.signin.SignInService

class AuthenticationModule(
    private val controller: AuthenticationController
) : Module {
    constructor(service: SignInService) : this(AuthenticationController(service))

    init {
//        controller.service.users.createIfNotExist(AuthenticationModule.GENESIS)
    }

    override val name = "authentication"

    override val actions: List<Action> = listOf(
        SignInAction(controller),
    )
}