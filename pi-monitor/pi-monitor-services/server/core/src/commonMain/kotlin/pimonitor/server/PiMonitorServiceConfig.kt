package pimonitor.server

import bitframe.authentication.server.users.UsersService
import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.users.UsersDao
import events.EventBus
import bitframe.service.config.ServiceConfig
import kotlinx.coroutines.CoroutineScope
import logging.Logger
import pimonitor.monitored.MonitoredBusinessesDao
import pimonitor.monitors.MonitorDao
import pimonitor.server.authentication.signup.SignUpServiceConfig
import pimonitor.server.businesses.BusinessesServiceConfig
import pimonitor.server.monitors.MonitorsServiceConfig
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

        @JvmField
        val DEFAULT_LOGGER = ServiceConfig.DEFAULT_LOGGER

        operator fun invoke(
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            spacesDao: SpacesDao,
            usersDao: UsersDao,
            monitorsDao: MonitorDao,
            businessesDao: MonitoredBusinessesDao,
            scope: CoroutineScope = DEFAULT_SCOPE,
        ) = object : PiMonitorServiceConfig {
            override val bus = bus
            override val logger: Logger = logger
            override val scope = scope
            override val spacesDao = spacesDao
            override val usersDao = usersDao
            override val monitorsDao = monitorsDao
            override val businessesDao = businessesDao
        }

        operator fun invoke(
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            provider: PiMonitorDaoProvider,
            scope: CoroutineScope = DEFAULT_SCOPE
        ) = invoke(
            bus = bus,
            logger = logger,
            spacesDao = provider.spaces,
            usersDao = provider.users,
            monitorsDao = provider.monitors,
            businessesDao = provider.businesses,
            scope = scope,
        )
    }
}