package bitframe.server.modules.authentication.services

import bitframe.authentication.server.signin.SignInService
import bitframe.authentication.server.users.UsersService

interface AuthenticationService {
    val users: UsersService
    val signIn: SignInService
}