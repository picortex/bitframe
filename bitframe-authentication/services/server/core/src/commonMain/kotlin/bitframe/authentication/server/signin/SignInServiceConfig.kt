package bitframe.authentication.server.signin

import bitframe.authentication.users.UsersDao
import bitframe.events.EventBus
import bitframe.service.config.ServiceConfig
import kotlinx.coroutines.CoroutineScope
import kotlin.jvm.JvmOverloads

interface SignInServiceConfig : ServiceConfig {
    val usersDao: UsersDao
}