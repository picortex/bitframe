package bitframe.service.client.config

import events.EventBus
import cache.Cache
import kotlinx.coroutines.CoroutineScope
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic
import bitframe.service.config.ServiceConfig as CoreServiceConfig

interface ServiceConfig : CoreServiceConfig {
    val appId: String
    val cache: Cache

    companion object {
        @JvmField
        val DEFAULT_SCOPE = CoreServiceConfig.DEFAULT_SCOPE

        @JvmField
        val DEFAULT_BUS = CoreServiceConfig.DEFAULT_BUS

        @JvmSynthetic
        operator fun invoke(
            appId: String,
            cache: Cache,
            bus: EventBus = DEFAULT_BUS,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): ServiceConfig = object : ServiceConfig, CoreServiceConfig by CoreServiceConfig(bus, scope) {
            override val appId = appId
            override val cache = cache
        }

        @JvmOverloads
        @JvmStatic
        fun create(
            appId: String,
            cache: Cache,
            bus: EventBus = DEFAULT_BUS,
            scope: CoroutineScope = DEFAULT_SCOPE
        ) = invoke(appId, cache, bus, scope)
    }
}