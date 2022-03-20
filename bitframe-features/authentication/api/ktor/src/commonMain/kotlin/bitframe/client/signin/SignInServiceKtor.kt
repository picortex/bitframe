package bitframe.client.signin

import bitframe.client.ServiceConfigKtor
import bitframe.client.of
import bitframe.client.rest.pathV1
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
    override val config: ServiceConfigKtor
) : SignInService(config) {
    private val http get() = config.http
    private val json get() = config.json

    @OptIn(InternalAPI::class)
    override fun signIn(rb: RequestBody.UnAuthorized<SignInCredentials>): Later<SignInResult> = scope.later {
        val resp = http.post(config.pathV1.signIn) { body = json.of(rb) }
        json.decodeResponseFromString(SignInResult.serializer(), resp.bodyAsText()).response()
    }
}