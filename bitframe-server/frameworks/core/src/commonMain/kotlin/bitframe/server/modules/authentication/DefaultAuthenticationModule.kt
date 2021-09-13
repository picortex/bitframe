package bitframe.server.modules.authentication

import bitframe.server.actions.Action
import bitframe.server.data.DAOProvider
import bitframe.server.http.HttpResponse
import bitframe.server.http.HttpRoute
import io.ktor.http.*
import io.ktor.http.HttpMethod.Companion.Get
import io.ktor.http.HttpMethod.Companion.Post
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import later.await
import users.account.Account
import users.user.Basic
import users.user.Contacts
import users.user.CreateUserParams
import users.user.User
import kotlin.jvm.JvmOverloads

open class DefaultAuthenticationModule @JvmOverloads constructor(
    private val controller: AuthenticationController,
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
            credentials = Basic("752748674", "genesis")
        )
    }

    override val name = "acceptance.authentication"

    private val json = Json {
        prettyPrint = true
    }

    override val actions: List<Action> = listOf(
        Action("login", mapOf(), HttpRoute(Post, "/login") {
            HttpResponse(HttpStatusCode.OK)
        }),
        Action("users", mapOf(), HttpRoute(Get, "/users") {
            val users = controller.service.users().await()
            HttpResponse(HttpStatusCode.OK, json.encodeToString(ListSerializer(User.serializer()), users))
        }),
        Action("accounts", mapOf(), HttpRoute(Get, "/accounts") {
            val accounts = controller.service.accounts().await()
            HttpResponse(HttpStatusCode.OK, json.encodeToString(ListSerializer(Account.serializer()), accounts))
        })
    )
}