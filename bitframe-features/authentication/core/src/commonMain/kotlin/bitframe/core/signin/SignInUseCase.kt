package bitframe.core.signin

import bitframe.core.RequestBody
import later.Later

interface SignInUseCase {
    fun signIn(rb: RequestBody.UnAuthorized<SignInCredentials>): Later<SignInResult>
}