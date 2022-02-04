package bitframe.authentication.users

import bitframe.daos.conditions.Condition
import bitframe.daos.conditions.matching
import kotlinx.coroutines.delay
import later.Later
import later.later

class UsersDaoInMemory(
    private val config: UsersDaoInMemoryConfig = UsersDaoInMemoryConfig()
) : UsersDao {
    private val scope = config.scope
    private val users = config.users
    private val lock = config.lock
    override fun create(params: CreateUserParams): Later<User> = scope.later {
        delay(config.simulationTime)
        val existing = users.values.find { it.name == params.name }
        if (existing == null) {
            lock.lock()
            val uid = "user-${users.size + 1}"
            val user = params.toUser(uid)
            users[uid] = user
            lock.unlock()
            user
        } else existing
    }

    override fun update(u: User): Later<User> = scope.later {
        delay(config.simulationTime)
        users[u.uid] = u
        u
    }

    override fun createIfNotExist(params: CreateUserParams) = create(params)

    override fun all(where: Condition<String, Any?>?) = scope.later {
        delay(config.simulationTime)
        if (where == null) {
            users.values.toList()
        } else {
            users.values.matching(where, User.serializer())
        }
    }
}