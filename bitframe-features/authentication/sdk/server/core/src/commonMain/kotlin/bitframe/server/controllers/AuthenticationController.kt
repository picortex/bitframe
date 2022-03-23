package bitframe.server.controllers

import bitframe.core.RequestBody
import bitframe.core.profile.params.ChangePasswordParams
import bitframe.server.http.HttpRequest
import bitframe.server.http.HttpResponse
import bitframe.server.http.compulsoryBody
import bitframe.server.http.toHttpResponse
import bitframe.server.profile.ProfileService
import bitframe.server.signin.SignInService
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import later.await
import response.response

class AuthenticationController(
    val signInService: SignInService,
    val profileService: ProfileService
) {
    val json get() = signInService.config.json

    suspend fun signIn(req: HttpRequest): HttpResponse = response {
        val rb = signInService.parser.parseCredentialsFromString(req.compulsoryBody())
        resolve(signInService.signIn(rb).await(), HttpStatusCode.Accepted)
    }.toHttpResponse()

    suspend fun changePassword(req: HttpRequest): HttpResponse = response {
        val rb = json.decodeFromString<RequestBody.Authorized<ChangePasswordParams>>(req.compulsoryBody())
        resolve(profileService.changePassword(rb).await())
    }.toHttpResponse()
}