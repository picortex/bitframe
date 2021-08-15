package users.server

import later.Later
import users.account.Account
import users.account.CreateAccountParams

interface AccountsDao {
    fun createIfNotExist(params: CreateAccountParams): Later<Account>
}