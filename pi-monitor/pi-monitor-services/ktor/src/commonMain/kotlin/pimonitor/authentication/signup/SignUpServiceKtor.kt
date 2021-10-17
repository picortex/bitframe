package pimonitor.authentication.signup

import bitframe.events.EventBus
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

class SignUpServiceKtor(
    override val bus: EventBus,
    override val config: KtorClientConfiguration
) : SignUpService(bus, config), MiniService {
    private val client = config.http
    private val baseUrl = "${config.url}/api/authentication"

    override fun executeSignUp(params: SignUpParams): Later<SignUpResult> = scope.later {
        val resp = try {
            client.post("$baseUrl/sign-up") { body = JsonContent(params) }
        } catch (err: ClientRequestException) {
            err.response
        }
        Json.decodeResponseFromString(SignUpResult.serializer(), resp.readText()).response()
    }
}