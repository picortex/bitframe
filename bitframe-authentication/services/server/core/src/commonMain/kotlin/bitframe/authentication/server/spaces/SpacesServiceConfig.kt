package bitframe.authentication.server.spaces

import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.users.UsersDao
import bitframe.service.config.ServiceConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.jvm.JvmOverloads

interface SpacesServiceConfig : ServiceConfig {
    val spacesDao: SpacesDao
    val usersDao: UsersDao
}