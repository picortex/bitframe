package bitframe.authentication.server.users

import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.users.UsersDao
import bitframe.service.config.ServiceConfig
import kotlinx.coroutines.CoroutineScope

class UsersServiceConfig(
    val usersDao: UsersDao,
    val spacesDao: SpacesDao,
    override val scope: CoroutineScope = ServiceConfig.DEFAULT_SCOPE
) : ServiceConfig