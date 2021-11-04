package pimonitor

import bitframe.authentication.client.signin.SignInServiceMockConfig
import bitframe.authentication.users.User
import bitframe.authentication.users.UsersDao
import bitframe.daos.config.InMemoryDaoConfig
import bitframe.events.EventBus
import bitframe.events.InMemoryEventBus
import bitframe.service.client.config.ServiceConfig
import cache.Cache
import kotlinx.coroutines.CoroutineScope

class PiMonitorServiceStubConfig(
    override val appId: String,
    val simulationTime: Long = 0L,
    override val cache: Cache,
    override val bus: EventBus = InMemoryEventBus(),
    override val scope: CoroutineScope = ServiceConfig.DEFAULT_SCOPE,
) : ServiceConfig {

    fun with(usersDao: UsersDao): SignInServiceMockConfig = object : SignInServiceMockConfig, ServiceConfig by this {
        override val users: MutableList<User> = mutableListOf()
        override val bus: EventBus = this@PiMonitorServiceStubConfig.bus
        override val cache: Cache = this@PiMonitorServiceStubConfig.cache
    }

    fun toInMemoryDaoConfig() = InMemoryDaoConfig(simulationTime, scope)
}