package bitframe.server.modules.authentication.controllers

import bitframe.authentication.signin.RawSignInCredentials
import response.response
import bitframe.server.BitframeService
import bitframe.server.http.HttpRequest
import bitframe.server.http.HttpResponse
import bitframe.server.http.compulsoryBody
import bitframe.server.http.toHttpResponse
import bitframe.service.requests.RequestBody
import bitframe.service.requests.map
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import later.await

class AuthenticationControllerImpl(
    override val service: BitframeService
) : AuthenticationController {
    override suspend fun signIn(req: HttpRequest): HttpResponse = response {
        val rb = try {
            Json.decodeFromString<RequestBody.UnAuthorized<RawSignInCredentials>>(req.compulsoryBody())
        } catch (err: Throwable) {
            null
        } ?: try {
            Json.decodeFromString<RequestBody.UnAuthorized<RawEmailPasswordSignInCredential>>(req.compulsoryBody()).map { it.toRawSignInCredentials() }
        } catch (err: Throwable) {
            null
        } ?: try {
            Json.decodeFromString<RequestBody.UnAuthorized<RawPhonePasswordSignInCredential>>(req.compulsoryBody()).map { it.toRawSignInCredentials() }
        } catch (err: Throwable) {
            null
        } ?: reject("Failed to get credentials from the body")
        resolve(service.signIn.signIn(rb).await(), HttpStatusCode.Accepted)
    }.toHttpResponse()
}