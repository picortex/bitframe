package bitframe.authentication.spaces

import bitframe.daos.conditions.Condition
import later.Later

interface SpacesDao {
    fun createIfNotExist(params: CreateSpaceParams): Later<Space>
    fun all(where: Condition<String, Any?>? = null): Later<List<Space>>
}