package pimonitor

import bitframe.ApplicationConfig
import bitframe.events.EventBus
import bitframe.server.BitframeApplicationConfig
import bitframe.server.modules.Module
import bitframe.authentication.server.users.UsersService
import bitframe.authentication.server.users.UsersServiceConfig
import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.users.UsersDao
import cache.Cache
import cache.MockCache
import kotlinx.coroutines.CoroutineScope
import pimonitor.data.PiMonitorDAOProvider
import pimonitor.monitors.MonitorDao
import pimonitor.server.authentication.signup.SignUpServiceConfig
import java.io.File

interface PiMonitorApplicationConfig : ApplicationConfig<PiMonitorDAOProvider>,
    SignUpServiceConfig {
    companion object {
        operator fun invoke(
            daoProvider: PiMonitorDAOProvider,
            client: File,
            cache: Cache = MockCache(),
            bus: EventBus = BitframeApplicationConfig.DEFAULT_EVENT_BUS,
            modules: MutableList<Module> = mutableListOf(),
            scope: CoroutineScope = BitframeApplicationConfig.DEFAULT_SCOPE,
        ) = object : PiMonitorApplicationConfig {
            override val client: File = client
            override val cache: Cache = cache
            override val bus: EventBus = bus
            override val daoProvider: PiMonitorDAOProvider = daoProvider
            override val modules: MutableList<Module> = modules
            override val scope: CoroutineScope = scope
            override val dao: MonitorDao = daoProvider.monitorsDao
            private val usersServiceConfig = object : UsersServiceConfig {
                override val usersDao: UsersDao = daoProvider.users
                override val spacesDao: SpacesDao = daoProvider.spaces
                override val scope: CoroutineScope = scope
            }
            override val usersService: UsersService = UsersService(usersServiceConfig)
        }
    }
}