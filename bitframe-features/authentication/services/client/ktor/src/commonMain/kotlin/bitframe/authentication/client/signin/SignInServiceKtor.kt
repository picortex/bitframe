package bitframe.authentication.client.signin

import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.signin.SignInCredentials
import bitframe.response.response.decodeResponseFromString
import bitframe.response.response.responseOf
import bitframe.service.client.MiniService
import bitframe.service.client.utils.JsonContent
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.util.*
import later.Later
import later.later

open class SignInServiceKtor(
    override val config: SignInServiceKtorConfig
) : SignInService(config), MiniService {
    private val path get() = config.url + "/api/authentication/sign-in"
    private val http get() = config.http
    private val json get() = config.json

    @OptIn(InternalAPI::class)
    override fun executeSignIn(credentials: SignInCredentials): Later<LoginConundrum> = scope.later {
        try {
            val resp = http.post(path) { body = JsonContent(credentials) }
            json.decodeResponseFromString(LoginConundrum.serializer(), resp.bodyAsText()).response()
        } catch (exp: ClientRequestException) {
            responseOf(cause = exp, message = "Failed to communicate with the server at ${config.url}").response()
        }
    }
}