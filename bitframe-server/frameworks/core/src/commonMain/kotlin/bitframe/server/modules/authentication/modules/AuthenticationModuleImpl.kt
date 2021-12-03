package bitframe.server.modules.authentication.modules

import bitframe.server.BitframeService
import bitframe.server.actions.Action
import bitframe.server.modules.authentication.controllers.AuthenticationController
import bitframe.server.modules.authentication.controllers.AuthenticationControllerImpl
import bitframe.server.modules.authentication.services.signin.SignInAction

class AuthenticationModuleImpl(
    private val controller: AuthenticationController
) : AuthenticationModule {
    constructor(service: BitframeService) : this(AuthenticationControllerImpl(service))

    init {
        controller.service.users.createIfNotExist(AuthenticationModule.GENESIS)
    }

    override val name = "authentication"

    override fun signInAction(): Action = SignInAction(controller)

    override val actions: List<Action> = listOf(
        signInAction(),
    )
}