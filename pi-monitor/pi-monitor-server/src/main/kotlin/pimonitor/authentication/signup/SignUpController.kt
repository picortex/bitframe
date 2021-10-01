package pimonitor.authentication.signup

import bitframe.authentication.users.UsersService
import bitframe.response.response.response
import bitframe.server.http.HttpRequest
import bitframe.server.http.compulsoryBody
import bitframe.server.http.toHttpResponse
import io.ktor.http.HttpStatusCode.Companion.Created
import kotlinx.serialization.json.Json
import later.await

private val json = Json {
    encodeDefaults = true
    ignoreUnknownKeys = true
}

class SignUpController(
    private val service: SignUpService
) {
    constructor(service: UsersService) : this(SignUpServiceImpl(service))

    suspend fun signUp(req: HttpRequest) = response {
        val params = json.decodeFromString(
            IndividualRegistrationParams.serializer(),
            req.compulsoryBody()
        )
        val conundrum = service.registerIndividuallyAs(params).await()
        resolve(conundrum, Created)
    }.toHttpResponse()
}