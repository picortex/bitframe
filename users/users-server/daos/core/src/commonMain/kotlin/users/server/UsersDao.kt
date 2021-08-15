package users.server

import later.Later
import users.user.CreateUserParams
import users.user.User

interface UsersDao {
    fun create(params: CreateUserParams): Later<User>
    fun createIfNotExist(params: CreateUserParams): Later<User>
}