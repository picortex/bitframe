package bitframe.authentication.client.users

import bitframe.core.actors.users.RegisterUserParams
import bitframe.core.actors.users.UsersService
import bitframe.authentication.signin.SignInResult
import bitframe.client.KtorServiceConfig
import later.Later

class UsersServiceKtor(
    private val config: KtorServiceConfig
) : UsersService() {
    override fun register(params: RegisterUserParams): Later<SignInResult> {
        TODO("Not yet implemented")
    }
}