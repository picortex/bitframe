@file:JsExport

package bitframe.authentication.users

import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.spaces.RegisterSpaceParams
import later.Later
import kotlin.js.JsExport

abstract class UsersService {
    abstract fun createDefaultIfNotExist(params: CreateUserParams): Later<User>

    abstract fun register(
        user: RegisterUserParams,
        space: RegisterSpaceParams = RegisterSpaceParams(user.name)
    ): Later<LoginConundrum>
}