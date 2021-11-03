package bitframe.authentication.client.signin

import bitframe.authentication.users.UsersDao
import bitframe.authentication.users.UsersDaoInMemory
import bitframe.events.EventBus
import bitframe.events.InMemoryEventBus
import bitframe.service.config.ServiceConfig
import cache.Cache
import cache.MockCache
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class SignInServiceMockConfig(
    override val appId: String = "<test-id>",
    internal val usersDao: UsersDao = UsersDaoInMemory(),
    override val cache: Cache = MockCache(),
    override val bus: EventBus = InMemoryEventBus(),
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob())
) : SignInServiceConfig, ServiceConfig