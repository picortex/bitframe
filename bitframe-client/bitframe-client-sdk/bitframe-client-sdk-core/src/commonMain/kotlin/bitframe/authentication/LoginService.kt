package bitframe.authentication

import later.Later

interface LoginService {
    fun loginWith(credentials: Credentials): Later<LoginConundrum>
}