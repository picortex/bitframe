package bitframe.authentication.server.signin

import bitframe.authentication.users.UsersDao
import bitframe.events.EventBus
import bitframe.service.config.ServiceConfig
import kotlinx.coroutines.CoroutineScope
import kotlin.jvm.JvmOverloads

class SignInServiceConfig @JvmOverloads constructor(
    internal val usersDao: UsersDao,
    val bus: EventBus,
    override val scope: CoroutineScope = ServiceConfig.DEFAULT_SCOPE
) : ServiceConfig