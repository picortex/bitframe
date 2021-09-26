package bitframe.server.modules.authentication

import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.signin.LoginCredentials
import duality.Result
import duality.catching
import kotlinx.serialization.json.Json
import later.await

class DefaultAuthenticationController(
    override val service: AuthenticationService
) : AuthenticationController {
    override suspend fun signIn(body: String?): Result<LoginConundrum> = catching {
        val credentials = Json.decodeFromString(
            deserializer = LoginCredentials.serializer(),
            string = body ?: throw IllegalArgumentException("A json body was not provided")
        )
        service.signIn(credentials).await()
    }
}