package users.server

import later.Later
import users.user.CreateUserParams
import users.user.User

interface UsersService {
    fun createDefaultUserIfNotExist(params: CreateUserParams): Later<User>
}