package bitframe.authentication.users

import bitframe.daos.conditions.Condition
import later.Later

interface UsersDao {
    fun create(params: CreateUserParams): Later<User>
    fun update(u: User): Later<User>
    fun createIfNotExist(params: CreateUserParams): Later<User>
    fun all(where: Condition<String, Any?>? = null): Later<List<User>>
}