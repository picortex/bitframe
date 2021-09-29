package pimonitor.authentication.signup

import bitframe.authentication.users.Contacts
import bitframe.response.response.response
import bitframe.server.http.HttpRequest
import bitframe.server.http.compulsoryBody
import bitframe.server.http.toHttpResponse
import bitframe.server.modules.authentication.AuthenticationService
import bitframe.server.modules.authentication.RegisterUserParams
import io.ktor.http.HttpStatusCode.Companion.Created
import kotlinx.serialization.json.Json
import later.await
import pimonitor.IndividualRegistrationParams

private val json = Json {
    encodeDefaults = true
    ignoreUnknownKeys = true
}

class SignUpController(
    val authenticationService: AuthenticationService
) {
    suspend fun signUp(req: HttpRequest) = response {
        val params = json.decodeFromString(
            IndividualRegistrationParams.serializer(),
            req.compulsoryBody()
        )

        val conundrum = authenticationService.registerUser(
            user = RegisterUserParams(
                name = params.name ?: throw RuntimeException("Name must not be null"),
                contacts = Contacts.of(params.email ?: throw RuntimeException("Name must not be null")),
                password = params.password ?: throw RuntimeException("Password must not be null")
            )
        ).await()
        resolve(conundrum, Created)
    }.toHttpResponse()
}