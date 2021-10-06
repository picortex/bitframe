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

class SignUpServiceKtor(
    override val config: KtorClientConfiguration
) : SignUpService(), MiniService {
    private val client = config.http
    private val scope = config.scope
    override fun registerIndividuallyAs(
        person: IndividualRegistrationParams
    ): Later<LoginConundrum> = scope.later {
        val json = client.post<String>(config.url + "/api/authentication/sign-up") {
            body = JsonContent(person)
        }
        val res = Json.decodeResponseFromString(LoginConundrum.serializer(), json)
        res.response()
    }

    override fun register(business: Monitor.Business, representedBy: Monitor.Person): Later<LoginConundrum> {
        TODO("Not yet implemented")
    }

    override fun signUp(params: SignUpParams): Later<SignUpResult> {
        TODO("Not yet implemented")
    }
}