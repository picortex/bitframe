package bitframe.server.controllers

import bitframe.core.RequestBody.UnAuthorized
import bitframe.core.map
import bitframe.core.signin.SignInCredentials
import bitframe.server.http.HttpRequest
import bitframe.server.http.HttpResponse
import bitframe.server.http.compulsoryBody
import bitframe.server.http.toHttpResponse
import bitframe.server.signin.SignInService
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import later.await
import response.response

class AuthenticationController(
    val service: SignInService
) {
    suspend fun signIn(req: HttpRequest): HttpResponse = response {
        val rb = try {
            Json.decodeFromString<UnAuthorized<SignInCredentials>>(req.compulsoryBody())
        } catch (err: Throwable) {
            null
        } ?: try {
            Json.decodeFromString<UnAuthorized<RawEmailPasswordSignInCredential>>(req.compulsoryBody()).map { it.toRawSignInCredentials() }
        } catch (err: Throwable) {
            null
        } ?: try {
            Json.decodeFromString<UnAuthorized<RawPhonePasswordSignInCredential>>(req.compulsoryBody()).map { it.toRawSignInCredentials() }
        } catch (err: Throwable) {
            null
        } ?: reject("Failed to get credentials from the body")
        resolve(service.signIn(rb).await(), HttpStatusCode.Accepted)
    }.toHttpResponse()
}