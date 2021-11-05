package bitframe.authentication.users

import bitframe.daos.config.InMemoryDaoConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex

interface UsersDaoInMemoryConfig : InMemoryDaoConfig {
    val users: MutableMap<String, User>

    companion object {
        operator fun invoke(
            users: MutableMap<String, User> = mutableMapOf(),
            simulationTime: Long = InMemoryDaoConfig.DEFAULT_SIMULATION_TIME,
            lock: Mutex = InMemoryDaoConfig.DEFAULT_LOCK,
            scope: CoroutineScope = InMemoryDaoConfig.DEFAULT_SCOPE,
        ): UsersDaoInMemoryConfig = object : UsersDaoInMemoryConfig, InMemoryDaoConfig by InMemoryDaoConfig(simulationTime, lock, scope) {
            override val users: MutableMap<String, User> = users
        }
    }
}