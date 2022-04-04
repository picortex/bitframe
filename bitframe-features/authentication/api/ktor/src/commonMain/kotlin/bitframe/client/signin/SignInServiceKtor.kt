package bitframe.client.signin

import bitframe.client.ServiceConfigKtor
import bitframe.client.of
import bitframe.client.rest.pathV1
import bitframe.core.RequestBody
import bitframe.core.signin.SignInParams
import bitframe.core.signin.SignInRawParams
import bitframe.core.signin.SignInResult
import io.ktor.client.request.*
import io.ktor.client.statement.*
import later.Later
import later.later
import response.decodeResponseFromString

open class SignInServiceKtor(
    private val config: ServiceConfigKtor
) : SignInService(config) {
    private val http get() = config.http
    private val json get() = config.json

    override fun signIn(rb: RequestBody.UnAuthorized<SignInParams>): Later<SignInResult> = scope.later {
        val resp = http.post(config.pathV1.signIn) { setBody(json.of(rb)) }
        json.decodeResponseFromString(SignInResult.serializer(), resp.bodyAsText()).response()
    }
}