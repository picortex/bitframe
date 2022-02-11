package bitframe.authentication.client.signin

import bitframe.authentication.signin.RawSignInCredentials
import bitframe.authentication.signin.SignInResult
import bitframe.service.client.MiniService
import bitframe.service.client.config.KtorClientConfiguration
import bitframe.service.client.utils.of
import bitframe.service.requests.RequestBody
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.util.*
import later.Later
import later.later
import response.decodeResponseFromString

open class SignInServiceKtor(
    override val config: KtorClientConfiguration
) : SignInService(config), MiniService {
    private val path get() = config.url + "/api/authentication/sign-in"
    private val http get() = config.http
    private val json get() = config.json

    @OptIn(InternalAPI::class)
    override fun signIn(rb: RequestBody.UnAuthorized<RawSignInCredentials>): Later<SignInResult> = scope.later {
        val resp = http.post(path) { body = json.of(rb) }
        json.decodeResponseFromString(SignInResult.serializer(), resp.bodyAsText()).response()
    }
}