package bitframe.authentication.spaces

import bitframe.daos.conditions.Condition
import bitframe.daos.conditions.matching
import bitframe.daos.config.InMemoryDaoConfig
import kotlinx.coroutines.delay
import later.Later
import later.later

class SpacesDaoInMemory(
    private val spaces: MutableMap<String, Space> = mutableMapOf(),
    private val config: InMemoryDaoConfig = InMemoryDaoConfig.DEFAULT
) : SpacesDao {
    private val scope = config.scope
    override fun createIfNotExist(params: CreateSpaceParams): Later<Space> = scope.later {
        delay(config.simulationTime)
        val existing = spaces.values.find { it.name.contentEquals(params.name) }
        if (existing == null) {
            val uid = "space-${spaces.size + 1}"
            val space = params.toSpace(uid)
            spaces[uid] = space
            space
        } else existing
    }

    override fun all(where: Condition<String, Any?>?): Later<List<Space>> = scope.later {
        delay(config.simulationTime)
        if (where == null) {
            spaces.values.toList()
        } else {
            spaces.values.matching(where, Space.serializer())
        }
    }
}