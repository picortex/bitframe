package bitframe.authentication.client.users

import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.spaces.CreateSpaceParams
import bitframe.authentication.users.CreateUserParams
import bitframe.actors.users.RegisterUserParams
import bitframe.actors.users.User
import bitframe.actors.users.UsersService
import bitframe.service.client.config.ServiceConfig
import later.Later

class UsersServiceMock(
    private val config: ServiceConfig
) : UsersService() {
    override fun createIfNotExist(params: CreateUserParams): Later<User> {
        TODO("Not yet implemented")
    }

    override fun registerWithSpace(user: RegisterUserParams, space: CreateSpaceParams): Later<LoginConundrum> {
        TODO("Not yet implemented")
    }
}