package bitframe.authentication.client.signin

import bitframe.authentication.signin.SignInCredentials
import bitframe.authentication.signin.SignInResult
import bitframe.service.client.MiniService
import bitframe.client.KtorServiceConfig
import bitframe.service.client.utils.of
import bitframe.core.service.requests.RequestBody
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.util.*
import later.Later
import later.later
import response.decodeResponseFromString

open class SignInServiceKtor(
    override val config: KtorServiceConfig
) : SignInService(config), MiniService {
    private val path get() = config.url + "/api/authentication/sign-in"
    private val http get() = config.http
    private val json get() = config.json

    @OptIn(InternalAPI::class)
    override fun signIn(rb: RequestBody.UnAuthorized<SignInCredentials>): Later<SignInResult> = scope.later {
        val resp = http.post(path) { body = json.of(rb) }
        json.decodeResponseFromString(SignInResult.serializer(), resp.bodyAsText()).response()
    }
}