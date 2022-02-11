@file:Suppress("EXPERIMENTAL_API_USAGE", "NON_EXPORTABLE_TYPE")
@file:JsExport

import bitframe.api.BitframeService
import cache.AsyncStorageCache
import cache.MockCache
import kotlinx.serialization.json.Json
import logging.Appender
import logging.ConsoleAppender
import logging.Logger
import pimonitor.api.PiMonitorService
import pimonitor.api.PiMonitorServiceKtor
import pimonitor.api.PiMonitorServiceKtorConfig
import platform.Platform

@JsName("clientWithConfigBlock")
fun client(
    block: ServiceConfiguration.() -> Unit
): PiMonitorService = client(
    js("{}").unsafeCast<ServiceConfiguration>().apply(block)
)

fun client(config: ServiceConfiguration): PiMonitorService {
    val url = config.url ?: error("Url must not be null | undefined")
    val appId = config.appId

    // default service appenders
    val appenders = mutableListOf<Appender>()
    if (config.logging?.console != false) {
        appenders.add(ConsoleAppender())
    }
    val logger = Logger(*appenders.toTypedArray())

    // default caches
    val cache = when {
        Platform.isBrowser -> BrowserWACache()
        Platform.isReactNative -> AsyncStorageCache()
        else -> MockCache().also { logger.warn("Unknown javascript platform, using a non persistent cache") }
    }

    val json = Json { encodeDefaults = true }
    return PiMonitorServiceKtor(
        PiMonitorServiceKtorConfig(appId, url, cache, logger = logger, json = json),
    )
}

fun service(config: ServiceConfiguration): PiMonitorService = client(config)