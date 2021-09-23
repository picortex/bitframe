package pimonitor.authentication.signup

import bitframe.authentication.LoginConundrum
import bitframe.authentication.LoginCredentials
import bitframe.server.http.HttpRequest
import bitframe.server.http.HttpResponse
import bitframe.server.http.compulsoryBody
import bitframe.server.modules.authentication.AuthenticationService
import duality.Result
import duality.catching
import duality.stringify
import io.ktor.http.*
import kotlinx.serialization.json.Json
import later.await
import pimonitor.IndividualRegistrationParams

class SignUpController(
    val signInService: AuthenticationService
) {
    suspend fun signUp(req: HttpRequest): HttpResponse {
        val res = catching {
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
        return HttpResponse(
            HttpStatusCode.OK,
            Result.stringify(LoginConundrum.serializer(), res)
        )
    }
}