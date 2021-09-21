package bitframe.server.modules.authentication

import bitframe.authentication.LoginConundrum
import bitframe.authentication.LoginCredentials
import bitframe.server.http.HttpResponse
import later.Later
import users.account.Account
import users.server.AccountsDao
import users.server.UsersDao
import users.user.CreateUserParams
import users.user.User

interface AuthenticationService {
    val usersDao: UsersDao
    val accountsDao: AccountsDao
    fun createDefaultUserIfNotExist(params: CreateUserParams): Later<User>
    fun users(): Later<List<User>>
    fun accounts(): Later<List<Account>>
    fun signIn(credentials: LoginCredentials): Later<LoginConundrum>
}