package bitframe.authentication.users

import bitframe.daos.config.InMemoryDaoConfig
import kotlinx.coroutines.CoroutineScope

interface UsersDaoInMemoryConfig : InMemoryDaoConfig {
    val users: MutableMap<String, User>

    companion object {
        operator fun invoke(
            users: MutableMap<String, User> = mutableMapOf(),
            simulationTime: Long = InMemoryDaoConfig.DEFAULT_SIMULTATION_TIME,
            scope: CoroutineScope = InMemoryDaoConfig.DEFAULT_SCOPE,
        ) = object : UsersDaoInMemoryConfig {
            override val users: MutableMap<String, User> = users
            override val simulationTime: Long = simulationTime
            override val scope: CoroutineScope = scope
        }
    }
}