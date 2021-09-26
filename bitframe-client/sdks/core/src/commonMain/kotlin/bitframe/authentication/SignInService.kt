package bitframe.authentication

import bitframe.MiniService
import later.Later

interface SignInService : MiniService {
    fun signIn(credentials: LoginCredentials): Later<LoginConundrum>
}