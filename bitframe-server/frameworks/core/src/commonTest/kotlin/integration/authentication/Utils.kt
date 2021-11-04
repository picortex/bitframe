package integration.authentication

import bitframe.events.EventBus
import bitframe.events.InMemoryEventBus
import bitframe.server.BitframeApplicationConfig
import bitframe.server.BitframeDaoProvider
import bitframe.server.data.DAOProvider
import bitframe.server.modules.authentication.controllers.AuthenticationControllerImpl
import bitframe.server.modules.authentication.services.AuthenticationServiceImpl
import bitframe.service.config.ServiceConfig
import cache.Cache
import cache.MockCache
import integration.TestBitframeDaoProvider

private val inMemoryDaoProvider: DAOProvider = TestBitframeDaoProvider()

internal val DAO_PROVIDER_UNDER_TEST = inMemoryDaoProvider

internal val SERVICE_CONFIG = ServiceConfig()

internal val BUS: EventBus = InMemoryEventBus()

internal val CACHE: Cache = MockCache()

//interface val AUTHENTICATION_SERVICE_CONFIG = AuthenticationServiceConfig()

internal val BITFRAME_CONFIG = BitframeApplicationConfig(inMemoryDaoProvider)

internal val AUTHENTICATION_SERVICE_UNDER_TEST = AuthenticationServiceImpl(BITFRAME_CONFIG)

internal val AUTHENTICATION_CONTROLLER_UNDER_TEST = AuthenticationControllerImpl(AUTHENTICATION_SERVICE_UNDER_TEST)