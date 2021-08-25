package bitframe.server

import bitframe.server.data.DAOProvider
import users.server.AccountsDao
import users.server.AccountsDaoInMemory
import users.server.UsersDao
import users.server.UsersDaoInMemory
import kotlin.jvm.JvmOverloads

class InMemoryDaoProvider @JvmOverloads constructor(
    override val users: UsersDao = UsersDaoInMemory(),
    override val accounts: AccountsDao = AccountsDaoInMemory()
) : DAOProvider