package bitframe.authentication.users

import bitframe.server.data.Condition
import later.Later

interface UsersDao {
    fun create(params: CreateUserParams): Later<User>
    fun createIfNotExist(params: CreateUserParams): Later<User>
    fun all(where: Condition<String, Any?>? = null): Later<List<User>>
}