package pimonitor.authentication.signup

import bitframe.response.response.decodeResponseFromString
import bitframe.service.MiniService
import bitframe.service.config.KtorClientConfiguration
import bitframe.service.utils.JsonContent
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json
import later.Later
import later.later
import pimonitor.monitors.SignUpParams

class SignUpServiceKtor(
    override val config: KtorClientConfiguration
) : SignUpService(), MiniService {
    private val client = config.http
    private val scope = config.scope
    private val baseUrl = "${config.url}/api/authentication"

    override fun signUp(params: SignUpParams): Later<SignUpResult> = scope.later {
        validate(params)
        val resp = try {
            client.post("$baseUrl/sign-up") { body = JsonContent(params) }
        } catch (err: ClientRequestException) {
            err.response
        }
        Json.decodeResponseFromString(SignUpResult.serializer(), resp.readText()).response()
    }
}