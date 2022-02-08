@file:JsExport

package bitframe.actors.users

import bitframe.actors.users.usecases.RegisterUserUseCase
import bitframe.authentication.users.CreateUserParams
import later.Later
import kotlin.js.JsExport

abstract class UsersService : RegisterUserUseCase {
    abstract fun createIfNotExist(params: CreateUserParams): Later<User>
}