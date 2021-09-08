package bitframe.authentication

import later.Later

interface SignInService {
    val config: ClientConfiguration
    fun loginWith(credentials: LoginCredentials): Later<LoginConundrum>
}