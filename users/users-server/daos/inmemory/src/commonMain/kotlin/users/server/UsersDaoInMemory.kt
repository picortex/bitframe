package users.server

import later.Later
import users.user.CreateUserParams
import users.user.User

class UsersDaoInMemory(
    private val users: MutableMap<String, User> = mutableMapOf()
) : UsersDao {
    override fun create(params: CreateUserParams): Later<User> {
        val existing = users.values.find { it.name == params.name }
        return if (existing == null) {
            val uid = "user-${users.size + 1}"
            val user = params.toUser(uid)
            users[uid] = user
            Later.resolve(user)
        } else Later.resolve(existing)
    }

    override fun createIfNotExist(params: CreateUserParams): Later<User> {
        return create(params)
    }

    override fun all() = Later.resolve(users.values.toList())
}