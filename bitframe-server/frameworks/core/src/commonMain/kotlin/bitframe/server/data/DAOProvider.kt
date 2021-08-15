package bitframe.server.data

import users.server.AccountsDao
import users.server.UsersDao

interface DAOProvider {
    val users: UsersDao
    val accounts: AccountsDao
}