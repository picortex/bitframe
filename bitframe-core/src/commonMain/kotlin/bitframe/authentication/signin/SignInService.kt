package bitframe.authentication.signin

import bitframe.authentication.LoginConundrum
import bitframe.authentication.LoginCredentials
import later.Later

interface SignInService {
    fun signIn(credentials: LoginCredentials): Later<LoginConundrum>
}