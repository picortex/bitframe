package bitframe.authentication.client.users

import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.spaces.CreateSpaceParams
import bitframe.authentication.users.CreateUserParams
import bitframe.authentication.users.RegisterUserParams
import bitframe.authentication.users.User
import bitframe.authentication.users.UsersService
import bitframe.service.client.config.KtorClientConfiguration
import later.Later

class UsersServiceKtor(
    private val configuration: KtorClientConfiguration
) : UsersService() {
    override fun createIfNotExist(params: CreateUserParams): Later<User> {
        TODO("Not yet implemented")
    }

    override fun registerWithSpace(user: RegisterUserParams, space: CreateSpaceParams): Later<LoginConundrum> {
        TODO("Not yet implemented")
    }
}