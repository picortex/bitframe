package bitframe.server.modules.authentication.modules

import bitframe.server.BitframeApplicationConfig
import bitframe.server.actions.Action
import bitframe.server.data.DAOProvider
import bitframe.server.modules.authentication.services.signin.SignInAction

class AuthenticationModuleImpl(
    private val config: AuthenticationModuleConfig
) : AuthenticationModule {
    constructor(config: BitframeApplicationConfig<DAOProvider>) : this(AuthenticationModuleConfig(config))

    init {
        config.controller.service.users.createIfNotExist(config.defaultUserParams)
    }

    override val name = "authentication"

    override fun signInAction(): Action = SignInAction(config.controller)

    override val actions: List<Action> = listOf(
        signInAction(),
    )
}