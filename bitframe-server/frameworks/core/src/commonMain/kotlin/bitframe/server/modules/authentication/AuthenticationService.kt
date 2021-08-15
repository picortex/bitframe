package bitframe.server.modules.authentication

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
}