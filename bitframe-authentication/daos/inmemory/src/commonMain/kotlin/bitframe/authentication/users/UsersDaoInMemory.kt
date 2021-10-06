package bitframe.authentication.users

import bitframe.daos.conditions.Condition
import bitframe.daos.conditions.matching
import bitframe.daos.config.InMemoryDaoConfig
import kotlinx.coroutines.delay
import later.Later
import later.later

class UsersDaoInMemory(
    private val users: MutableMap<String, User> = mutableMapOf(),
    private val config: InMemoryDaoConfig = InMemoryDaoConfig.DEFAULT
) : UsersDao {
    private val scope = config.scope
    override fun create(params: CreateUserParams): Later<User> = scope.later {
        delay(config.simulationTime.toLong())
        val existing = users.values.find { it.name == params.name }
        if (existing == null) {
            val uid = "user-${users.size + 1}"
            val user = params.toUser(uid)
            users[uid] = user
            user
        } else existing
    }

    override fun update(u: User): Later<User> = scope.later {
        delay(config.simulationTime.toLong())
        users[u.uid] = u
        u
    }

    override fun createIfNotExist(params: CreateUserParams) = create(params)

    override fun all(where: Condition<String, Any?>?) = scope.later {
        delay(config.simulationTime.toLong())
        if (where == null) {
            users.values.toList()
        } else {
            users.values.matching(where, User.serializer())
        }
    }
}