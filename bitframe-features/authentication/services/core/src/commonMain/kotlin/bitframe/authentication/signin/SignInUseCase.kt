package bitframe.authentication.signin

import bitframe.service.requests.RequestBody
import later.Later

interface SignInUseCase {
    fun signIn(rb: RequestBody.UnAuthorized<SignInCredentials>): Later<SignInResult>
}