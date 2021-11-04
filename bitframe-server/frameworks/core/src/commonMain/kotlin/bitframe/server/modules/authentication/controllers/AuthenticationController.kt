package bitframe.server.modules.authentication.controllers

import bitframe.server.http.HttpResponse
import bitframe.server.modules.authentication.services.AuthenticationService

interface AuthenticationController {
    val service: AuthenticationService
    suspend fun signIn(body: String?): HttpResponse
}