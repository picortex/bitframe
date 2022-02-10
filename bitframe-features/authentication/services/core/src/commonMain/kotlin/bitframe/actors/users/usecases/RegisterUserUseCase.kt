package bitframe.actors.users.usecases

import bitframe.actors.users.RegisterUserParams
import bitframe.authentication.signin.SignInResult
import later.Later

interface RegisterUserUseCase {
    fun register(params: RegisterUserParams): Later<SignInResult>
}