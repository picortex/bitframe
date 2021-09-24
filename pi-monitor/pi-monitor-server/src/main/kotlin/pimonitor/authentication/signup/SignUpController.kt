package pimonitor.authentication.signup

import bitframe.authentication.LoginCredentials
import bitframe.http.response.catching
import bitframe.server.http.HttpRequest
import bitframe.server.http.compulsoryBody
import bitframe.server.modules.authentication.AuthenticationService
import kotlinx.serialization.json.Json
import later.Later
import later.await
import pimonitor.IndividualRegistrationParams

class SignUpController(
    val signInService: AuthenticationService
) {
    suspend fun signUp(req: HttpRequest) = catching {
        val params = Json.decodeFromString(
            IndividualRegistrationParams.serializer(),
            req.compulsoryBody()
        )
        val credentials = LoginCredentials(
            alias = params.email ?: throw IllegalArgumentException("Email must not be null"),
            password = params.password ?: throw IllegalArgumentException("Passwords must not be null")
        )
        signInService.signIn(credentials).await()
    }
}