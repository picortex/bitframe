@file:JsExport

package bitframe.actors.users

import bitframe.actors.users.usecases.RegisterUser
import bitframe.authentication.users.CreateUserParams
import later.Later
import kotlin.js.JsExport

abstract class UsersService : RegisterUser {
    abstract fun createIfNotExist(params: CreateUserParams): Later<User>
}