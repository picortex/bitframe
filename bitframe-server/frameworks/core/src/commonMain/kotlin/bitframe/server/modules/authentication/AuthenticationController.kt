package bitframe.server.modules.authentication

import bitframe.server.http.HttpResponse

interface AuthenticationController {
    val service: AuthenticationService
    suspend fun signIn(body: String?): HttpResponse
}