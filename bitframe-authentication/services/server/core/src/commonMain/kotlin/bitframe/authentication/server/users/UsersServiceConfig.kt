package bitframe.authentication.server.users

import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.users.UsersDao
import bitframe.service.config.ServiceConfig
import kotlinx.coroutines.CoroutineScope

interface UsersServiceConfig : ServiceConfig {
    val usersDao: UsersDao
    val spacesDao: SpacesDao
}