package pimonitor.client.signup

import bitframe.client.ServiceConfigKtor
import bitframe.client.of
import bitframe.core.RequestBody
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.util.*
import later.later
import pimonitor.core.signup.SignUpResult
import pimonitor.core.signup.params.BusinessSignUpParams
import pimonitor.core.signup.params.IndividualSignUpParams
import response.decodeResponseFromString

class SignUpServiceKtor(
    override val config: ServiceConfigKtor
) : SignUpService {
    private val client = config.http
    private val baseUrl = "${config.url}/api/authentication"
    private val json = config.json

    @OptIn(InternalAPI::class)
    override fun signUpAsIndividual(rb: RequestBody.UnAuthorized<IndividualSignUpParams>) = config.scope.later {
        val resp = client.post("$baseUrl/sign-up/individual") {
            body = json.of(rb)
        }
        json.decodeResponseFromString(SignUpResult.serializer(), resp.bodyAsText()).response()
    }

    @OptIn(InternalAPI::class)
    override fun signUpAsBusiness(rb: RequestBody.UnAuthorized<BusinessSignUpParams>) = config.scope.later {
        val resp = client.post("$baseUrl/sign-up/business") {
            body = json.of(rb)
        }
        json.decodeResponseFromString(SignUpResult.serializer(), resp.bodyAsText()).response()
    }
}