package pimonitor.client.authentication.signup

import bitframe.response.response.decodeResponseFromString
import bitframe.service.client.MiniService
import bitframe.service.client.utils.JsonContent
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json
import later.Later
import later.later
import pimonitor.authentication.signup.SignUpParams
import pimonitor.authentication.signup.SignUpResult
import pimonitor.authentication.signup.SignUpService

class SignUpServiceKtor(
    override val config: SignUpServiceKtorConfig
) : SignUpService(config), MiniService {
    private val client = config.http
    private val baseUrl = "${config.url}/api/authentication"
    private val scope = config.scope

    override fun executeSignUp(params: SignUpParams): Later<SignUpResult> = scope.later {
        val resp = try {
            client.post("$baseUrl/sign-up") { body = JsonContent(params) }
        } catch (err: ClientRequestException) {
            err.response
        }
        Json.decodeResponseFromString(SignUpResult.serializer(), resp.readText()).response()
    }
}