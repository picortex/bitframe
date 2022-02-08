package bitframe.actors.users.usecases

import bitframe.actors.users.RegisterUserParams
import bitframe.authentication.signin.SignInResult
import bitframe.authentication.spaces.CreateSpaceParams
import later.Later

interface RegisterUser {
    fun register(params: RegisterUserParams): Later<SignInResult>
}