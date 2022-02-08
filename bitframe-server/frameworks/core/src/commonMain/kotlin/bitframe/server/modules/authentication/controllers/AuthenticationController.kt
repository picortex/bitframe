package bitframe.server.modules.authentication.controllers

import bitframe.server.BitframeService
import bitframe.server.http.HttpRequest
import bitframe.server.http.HttpResponse

interface AuthenticationController {
    val service: BitframeService
    suspend fun signIn(req: HttpRequest): HttpResponse
}