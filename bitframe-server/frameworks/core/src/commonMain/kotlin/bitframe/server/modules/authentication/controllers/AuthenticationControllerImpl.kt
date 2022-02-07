package bitframe.server.modules.authentication.controllers

import bitframe.authentication.signin.SignInCredentials
import response.response
import bitframe.server.BitframeService
import bitframe.server.http.HttpResponse
import bitframe.server.http.toHttpResponse
import io.ktor.http.*
import kotlinx.serialization.json.Json
import later.await

class AuthenticationControllerImpl(
    override val service: BitframeService
) : AuthenticationController {
    override suspend fun signIn(body: String?): HttpResponse = response {
        val credentials = Json.decodeFromString(
            deserializer = SignInCredentials.serializer(),
            string = body ?: reject("A json body was not provided")
        )
        resolve(service.signIn.signIn(credentials).await(), HttpStatusCode.Accepted)
    }.toHttpResponse()
}