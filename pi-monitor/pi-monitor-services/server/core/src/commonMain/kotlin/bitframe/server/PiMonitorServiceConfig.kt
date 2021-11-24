package bitframe.server

import bitframe.authentication.server.signin.SignInServiceConfig
import bitframe.authentication.server.spaces.SpacesServiceConfig
import bitframe.authentication.server.users.UsersService
import bitframe.authentication.server.users.UsersServiceConfig
import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.users.UsersDao
import bitframe.events.EventBus
import bitframe.service.config.ServiceConfig
import kotlinx.coroutines.CoroutineScope
import bitframe.monitored.MonitoredBusinessesDao
import bitframe.monitors.MonitorDao
import bitframe.server.authentication.signup.SignUpServiceConfig
import bitframe.server.businesses.BusinessesServiceConfig
import bitframe.server.monitors.MonitorsServiceConfig
import kotlin.jvm.JvmField

interface PiMonitorServiceConfig : ServiceConfig,
    SpacesServiceConfig,
    UsersServiceConfig,
    SignInServiceConfig,
    MonitorsServiceConfig,
    BusinessesServiceConfig {

    fun with(users: UsersService): SignUpServiceConfig = object : SignUpServiceConfig, MonitorsServiceConfig by this {
        override val usersService: UsersService = users
    }

    companion object {
        @JvmField
        val DEFAULT_BUS = ServiceConfig.DEFAULT_BUS

        @JvmField
        val DEFAULT_SCOPE = ServiceConfig.DEFAULT_SCOPE

        operator fun invoke(
            bus: EventBus = DEFAULT_BUS,
            spacesDao: SpacesDao,
            usersDao: UsersDao,
            monitorsDao: MonitorDao,
            businessesDao: MonitoredBusinessesDao,
            scope: CoroutineScope = DEFAULT_SCOPE,
        ) = object : PiMonitorServiceConfig {
            override val bus = bus
            override val scope = scope
            override val spacesDao = spacesDao
            override val usersDao = usersDao
            override val monitorsDao = monitorsDao
            override val businessesDao = businessesDao
        }

        operator fun invoke(
            bus: EventBus = DEFAULT_BUS,
            provider: PiMonitorDaoProvider,
            scope: CoroutineScope = DEFAULT_SCOPE
        ) = invoke(
            bus = bus,
            spacesDao = provider.spaces,
            usersDao = provider.users,
            monitorsDao = provider.monitors,
            businessesDao = provider.businesses,
            scope = scope,
        )
    }
}