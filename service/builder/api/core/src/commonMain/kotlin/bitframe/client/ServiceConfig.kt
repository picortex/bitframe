@file:Suppress("WRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE")

package bitframe.client

import bitframe.core.Session
import bitframe.core.Session.Unknown
import keep.Cache
import events.EventBus
import kotlinx.coroutines.CoroutineScope
import cinematic.MutableLive
import cinematic.mutableLiveOf
import lexi.Logger
import kotlin.js.JsExport
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic
import bitframe.core.ServiceConfig as CoreServiceConfig

@Deprecated("In favour of bitframe.ApiConfig")
@JsExport
interface ServiceConfig : CoreServiceConfig {
    val appId: String
    val session: MutableLive<Session>
    val cache: Cache

    companion object {
        @JvmField
        val DEFAULT_SCOPE = CoreServiceConfig.DEFAULT_SCOPE

        @JvmField
        val DEFAULT_BUS = CoreServiceConfig.DEFAULT_BUS

        @JvmField
        val DEFAULT_LIVE_SESSION = mutableLiveOf<Session>(Unknown)

        @JvmField
        val DEFAULT_LOGGER = CoreServiceConfig.DEFAULT_LOGGER

        @JvmSynthetic
        operator fun invoke(
            appId: String,
            cache: Cache,
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            session: MutableLive<Session> = DEFAULT_LIVE_SESSION,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): ServiceConfig = object : ServiceConfig, CoreServiceConfig by CoreServiceConfig(bus, logger, scope) {
            override val appId = appId
            override val cache = cache
            override val session = session
        }

        @JvmOverloads
        @JvmStatic
        fun create(
            appId: String,
            cache: Cache,
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            session: MutableLive<Session> = DEFAULT_LIVE_SESSION,
            scope: CoroutineScope = DEFAULT_SCOPE
        ) = invoke(appId, cache, bus, logger, session, scope)
    }
}