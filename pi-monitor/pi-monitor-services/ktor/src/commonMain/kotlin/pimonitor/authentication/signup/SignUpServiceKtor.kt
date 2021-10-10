package pimonitor.authentication.signup

import bitframe.authentication.signin.LoginConundrum
import bitframe.response.response.decodeResponseFromString
import bitframe.service.MiniService
import bitframe.service.config.KtorClientConfiguration
import bitframe.service.utils.JsonContent
import io.ktor.client.request.*
import kotlinx.serialization.json.Json
import later.Later
import later.later
import pimonitor.Monitor
import pimonitor.monitors.SignUpParams

class SignUpServiceKtor(
    override val config: KtorClientConfiguration
) : SignUpService(), MiniService {
    private val client = config.http
    private val scope = config.scope
    
    override fun signUp(params: SignUpParams): Later<SignUpResult> = scope.later {
        val json = client.post<String>(config.url + "/api/authentication/sign-up") {
            body = JsonContent(params)
        }
        val res = Json.decodeResponseFromString(SignUpResult.serializer(), json)
        res.response()
    }
}