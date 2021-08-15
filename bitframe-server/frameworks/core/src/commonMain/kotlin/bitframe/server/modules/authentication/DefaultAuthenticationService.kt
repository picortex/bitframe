package bitframe.server.modules.authentication

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import later.await
import later.later
import users.account.CreateAccountParams
import users.server.AccountsDao
import users.server.UsersDao
import users.user.CreateUserParams

class DefaultAuthenticationService(
    override val usersDao: UsersDao,
    override val accountsDao: AccountsDao
) : AuthenticationService, CoroutineScope by CoroutineScope(SupervisorJob()) {

    override fun createDefaultUserIfNotExist(params: CreateUserParams) = later {
        val accountParams = CreateAccountParams("Genesis")
        val account = accountsDao.createIfNotExist(accountParams).await()
        usersDao.createIfNotExist(params).await()
    }
}