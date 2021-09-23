package users.server

import bitframe.server.data.Condition
import later.Later
import users.account.Account
import users.account.CreateAccountParams

interface AccountsDao {
    fun createIfNotExist(params: CreateAccountParams): Later<Account>
    fun all(where: Condition<String, Any?>? = null): Later<List<Account>>
}