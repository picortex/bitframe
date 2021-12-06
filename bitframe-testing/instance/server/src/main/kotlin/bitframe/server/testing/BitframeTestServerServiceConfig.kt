package bitframe.server.testing

import bitframe.authentication.server.signin.SignInServiceConfig
import bitframe.authentication.server.spaces.SpacesServiceConfig
import bitframe.authentication.server.users.UsersServiceConfig
import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.users.UsersDao
import events.EventBus
import bitframe.service.config.ServiceConfig
import kotlinx.coroutines.CoroutineScope

class BitframeTestServerServiceConfig(
    override val spacesDao: SpacesDao,
    override val usersDao: UsersDao,
    override val bus: EventBus = ServiceConfig.DEFAULT_BUS,
    override val scope: CoroutineScope = ServiceConfig.DEFAULT_SCOPE
) : SpacesServiceConfig, UsersServiceConfig, SignInServiceConfig