package bitframe.authentication.client.signin

import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.signin.SignInCredentials
import bitframe.service.client.MiniService
import bitframe.service.client.config.KtorClientConfiguration
import bitframe.service.client.utils.unAuthorizedBodyOf
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.util.*
import later.Later
import later.later
import response.decodeResponseFromString
import response.responseOf

open class SignInServiceKtor(
    override val config: KtorClientConfiguration
) : SignInService(config), MiniService {
    private val path get() = config.url + "/api/authentication/sign-in"
    private val http get() = config.http
    private val json get() = config.json

    @OptIn(InternalAPI::class)
    override fun executeSignIn(credentials: SignInCredentials): Later<LoginConundrum> = scope.later {
        try {
            val resp = http.post(path) { body = json.unAuthorizedBodyOf(config.appId, credentials) }
            json.decodeResponseFromString(LoginConundrum.serializer(), resp.bodyAsText()).response()
        } catch (exp: ClientRequestException) {
            responseOf(cause = exp, message = "Failed to communicate with the server at ${config.url}").response()
        }
    }
}