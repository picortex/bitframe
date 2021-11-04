package bitframe.authentication

import bitframe.authentication.spaces.Space
import bitframe.authentication.spaces.SpacesDaoInMemoryConfig
import bitframe.authentication.users.User
import bitframe.authentication.users.UsersDaoInMemoryConfig
import bitframe.daos.config.DaoConfig
import kotlinx.coroutines.CoroutineScope
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface InMemoryAuthenticationDaoProviderConfig : UsersDaoInMemoryConfig, SpacesDaoInMemoryConfig {
    companion object {
        val DEFAULT_SCOPE = DaoConfig.DEFAULT_SCOPE
        const val DEFAULT_SIMULATION_TIME = 0L
        val DEFAULT_SPACES = mutableMapOf<String, Space>()
        val DEFAULT_USERS = mutableMapOf<String, User>()

        @JvmSynthetic
        operator fun invoke(
            simulationTime: Long = DEFAULT_SIMULATION_TIME,
            users: MutableMap<String, User> = DEFAULT_USERS,
            spaces: MutableMap<String, Space> = DEFAULT_SPACES,
            scope: CoroutineScope = DEFAULT_SCOPE
        ) = object : InMemoryAuthenticationDaoProviderConfig {
            override val users: MutableMap<String, User> = users
            override val simulationTime: Long = simulationTime
            override val scope: CoroutineScope = scope
            override val spaces: MutableMap<String, Space> = spaces
        }

        @JvmOverloads
        @JvmStatic
        fun create(
            simulationTime: Long = DEFAULT_SIMULATION_TIME,
            users: MutableMap<String, User> = DEFAULT_USERS,
            spaces: MutableMap<String, Space> = DEFAULT_SPACES,
            scope: CoroutineScope = DEFAULT_SCOPE
        ) = invoke(simulationTime, users, spaces, scope)
    }
}