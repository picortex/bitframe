package bitframe.authentication.server.spaces

import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.users.UsersDao
import bitframe.service.config.ServiceConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.jvm.JvmOverloads

open class SpacesServiceConfig @JvmOverloads constructor(
    val spacesDao: SpacesDao,
    val usersDao: UsersDao,
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob())
) : ServiceConfig