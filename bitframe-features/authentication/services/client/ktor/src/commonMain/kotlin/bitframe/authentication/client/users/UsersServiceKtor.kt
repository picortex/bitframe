package bitframe.authentication.client.users

import bitframe.authentication.signin.SignInResult
import bitframe.actors.users.RegisterUserParams
import bitframe.actors.users.User
import bitframe.actors.users.UsersService
import bitframe.service.client.config.KtorClientConfiguration
import later.Later

class UsersServiceKtor(
    private val config: KtorClientConfiguration
) : UsersService() {
    override fun register(params: RegisterUserParams): Later<SignInResult> {
        TODO("Not yet implemented")
    }
}