@file:Suppress("EXPERIMENTAL_API_USAGE", "NON_EXPORTABLE_TYPE")
@file:JsExport

import cache.AsyncStorageCache
import cache.MockCache
import kotlinx.serialization.json.Json
import logging.Appender
import logging.ConsoleAppender
import logging.Logger
import pimonitor.api.PiMonitorApi
import pimonitor.api.PiMonitorServiceKtor
import pimonitor.api.PiMonitorApiKtorConfig
import platform.Platform

@JsName("clientWithConfigBlock")
fun client(
    block: ServiceConfiguration.() -> Unit
): PiMonitorApi = client(
    js("{}").unsafeCast<ServiceConfiguration>().apply(block)
)

fun client(config: ServiceConfiguration): PiMonitorApi {
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
        PiMonitorApiKtorConfig(appId, url, cache, logger = logger, json = json),
    )
}

fun service(config: ServiceConfiguration): PiMonitorApi = client(config)