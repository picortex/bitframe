package pimonitor.client.authentication.signup

import bitframe.client.KtorServiceConfig
import bitframe.service.client.utils.of
import bitframe.core.service.requests.RequestBody
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.util.*
import pimonitor.authentication.signup.SignUpParams
import pimonitor.authentication.signup.SignUpResult
import response.decodeResponseFromString

class SignUpServiceKtor(
    override val config: KtorServiceConfig
) : SignUpService(config) {
    private val client = config.http
    private val baseUrl = "${config.url}/api/authentication"
    private val json = config.json

    val logger = config.logger.with(
        "source" to this::class.simpleName
    )

    @OptIn(InternalAPI::class)
    override fun signUp(rb: RequestBody.UnAuthorized<SignUpParams>) = scope.later {
        val resp = client.post("$baseUrl/sign-up") {
            body = json.of(rb, SignUpParams.serializer())
        }
        logger.log(resp.bodyAsText())
        json.decodeResponseFromString(SignUpResult.serializer(), resp.bodyAsText()).response()
    }
}