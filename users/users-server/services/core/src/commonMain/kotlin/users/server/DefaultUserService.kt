package users.server

import later.Later
import users.user.CreateUserParams
import users.user.User

class DefaultUserService : UsersService {
    override fun createDefaultUserIfNotExist(params: CreateUserParams): Later<User> {
        
    }
}