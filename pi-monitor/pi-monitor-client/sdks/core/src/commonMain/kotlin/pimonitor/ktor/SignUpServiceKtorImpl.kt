package pimonitor.ktor

import bitframe.service.config.KtorClientConfiguration
import bitframe.authentication.signin.LoginConundrum
import bitframe.response.response.decodeResponseFromString
import bitframe.service.MiniService
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.content.*
import kotlinx.serialization.json.Json
import later.Later
import later.later
import pimonitor.authentication.signup.IndividualRegistrationParams
import pimonitor.Monitor
import pimonitor.authentication.signup.SignUpService

class SignUpServiceKtorImpl(
    override val config: KtorClientConfiguration
) : SignUpService(), MiniService {
    private val client = config.http
    private val scope = config.scope
    override fun registerIndividuallyAs(
        person: IndividualRegistrationParams
    ): Later<LoginConundrum> = scope.later {
        val json = client.post<String>(config.url + "/api/authentication/sign-up") {
            body = TextContent(
                text = Json.encodeToString(IndividualRegistrationParams.serializer(), person),
                contentType = ContentType.Application.Json
            )
        }
        val res = Json.decodeResponseFromString(LoginConundrum.serializer(), json)
        res.response()
    }

    override fun register(business: Monitor.Business, representedBy: Monitor.Person): Later<LoginConundrum> {
        TODO("Not yet implemented")
    }
}