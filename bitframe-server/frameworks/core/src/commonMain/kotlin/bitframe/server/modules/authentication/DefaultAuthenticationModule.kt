package bitframe.server.modules.authentication

import bitframe.server.actions.Action
import bitframe.server.data.DAOProvider
import bitframe.server.http.HttpResponse
import bitframe.server.http.HttpRoute
import io.ktor.http.*
import io.ktor.http.HttpMethod.Companion.Post
import users.user.Basic
import users.user.Contacts
import users.user.CreateUserParams
import kotlin.jvm.JvmOverloads

open class DefaultAuthenticationModule @JvmOverloads constructor(
    val controller: AuthenticationController,
    default: CreateUserParams = GENESIS
) : AuthenticationModule {

    @JvmOverloads
    constructor(
        service: AuthenticationService,
        default: CreateUserParams = GENESIS
    ) : this(DefaultAuthenticationController(service), default)

    @JvmOverloads
    constructor(
        provider: DAOProvider,
        default: CreateUserParams = GENESIS
    ) : this(DefaultAuthenticationService(provider.users, provider.accounts), default)

    init {
        controller.service.createDefaultUserIfNotExist(default)
    }

    companion object {
        val GENESIS = CreateUserParams(
            name = "Genesis",
            contacts = Contacts.None,
            credentials = Basic("genesis", "genesis")
        )
    }

    override val name = "authentication"

    override val actions: List<Action> = listOf(
        Action("login", mapOf(), HttpRoute(Post, "/login") {
            HttpResponse(HttpStatusCode.OK)
        })
    )
}