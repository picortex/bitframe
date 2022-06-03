package authenticator

import bitframe.core.RequestBody
import bitframe.core.signin.SignInResult
import bitframe.core.users.RegisterUserParams
import later.Later

interface UsersServiceCore {
    fun register(rb: RequestBody.UnAuthorized<RegisterUserParams>): Later<SignInResult>
}