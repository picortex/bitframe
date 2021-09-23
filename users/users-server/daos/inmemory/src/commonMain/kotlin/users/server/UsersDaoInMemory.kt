package users.server

import bitframe.server.data.Condition
import kotlinx.serialization.json.Json
import kotlinx.serialization.mapper.Mapper
import later.Later
import users.account.Account
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

    override fun all(where: Condition<String, Any?>?) = if (where == null) Later.resolve(users.values.toList()) else {
        val matches = users.filterValues {
            where.apply(Mapper.decodeFromString(Json.encodeToString(User.serializer(), it)))
        }.values
        Later.resolve(matches.toList())
    }
}