@file:JsExport

package bitframe.authentication.users

import bitframe.authentication.users.usecases.RegisterUser
import later.Later
import kotlin.js.JsExport

abstract class UsersService : RegisterUser {
    abstract fun createIfNotExist(params: CreateUserParams): Later<User>
}