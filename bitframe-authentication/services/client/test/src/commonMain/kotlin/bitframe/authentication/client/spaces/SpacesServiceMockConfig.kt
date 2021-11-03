package bitframe.authentication.client.spaces

import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.users.UsersDao
import bitframe.service.config.ServiceConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.jvm.JvmOverloads

open class SpacesServiceMockConfig @JvmOverloads constructor(
    val spaces: SpacesDao,
    val users: UsersDao,
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob())
) : ServiceConfig