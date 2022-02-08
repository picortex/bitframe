package bitframe.server.modules.authentication.controllers

import bitframe.authentication.signin.SignInCredentials
import response.response
import bitframe.server.BitframeService
import bitframe.server.http.HttpRequest
import bitframe.server.http.HttpResponse
import bitframe.server.http.compulsoryBody
import bitframe.server.http.toHttpResponse
import bitframe.service.requests.RequestBody
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import later.await

class AuthenticationControllerImpl(
    override val service: BitframeService
) : AuthenticationController {
    override suspend fun signIn(req: HttpRequest): HttpResponse = response {
        val rb = Json.decodeFromString<RequestBody.UnAuthorized<SignInCredentials>>(req.compulsoryBody())
        resolve(service.signIn.signIn(rb).await(), HttpStatusCode.Accepted)
    }.toHttpResponse()
}