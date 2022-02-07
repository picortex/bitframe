package pimonitor.client.authentication.signup

import bitframe.service.client.MiniService
import bitframe.service.client.config.KtorClientConfiguration
import bitframe.service.client.utils.unAuthorizedBodyOf
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.util.*
import later.Later
import later.later
import pimonitor.authentication.signup.SignUpParams
import pimonitor.authentication.signup.SignUpResult
import pimonitor.authentication.signup.SignUpService
import response.response.decodeResponseFromString

class SignUpServiceKtor(
    override val config: KtorClientConfiguration
) : SignUpService(config), MiniService {
    private val client = config.http
    private val baseUrl = "${config.url}/api/authentication"
    private val scope = config.scope
    private val json = config.json

    @OptIn(InternalAPI::class)
    override fun executeSignUp(params: SignUpParams): Later<SignUpResult> = scope.later {
        val resp = client.post("$baseUrl/sign-up") { body = json.unAuthorizedBodyOf(config.appId, params) }
        json.decodeResponseFromString(SignUpResult.serializer(), resp.bodyAsText()).response()
    }
}