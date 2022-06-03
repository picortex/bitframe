package pimonitor.client.signup

import bitframe.client.ServiceConfigKtor
import bitframe.client.of
import bitframe.core.RequestBody
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.util.*
import later.later
import pimonitor.client.utils.pathV1
import pimonitor.core.signup.SignUpResult
import pimonitor.core.signup.params.SignUpBusinessParams
import pimonitor.core.signup.params.SignUpIndividualParams
import response.decodeResponseFromString

class SignUpServiceKtor(
    private val config: ServiceConfigKtor
) : SignUpService(config) {
    private val client = config.http
    private val json = config.json

    @OptIn(InternalAPI::class)
    override fun signUpAsIndividual(rb: RequestBody.UnAuthorized<SignUpIndividualParams>) = config.scope.later {
        val resp = client.post(config.pathV1.signUpIndividual) {
            body = json.of(rb)
        }
        json.decodeResponseFromString(SignUpResult.serializer(), resp.bodyAsText()).response()
    }

    @OptIn(InternalAPI::class)
    override fun signUpAsBusiness(rb: RequestBody.UnAuthorized<SignUpBusinessParams>) = config.scope.later {
        val resp = client.post(config.pathV1.signUpBusiness) {
            body = json.of(rb)
        }
        json.decodeResponseFromString(SignUpResult.serializer(), resp.bodyAsText()).response()
    }
}