package bitframe.authentication.client.users

import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.spaces.CreateSpaceParams
import bitframe.authentication.users.CreateUserParams
import bitframe.authentication.users.RegisterUserParams
import bitframe.authentication.users.User
import bitframe.authentication.users.UsersService
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