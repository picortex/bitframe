package bitframe.authentication.users.usecases

import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.spaces.CreateSpaceParams
import bitframe.authentication.users.RegisterUserParams
import bitframe.authentication.users.toCreateSpaceParams
import later.Later

interface RegisterUser {
    fun register(params: RegisterUserParams) = registerWithSpace(params, params.toCreateSpaceParams())

    // Duplicated method names because of a kotlin js compiler bug on defaults
    fun registerWithSpace(user: RegisterUserParams, space: CreateSpaceParams): Later<LoginConundrum>
}