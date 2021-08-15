package users.server

import later.Later
import users.user.CreateUserParams
import users.user.User

class UsersDaoInMemory(
    val users: MutableMap<String, User> = mutableMapOf()
) : UsersDao {
    override fun create(params: CreateUserParams): Later<User> {
        val existing = users.values.find { it.name }
        return if (existing == null) {
            val uid = "account-${accounts.size + 1}"
            val account = params.toAccount(uid)
            accounts[uid] = account
            Later.resolve(account)
        } else Later.resolve(existing)
    }

    override fun createIfNotExist(params: CreateUserParams): Later<User> {
        TODO("Not yet implemented")
    }
}