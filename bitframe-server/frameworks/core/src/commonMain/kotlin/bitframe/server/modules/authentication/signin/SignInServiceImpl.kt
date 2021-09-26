package bitframe.server.modules.authentication.signin

import bitframe.authentication.LoginConundrum
import bitframe.authentication.LoginCredentials
import bitframe.authentication.signin.SignInService
import bitframe.server.data.contains
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import later.Later
import later.await
import later.later
import users.server.AccountsDao
import users.server.UsersDao

internal class SignInServiceImpl(
    val usersDao: UsersDao,
    val accountsDao: AccountsDao
) : SignInService, CoroutineScope by CoroutineScope(SupervisorJob()) {
    override fun signIn(credentials: LoginCredentials): Later<LoginConundrum> = later {
        val matches = usersDao.all(where = "contacts" contains credentials.alias).await()
        if (matches.isEmpty()) throw RuntimeException("User with alias=${credentials.alias}, not found")
        val match = matches.first()
        LoginConundrum(
            user = bitframe.authentication.User(match.tag),
            accounts = listOf(
                bitframe.authentication.Account("uid")
            )
        )
    }
}