package integration.authentication

import bitframe.events.EventBus
import bitframe.events.InMemoryEventBus
import bitframe.server.InMemoryDaoProvider
import bitframe.server.data.DAOProvider
import bitframe.server.modules.authentication.AuthenticationControllerImpl
import bitframe.server.modules.authentication.AuthenticationServiceImpl
import bitframe.service.config.ServiceConfig
import cache.Cache
import cache.MockCache

private val inMemoryDaoProvider: DAOProvider = InMemoryDaoProvider()

internal val DAO_PROVIDER_UNDER_TEST = inMemoryDaoProvider

internal val SERVICE_CONFIG = ServiceConfig(
    "server-app"
)

internal val BUS: EventBus = InMemoryEventBus()

internal val CACHE: Cache = MockCache()

internal val AUTHENTICATION_SERVICE_UNDER_TEST = AuthenticationServiceImpl(DAO_PROVIDER_UNDER_TEST, SERVICE_CONFIG, CACHE, BUS)

internal val AUTHENTICATION_CONTROLLER_UNDER_TEST = AuthenticationControllerImpl(AUTHENTICATION_SERVICE_UNDER_TEST)