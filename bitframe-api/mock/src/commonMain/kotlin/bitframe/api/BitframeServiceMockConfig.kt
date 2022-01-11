package bitframe.api

import bitframe.authentication.client.signin.SignInServiceConfig
import bitframe.authentication.client.signin.SignInServiceMockConfig
import bitframe.authentication.signin.Session
import bitframe.authentication.users.User
import bitframe.service.client.config.ServiceConfig
import cache.Cache
import cache.MockCache
import events.EventBus
import kotlinx.coroutines.CoroutineScope
import live.Live
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface BitframeServiceMockConfig : BitframeServiceConfig, SignInServiceMockConfig {
    companion object {
        @JvmField
        val DEFAULT_SCOPE = ServiceConfig.DEFAULT_SCOPE

        @JvmField
        val DEFAULT_CACHE = MockCache()

        @JvmField
        val DEFAULT_BUS = ServiceConfig.DEFAULT_BUS

        @JvmField
        val DEFAULT_USERS = mutableListOf<User>()

        @JvmField
        val DEFAULT_APP_ID = "<test-id>"

        @JvmSynthetic
        operator fun invoke(
            appId: String = DEFAULT_APP_ID,
            users: MutableList<User> = DEFAULT_USERS,
            cache: Cache = DEFAULT_CACHE,
            signInSession: Live<Session> = SignInServiceConfig.DEFAULT_SIGN_IN_SESSION,
            bus: EventBus = DEFAULT_BUS,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): BitframeServiceMockConfig = object : BitframeServiceMockConfig, SignInServiceMockConfig by SignInServiceMockConfig(appId, users, cache, signInSession, bus, scope) {
            override val users: MutableList<User> = users
        }

        @JvmStatic
        @JvmOverloads
        fun create(
            appId: String = DEFAULT_APP_ID,
            users: MutableList<User> = DEFAULT_USERS,
            cache: Cache = DEFAULT_CACHE,
            signInSession: Live<Session> = SignInServiceConfig.DEFAULT_SIGN_IN_SESSION,
            bus: EventBus = DEFAULT_BUS,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): BitframeServiceMockConfig = invoke(appId, users, cache, signInSession, bus, scope)
    }
}