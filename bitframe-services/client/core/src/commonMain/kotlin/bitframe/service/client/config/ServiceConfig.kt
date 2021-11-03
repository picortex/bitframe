package bitframe.service.client.config

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic
import bitframe.service.config.ServiceConfig as CoreServiceConfig

interface ServiceConfig : CoreServiceConfig {
    val appId: String

    companion object {
        @JvmStatic
        val DEFAULT_SCOPE
            get() = CoreServiceConfig.DEFAULT_SCOPE

        @JvmSynthetic
        operator fun invoke(
            appId: String,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): ServiceConfig = object : ServiceConfig {
            override val appId: String = appId
            override val scope: CoroutineScope = scope
        }

        @JvmOverloads
        @JvmStatic
        fun create(
            appId: String,
            scope: CoroutineScope = DEFAULT_SCOPE
        ) = invoke(appId, scope)
    }
}