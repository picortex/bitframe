package pimonitor.client.authentication.signup

import bitframe.response.response.decodeResponseFromString
import bitframe.service.client.MiniService
import bitframe.service.client.config.KtorClientConfiguration
import bitframe.service.client.utils.JsonContent
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import later.Later
import later.later
import pimonitor.authentication.signup.SignUpParams
import pimonitor.authentication.signup.SignUpResult
import pimonitor.authentication.signup.SignUpService

class SignUpServiceKtor(
    override val config: KtorClientConfiguration
) : SignUpService(config), MiniService {
    private val client = config.http
    private val baseUrl = "${config.url}/api/authentication"
    private val scope = config.scope
    private val json = config.json

    override fun executeSignUp(params: SignUpParams): Later<SignUpResult> = scope.later {
        val resp = try {
            client.post("$baseUrl/sign-up") { body = JsonContent(params) }
        } catch (err: ClientRequestException) {
            err.response
        }
        json.decodeResponseFromString(SignUpResult.serializer(), resp.readText()).response()
    }
}