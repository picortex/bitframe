package bitframe.client.signin

import bitframe.client.KtorServiceConfig
import bitframe.client.of
import bitframe.core.RequestBody
import bitframe.core.signin.SignInCredentials
import bitframe.core.signin.SignInResult
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.util.*
import later.Later
import later.later
import response.decodeResponseFromString

open class SignInServiceKtor(
    override val config: KtorServiceConfig
) : SignInService(config) {
    private val path get() = config.url + "/api/authentication/sign-in"
    private val http get() = config.http
    private val json get() = config.json

    @OptIn(InternalAPI::class)
    override fun signIn(rb: RequestBody.UnAuthorized<SignInCredentials>): Later<SignInResult> = scope.later {
        val resp = http.post(path) { body = json.of(rb) }
        json.decodeResponseFromString(SignInResult.serializer(), resp.bodyAsText()).response()
    }
}