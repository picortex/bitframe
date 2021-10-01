package bitframe.server.modules.authentication

import bitframe.authentication.signin.Basic
import bitframe.authentication.users.Contacts
import bitframe.authentication.users.CreateUserParams
import bitframe.server.actions.Action
import bitframe.server.data.DAOProvider
import bitframe.server.modules.authentication.signin.SignInAction
import kotlin.jvm.JvmOverloads

open class AuthenticationModuleImpl @JvmOverloads constructor(
    private val controller: AuthenticationController,
    default: CreateUserParams = GENESIS
) : AuthenticationModule {

    @JvmOverloads
    constructor(
        service: AuthenticationService,
        default: CreateUserParams = GENESIS
    ) : this(AuthenticationControllerImpl(service), default)

    @JvmOverloads
    constructor(
        provider: DAOProvider,
        default: CreateUserParams = GENESIS
    ) : this(AuthenticationServiceImpl(provider.users, provider.spaces), default)

    init {
        controller.service.users.createIfNotExist(default)
    }

    companion object {
        val GENESIS = CreateUserParams(
            name = "Genesis",
            contacts = Contacts.None,
            credentials = Basic("ghost@genesis.com", "genesis")
        )
    }

    override val name = "authentication"

    override fun signInAction(): Action = SignInAction(controller)

    override val actions: List<Action> = listOf(
        signInAction(),
    )
}