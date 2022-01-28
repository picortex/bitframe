package bitframe.authentication.client

import bitframe.authentication.signin.Session
import events.EventBus
import bitframe.service.client.config.ServiceConfig
import cache.Cache
import kotlinx.coroutines.CoroutineScope
import live.MutableLive
import logging.Logger
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface SigningServiceConfig : ServiceConfig {
    val signInSession: MutableLive<Session>

    companion object {
        @JvmField
        val DEFAULT_SIGN_IN_SESSION: MutableLive<Session> = MutableLive(Session.Unknown)

        @JvmSynthetic
        operator fun invoke(
            appId: String,
            cache: Cache,
            signInSession: MutableLive<Session> = DEFAULT_SIGN_IN_SESSION,
            bus: EventBus = ServiceConfig.DEFAULT_BUS,
            logger: Logger = ServiceConfig.DEFAULT_LOGGER,
            scope: CoroutineScope = ServiceConfig.DEFAULT_SCOPE,
        ): SigningServiceConfig = object : SigningServiceConfig, ServiceConfig by ServiceConfig(appId, cache, bus, logger, scope) {
            override val signInSession: MutableLive<Session> = signInSession
        }

        @JvmStatic
        @JvmOverloads
        fun create(
            appId: String,
            cache: Cache,
            signInSession: MutableLive<Session> = MutableLive(Session.Unknown),
            bus: EventBus = ServiceConfig.DEFAULT_BUS,
            logger: Logger = ServiceConfig.DEFAULT_LOGGER,
            scope: CoroutineScope = ServiceConfig.DEFAULT_SCOPE,
        ) = invoke(appId, cache, signInSession, bus, logger, scope)
    }
}