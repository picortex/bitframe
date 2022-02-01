package bitframe.authentication

import bitframe.authentication.spaces.Space
import bitframe.authentication.spaces.SpacesDaoInMemoryConfig
import bitframe.authentication.users.User
import bitframe.authentication.users.UsersDaoInMemoryConfig
import bitframe.daos.config.DaoConfig
import bitframe.daos.config.InMemoryDaoConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface InMemoryAuthenticationDaoProviderConfig : UsersDaoInMemoryConfig, SpacesDaoInMemoryConfig {
    companion object {
        val DEFAULT_SCOPE = InMemoryDaoConfig.DEFAULT_SCOPE
        val DEFAULT_SIMULATION_TIME = InMemoryDaoConfig.DEFAULT_SIMULATION_TIME
        val DEFAULT_SPACES = mutableMapOf<String, Space>()
        val DEFAULT_USERS = mutableMapOf<String, User>()

        @JvmSynthetic
        operator fun invoke(
            simulationTime: Long = DEFAULT_SIMULATION_TIME,
            users: MutableMap<String, User> = DEFAULT_USERS,
            spaces: MutableMap<String, Space> = DEFAULT_SPACES,
            lock: Mutex = InMemoryDaoConfig.DEFAULT_LOCK,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): InMemoryAuthenticationDaoProviderConfig = object : InMemoryAuthenticationDaoProviderConfig,
            InMemoryDaoConfig by InMemoryDaoConfig(simulationTime, lock, scope) {
            override val users: MutableMap<String, User> = users
            override val spaces: MutableMap<String, Space> = spaces
        }

        @JvmOverloads
        @JvmStatic
        fun create(
            simulationTime: Long = DEFAULT_SIMULATION_TIME,
            users: MutableMap<String, User> = DEFAULT_USERS,
            spaces: MutableMap<String, Space> = DEFAULT_SPACES,
            lock: Mutex = InMemoryDaoConfig.DEFAULT_LOCK,
            scope: CoroutineScope = DEFAULT_SCOPE
        ) = invoke(simulationTime, users, spaces, lock, scope)
    }
}