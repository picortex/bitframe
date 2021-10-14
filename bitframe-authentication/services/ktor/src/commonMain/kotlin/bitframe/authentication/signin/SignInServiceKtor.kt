package bitframe.authentication.signin

import bitframe.authentication.apps.App
import bitframe.authentication.spaces.Space
import bitframe.authentication.users.User
import bitframe.response.response.decodeResponseFromString
import bitframe.service.MiniService
import bitframe.service.config.KtorClientConfiguration
import bitframe.service.utils.JsonContent
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json
import later.Later
import later.later
import kotlin.jvm.JvmOverloads

open class SignInServiceKtor @JvmOverloads constructor(
    override val config: KtorClientConfiguration
) : SignInService<Nothing?>(), MiniService {
    protected val path get() = config.url + "/api/authentication/sign-in"
    protected val http get() = config.http
    override fun executeSignIn(credentials: SignInCredentials): Later<LoginConundrum> = scope.later {
        val resp = try {
            http.post(path) { body = JsonContent(credentials) }
        } catch (exp: ClientRequestException) {
            exp.response
        }
        Json.decodeResponseFromString(LoginConundrum.serializer(), resp.readText()).response()
    }

    override fun makeSession(app: App, space: Space, user: User) = scope.later {
        Session.SignedIn(app, space, user, null)
    }
}