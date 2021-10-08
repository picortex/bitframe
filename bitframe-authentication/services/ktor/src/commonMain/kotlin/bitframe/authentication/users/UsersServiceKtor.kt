package bitframe.authentication.users

import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.spaces.RegisterSpaceParams
import bitframe.service.config.KtorClientConfiguration
import later.Later

class UsersServiceKtor(
    private val configuration: KtorClientConfiguration
) : UsersService() {
    override fun createIfNotExist(params: CreateUserParams): Later<User> {
        TODO("Not yet implemented")
    }

    override fun registerWithSpace(user: RegisterUserParams, space: RegisterSpaceParams): Later<LoginConundrum> {
        TODO("Not yet implemented")
    }
}