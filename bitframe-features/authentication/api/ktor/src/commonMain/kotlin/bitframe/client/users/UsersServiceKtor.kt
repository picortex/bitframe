package bitframe.client.users

import bitframe.client.KtorServiceConfig
import bitframe.core.signin.SignInResult
import bitframe.core.users.RegisterUserParams
import bitframe.core.users.UsersService
import later.Later

class UsersServiceKtor(
    private val config: KtorServiceConfig
) : UsersService() {
    override fun register(params: RegisterUserParams): Later<SignInResult> {
        TODO("Not yet implemented")
    }
}