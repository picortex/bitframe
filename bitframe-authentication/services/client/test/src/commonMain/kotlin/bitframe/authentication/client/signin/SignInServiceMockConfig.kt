package bitframe.authentication.client.signin

import bitframe.authentication.users.User
import bitframe.events.EventBus
import bitframe.events.InMemoryEventBus
import bitframe.service.client.config.ServiceConfig
import cache.Cache
import cache.MockCache
import kotlinx.coroutines.CoroutineScope
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface SignInServiceMockConfig : ServiceConfig {
    val users: MutableList<User>

    companion object {
        val DEFAULT_SCOPE = ServiceConfig.DEFAULT_SCOPE
        val DEFAULT_CACHE = MockCache()
        val DEFAULT_BUS = InMemoryEventBus()
        val DEFAULT_USERS = mutableListOf<User>()
        val DEFAULT_APP_ID = "<test-id>"

        @JvmSynthetic
        operator fun invoke(
            appId: String = DEFAULT_APP_ID,
            users: MutableList<User> = DEFAULT_USERS,
            cache: Cache = DEFAULT_CACHE,
            bus: EventBus = DEFAULT_BUS,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): SignInServiceMockConfig = object : SignInServiceMockConfig, ServiceConfig by ServiceConfig(appId, cache, bus, scope) {
            override val users: MutableList<User> = users
        }

        @JvmOverloads
        @JvmStatic
        fun create(
            appId: String = DEFAULT_APP_ID,
            users: MutableList<User> = DEFAULT_USERS,
            cache: Cache = DEFAULT_CACHE,
            bus: EventBus = DEFAULT_BUS,
            scope: CoroutineScope = DEFAULT_SCOPE
        ) = invoke(appId, users, cache, bus, scope)
    }
}