package bitframe.server.modules.authentication.services

import bitframe.authentication.server.signin.SignInServiceConfig
import bitframe.authentication.server.users.UsersServiceConfig
import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.users.UsersDao
import bitframe.events.EventBus
import bitframe.server.BitframeApplicationConfig
import cache.Cache
import kotlinx.coroutines.CoroutineScope

interface AuthenticationServiceConfig : SignInServiceConfig, UsersServiceConfig {
    val cache: Cache

    companion object {
        operator fun invoke(config: BitframeApplicationConfig) = object : AuthenticationServiceConfig {
            override val cache: Cache = config.cache
            override val usersDao: UsersDao = config.daoProvider.users
            override val bus: EventBus = config.bus
            override val scope: CoroutineScope = config.scope
            override val spacesDao: SpacesDao = config.daoProvider.spaces
        }
    }
}