package users.server

import later.Later
import users.user.CreateUserParams
import users.user.User

class UsersDaoInMemory(val users: MutableMap<String, User>) : UsersDao {
    override fun create(params: CreateUserParams): Later<User> {
        TODO()
    }
}