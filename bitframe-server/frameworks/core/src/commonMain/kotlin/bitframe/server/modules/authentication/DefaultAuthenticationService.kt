package bitframe.server.modules.authentication

import bitframe.authentication.LoginConundrum
import bitframe.authentication.LoginCredentials
import bitframe.server.data.DAOProvider
import bitframe.server.http.HttpResponse
import io.ktor.http.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import later.Later
import later.await
import later.later
import users.account.Account
import users.account.CreateAccountParams
import users.server.AccountsDao
import users.server.UsersDao
import users.user.CreateUserParams
import users.user.User

class DefaultAuthenticationService(
    override val usersDao: UsersDao,
    override val accountsDao: AccountsDao
) : AuthenticationService, CoroutineScope by CoroutineScope(SupervisorJob()) {

    constructor(provider: DAOProvider) : this(provider.users, provider.accounts)

    override fun createDefaultUserIfNotExist(params: CreateUserParams) = later {
        val accountParams = CreateAccountParams("Genesis")
        val account = accountsDao.createIfNotExist(accountParams)
        val user = usersDao.createIfNotExist(params)
        account.await(); user.await()
    }

    override fun signIn(credentials: LoginCredentials): Later<LoginConundrum> = later {
        LoginConundrum(
            user = bitframe.authentication.User("test"),
            accounts = listOf(
                bitframe.authentication.Account("uid")
            )
        )
    }

    override fun users(): Later<List<User>> {
        return usersDao.all()
    }

    override fun accounts(): Later<List<Account>> {
        return accountsDao.all()
    }
}