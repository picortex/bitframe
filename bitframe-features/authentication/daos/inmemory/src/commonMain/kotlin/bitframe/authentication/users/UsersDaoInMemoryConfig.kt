package bitframe.authentication.users

import bitframe.daos.config.MockDaoConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex

interface UsersDaoInMemoryConfig : MockDaoConfig {
    val users: MutableMap<String, User>

    companion object {
        operator fun invoke(
            users: MutableMap<String, User> = mutableMapOf(),
            simulationTime: Long = MockDaoConfig.DEFAULT_SIMULATION_TIME,
            lock: Mutex = MockDaoConfig.DEFAULT_LOCK,
            scope: CoroutineScope = MockDaoConfig.DEFAULT_SCOPE,
        ): UsersDaoInMemoryConfig = object : UsersDaoInMemoryConfig, MockDaoConfig by MockDaoConfig(simulationTime, lock, scope) {
            override val users: MutableMap<String, User> = users
        }
    }
}