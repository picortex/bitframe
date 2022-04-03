package pimonitor.server.signup

import bitframe.core.RequestBody
import bitframe.server.http.HttpRequest
import bitframe.server.http.compulsoryBody
import bitframe.server.http.toHttpResponse
import io.ktor.http.HttpStatusCode.Companion.Created
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import later.await
import pimonitor.core.signup.SignUpServiceDaod
import pimonitor.core.signup.params.SignUpBusinessParams
import pimonitor.core.signup.params.SignUpIndividualParams
import response.response

class SignUpController(
    internal val service: SignUpServiceDaod
) {
    val json get() = service.config.json

    @OptIn(ExperimentalSerializationApi::class)
    suspend fun signUpAsIndividual(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.UnAuthorized<SignUpIndividualParams>>(req.compulsoryBody())
        resolve(service.signUpAsIndividual(rb).await(), Created)
    }.toHttpResponse()

    suspend fun signUpAsBusiness(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.UnAuthorized<SignUpBusinessParams>>(req.compulsoryBody())
        resolve(service.signUpAsBusiness(rb).await(), Created)
    }.toHttpResponse()
}