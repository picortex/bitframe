package bitframe.authentication.spaces

import bitframe.authentication.configs.DaoConfig
import bitframe.authentication.utils.matching
import bitframe.server.data.Condition
import later.Later

class SpacesDaoInMemory(
    private val spaces: MutableMap<String, Space> = mutableMapOf(),
    private val config: DaoConfig = DaoConfig.DEFAULT
) : SpacesDao {
    override fun createIfNotExist(params: CreateSpaceParams): Later<Space> {
        val existing = spaces.values.find { it.name.contentEquals(params.name) }
        return if (existing == null) {
            val uid = "space-${spaces.size + 1}"
            val account = params.toSpace(uid)
            spaces[uid] = account
            Later.resolve(account)
        } else Later.resolve(existing)
    }

    override fun all(where: Condition<String, Any?>?): Later<List<Space>> = if (where == null) Later.resolve(spaces.values.toList()) else {
        Later.resolve(spaces.values.matching(where, Space.serializer()))
    }
}