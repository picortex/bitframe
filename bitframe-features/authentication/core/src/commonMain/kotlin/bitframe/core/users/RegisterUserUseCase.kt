package bitframe.core.users

import bitframe.core.signin.SignInResult
import later.Later

interface RegisterUserUseCase {
    fun register(params: RegisterUserParams): Later<SignInResult>
}