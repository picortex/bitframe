package bitframe

import bitframe.authentication.signin.SignInService
import bitframe.authentication.users.UsersService

interface BitframeService {
    val users: UsersService
    val signIn: SignInService<*>
}