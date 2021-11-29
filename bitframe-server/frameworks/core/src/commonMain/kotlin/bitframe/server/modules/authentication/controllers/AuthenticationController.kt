package bitframe.server.modules.authentication.controllers

import bitframe.server.BitframeService
import bitframe.server.http.HttpResponse

interface AuthenticationController {
    val service: BitframeService
    suspend fun signIn(body: String?): HttpResponse
}