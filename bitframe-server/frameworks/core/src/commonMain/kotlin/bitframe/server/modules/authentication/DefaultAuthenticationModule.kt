package bitframe.server.modules.authentication

import bitframe.authentication.signin.Basic
import bitframe.authentication.spaces.Space
import bitframe.authentication.users.Contacts
import bitframe.authentication.users.CreateUserParams
import bitframe.authentication.users.User
import bitframe.server.actions.Action
import bitframe.server.data.DAOProvider
import bitframe.server.http.HttpResponse
import bitframe.server.http.HttpRoute
import bitframe.server.modules.authentication.signin.DefaultSignInAction
import io.ktor.http.*
import io.ktor.http.HttpMethod.Companion.Get
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import later.await
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
    ) : this(DefaultAuthenticationService(provider.users, provider.spaces), default)

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
        DefaultSignInAction(controller),
        Action("users", mapOf(), HttpRoute(Get, "/users") {
            val users = controller.service.users().await()
            HttpResponse(HttpStatusCode.OK, json.encodeToString(ListSerializer(User.serializer()), users))
        }),
        Action("accounts", mapOf(), HttpRoute(Get, "/spaces") {
            val accounts = controller.service.spaces().await()
            HttpResponse(HttpStatusCode.OK, json.encodeToString(ListSerializer(Space.serializer()), accounts))
        })
    )
}