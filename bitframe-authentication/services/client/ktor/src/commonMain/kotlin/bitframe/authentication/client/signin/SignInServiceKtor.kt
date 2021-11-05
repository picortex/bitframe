package bitframe.authentication.client.signin

import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.signin.SignInCredentials
import bitframe.response.response.decodeResponseFromString
import bitframe.service.client.MiniService
import bitframe.service.client.config.KtorClientConfiguration
import bitframe.service.client.utils.JsonContent
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json
import later.Later
import later.later

open class SignInServiceKtor(
    override val config: KtorClientConfiguration
) : SignInService(config), MiniService {
    private val path get() = config.url + "/api/authentication/sign-in"
    private val http get() = config.http
    private val json get() = config.json
    override fun executeSignIn(credentials: SignInCredentials): Later<LoginConundrum> = scope.later {
        val resp = try {
            http.post(path) { body = JsonContent(credentials) }
        } catch (exp: ClientRequestException) {
            exp.response
        }
        json.decodeResponseFromString(LoginConundrum.serializer(), resp.readText()).response()
    }
}