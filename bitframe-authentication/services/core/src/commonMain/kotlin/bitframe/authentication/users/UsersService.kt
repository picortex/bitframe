@file:JsExport

package bitframe.authentication.users

import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.spaces.RegisterSpaceParams
import later.Later
import kotlin.js.JsExport

abstract class UsersService {
    abstract fun createIfNotExist(params: CreateUserParams): Later<User>

    fun register(params: RegisterUserParams) = registerWithSpace(params, params.toRegisterSpaceParams())

    // Duplicated method names because of a kotlin js compiler bug on defaults
    abstract fun registerWithSpace(user: RegisterUserParams, space: RegisterSpaceParams): Later<LoginConundrum>
}