package bitframe.authentication

import bitframe.MiniService
import later.Later

interface SignInService : MiniService {
    fun loginWith(credentials: LoginCredentials): Later<LoginConundrum>
}