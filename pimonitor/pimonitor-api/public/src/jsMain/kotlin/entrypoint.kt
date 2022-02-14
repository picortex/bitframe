import bitframe.client.BitframeApiKtorConfig
import bitframe.client.BitframeApiMockConfig
import bitframe.client.configurators.ApiConfigurator
import bitframe.client.configurators.ApiConfiguratorImpl
import bitframe.client.configurators.ApiMode
import bitframe.client.configurators.toApiMode
import cache.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import logging.Appender
import logging.ConsoleAppender
import logging.Logger
import pimonitor.client.PiMonitorApi
import pimonitor.client.PiMonitorApiKtor
import pimonitor.client.PiMonitorApiMock
import platform.Platform

fun api(configurator: ApiConfigurator.() -> Unit): PiMonitorApi {
    val cfg = ApiConfiguratorImpl().also(configurator)

    val appenders = buildList<Appender> {
        if (cfg.logging?.console == true) add(ConsoleAppender())
    }

    val mode = cfg.toApiMode()

    val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    val json = Json { prettyPrint = false }

    val logger = Logger(*appenders.toTypedArray())

    val namespace = when (mode) {
        is ApiMode.Live -> "app.live"
        is ApiMode.Mock -> "app.mock"
    }

    val cache = when {
        Platform.isBrowser -> BrowserCache(
            BrowserCacheConfig(namespace = namespace, json = json, scope = scope)
        )
        Platform.isReactNative -> AsyncStorageCache(
            AsyncStorageCacheConfig(namespace = namespace, json = json, scope = scope)
        )
        else -> MockCache(
            MockCacheConfig(namespace = namespace, scope = scope)
        ).also { logger.warn("Unknown javascript platform, using a non persistent cache") }
    }

    return when (mode) {
        is ApiMode.Live -> PiMonitorApiKtor(
            BitframeApiKtorConfig(
                appId = mode.appId, url = mode.url, cache = cache, json = json, logger = logger, scope = scope
            )
        )
        is ApiMode.Mock -> PiMonitorApiMock(
            BitframeApiMockConfig(
                cache = cache, logger = logger, scope = scope
            )
        )
    }
}