package bitframe.core.actors.users.usecases

import bitframe.core.actors.users.RegisterUserParams
import bitframe.authentication.signin.SignInResult
import later.Later

interface RegisterUserUseCase {
    fun register(params: RegisterUserParams): Later<SignInResult>
}