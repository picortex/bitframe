package bitframe.server

import bitframe.server.data.DAOProvider
import users.server.AccountsDao
import users.server.AccountsDaoInMemory
import users.server.UsersDao
import users.server.UsersDaoInMemory

class InMemoryDaoProvider(
    override val users: UsersDao = UsersDaoInMemory(),
    override val accounts: AccountsDao = AccountsDaoInMemory()
) : DAOProvider