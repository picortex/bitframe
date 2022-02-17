package pimonitor.client.signup

import bitframe.client.KtorServiceConfig
import bitframe.client.of
import bitframe.core.RequestBody
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.util.*
import later.later
import pimonitor.core.signup.SignUpParams
import pimonitor.core.signup.SignUpResult
import response.decodeResponseFromString

class SignUpServiceKtor(
    override val config: KtorServiceConfig
) : SignUpService(config) {
    private val client = config.http
    private val baseUrl = "${config.url}/api/authentication"
    private val json = config.json

    @OptIn(InternalAPI::class)
    override fun signUp(rb: RequestBody.UnAuthorized<SignUpParams>) = scope.later {
        val resp = client.post("$baseUrl/sign-up") {
            body = json.of(rb, SignUpParams.serializer())
        }
        logger.log(resp.bodyAsText())
        json.decodeResponseFromString(SignUpResult.serializer(), resp.bodyAsText()).response()
    }
}