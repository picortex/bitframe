package users.server

import later.Later
import users.account.Account
import users.account.CreateAccountParams

class AccountsDaoInMemory(
    private val accounts: MutableMap<String, Account> = mutableMapOf()
) : AccountsDao {
    override fun createIfNotExist(params: CreateAccountParams): Later<Account> {
        TODO("Not yet implemented")
    }
}