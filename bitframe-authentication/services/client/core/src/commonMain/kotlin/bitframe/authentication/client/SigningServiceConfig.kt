package bitframe.authentication.client

import bitframe.authentication.signin.Session
import events.EventBus
import bitframe.service.client.config.ServiceConfig
import cache.Cache
import kotlinx.coroutines.CoroutineScope
import live.Live
import logging.Logger
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface SigningServiceConfig : ServiceConfig {
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
            logger: Logger = ServiceConfig.DEFAULT_LOGGER,
            scope: CoroutineScope = ServiceConfig.DEFAULT_SCOPE,
        ): SigningServiceConfig = object : SigningServiceConfig, ServiceConfig by ServiceConfig(appId, cache, bus, logger, scope) {
            override val signInSession: Live<Session> = signInSession
        }

        @JvmStatic
        @JvmOverloads
        fun create(
            appId: String,
            cache: Cache,
            signInSession: Live<Session> = Live(Session.Unknown),
            bus: EventBus = ServiceConfig.DEFAULT_BUS,
            logger: Logger = ServiceConfig.DEFAULT_LOGGER,
            scope: CoroutineScope = ServiceConfig.DEFAULT_SCOPE,
        ) = invoke(appId, cache, signInSession, bus, logger, scope)
    }
}