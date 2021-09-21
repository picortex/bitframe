package bitframe.server.modules.authentication

import bitframe.authentication.LoginCredentials
import bitframe.server.http.HttpResponse
import io.ktor.http.*
import kotlinx.serialization.json.Json
import later.await

class DefaultAuthenticationController(
    override val service: AuthenticationService
) : AuthenticationController {
    override suspend fun signIn(body: String?): HttpResponse = try {
        val credentials = Json.decodeFromString(
            deserializer = LoginCredentials.serializer(),
            string = body ?: throw IllegalArgumentException("A json body was not provided")
        )
        service.signIn(credentials).await()
    } catch (err: Throwable) {
        HttpResponse(HttpStatusCode.BadRequest)
    }
}