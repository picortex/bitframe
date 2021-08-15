package bitframe.authentication

import later.Later

interface LoginService {
    val config: ClientConfiguration
    fun loginWith(credentials: LoginCredentials): Later<LoginConundrum>
}