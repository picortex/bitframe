package bitframe.server.modules.authentication

import bitframe.authentication.signin.SignInService
import bitframe.authentication.users.UsersService

interface AuthenticationService {
    val users: UsersService
    val signIn: SignInService
}