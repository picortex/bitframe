package bitframe

import bitframe.authentication.signin.SignInService
import bitframe.authentication.users.UsersService

interface BitframeService : MiniService {
    val users: UsersService
    val signIn: SignInService
}