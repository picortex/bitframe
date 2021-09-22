package bitframe.server.modules.authentication

import bitframe.authentication.LoginConundrum
import duality.Result

interface AuthenticationController {
    val service: AuthenticationService
    suspend fun signIn(body: String?): Result<LoginConundrum>
}