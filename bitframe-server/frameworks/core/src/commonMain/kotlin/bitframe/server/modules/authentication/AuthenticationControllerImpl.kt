package bitframe.server.modules.authentication

import bitframe.authentication.signin.SignInCredentials
import bitframe.response.response.response
import bitframe.server.http.HttpResponse
import bitframe.server.http.toHttpResponse
import io.ktor.http.*
import kotlinx.serialization.json.Json
import later.await

class AuthenticationControllerImpl(
    override val service: AuthenticationService
) : AuthenticationController {
    override suspend fun signIn(body: String?): HttpResponse = response {
        val credentials = Json.decodeFromString(
            deserializer = SignInCredentials.serializer(),
            string = body ?: throw IllegalArgumentException("A json body was not provided")
        )
        resolve(service.signIn.signIn(credentials).await(), HttpStatusCode.Accepted)
    }.toHttpResponse()
}