package pimonitor.ktor

import bitframe.authentication.KtorClientConfiguration
import bitframe.authentication.signin.LoginConundrum
import bitframe.response.response.decodeResponseFromString
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.content.*
import kotlinx.serialization.json.Json
import later.Later
import later.later
import pimonitor.IndividualRegistrationParams
import pimonitor.Monitor
import pimonitor.authentication.SignUpService

class SignUpServiceKtorImpl(
    override val config: KtorClientConfiguration
) : SignUpService {
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