package bitframe.authentication.client.signin

import bitframe.authentication.signin.Session
import bitframe.events.EventBus
import bitframe.service.client.config.ServiceConfig
import cache.Cache
import kotlinx.coroutines.CoroutineScope
import live.Live
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface SignInServiceConfig : ServiceConfig {
    val signInSession: Live<Session>

    companion object {
        @JvmField
        val DEFAULT_SIGN_IN_SESSION: Live<Session> = Live(Session.Unknown)

        @JvmSynthetic
        operator fun invoke(
            appId: String,
            cache: Cache,
            signInSession: Live<Session> = DEFAULT_SIGN_IN_SESSION,
            bus: EventBus = ServiceConfig.DEFAULT_BUS,
            scope: CoroutineScope = ServiceConfig.DEFAULT_SCOPE,
        ): SignInServiceConfig = object : SignInServiceConfig {
            override val signInSession: Live<Session> = signInSession
            override val appId: String = appId
            override val cache: Cache = cache
            override val bus: EventBus = bus
            override val scope: CoroutineScope = scope
        }

        @JvmStatic
        @JvmOverloads
        fun create(
            appId: String,
            cache: Cache,
            signInSession: Live<Session> = Live(Session.Unknown),
            bus: EventBus = ServiceConfig.DEFAULT_BUS,
            scope: CoroutineScope = ServiceConfig.DEFAULT_SCOPE,
        ) = invoke(appId, cache, signInSession, bus, scope)
    }
}