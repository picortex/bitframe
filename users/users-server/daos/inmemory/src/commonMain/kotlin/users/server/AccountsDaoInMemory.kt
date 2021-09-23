package users.server

import bitframe.server.data.Condition
import kotlinx.serialization.json.Json
import kotlinx.serialization.mapper.Mapper
import kotlinx.serialization.mapper.WrappedMap
import later.Later
import users.account.Account
import users.account.CreateAccountParams

class AccountsDaoInMemory(
    private val accounts: MutableMap<String, Account> = mutableMapOf()
) : AccountsDao {
    override fun createIfNotExist(params: CreateAccountParams): Later<Account> {
        val existing = accounts.values.find { it.name.contentEquals(params.name) }
        return if (existing == null) {
            val uid = "account-${accounts.size + 1}"
            val account = params.toAccount(uid)
            accounts[uid] = account
            Later.resolve(account)
        } else Later.resolve(existing)
    }

    override fun all(where: Condition<String, Any?>?): Later<List<Account>> = if (where == null) Later.resolve(accounts.values.toList()) else {
        Later.resolve(accounts.values.matching(where, Account.serializer()))
    }
}