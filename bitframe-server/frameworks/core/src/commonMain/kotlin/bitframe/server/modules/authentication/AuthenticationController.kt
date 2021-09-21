package bitframe.server.modules.authentication

import bitframe.server.http.HttpResponse

interface AuthenticationController {
    suspend fun signIn(body: String?): HttpResponse
    val service: AuthenticationService
}