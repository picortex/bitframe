package pimonitor.client

import bitframe.client.BitframeApiKtorConfig
import bitframe.client.BitframeApiMockConfig
import bitframe.client.configurators.ApiConfigurator
import bitframe.client.configurators.ApiConfiguratorImpl
import cache.MockCache
import logging.Appender
import logging.ConsoleAppender
import logging.Logger

fun api(configurator: ApiConfigurator.() -> Unit): PiMonitorApi {
    val cfg = ApiConfiguratorImpl().also(configurator)
    val appenders = buildList<Appender> {
        if (cfg.logging?.console == true) add(ConsoleAppender())
    }
    val logger = Logger(*appenders.toTypedArray())
    return if (cfg.appId?.isNotEmpty() == true && cfg.url?.isNotEmpty() == true) {
        val appId = cfg.appId!!
        val url = cfg.url!!
        val apiConfig = BitframeApiKtorConfig(
            appId, url, MockCache(), logger = logger
        )
        PiMonitorApiKtor(apiConfig)
    } else {
        val apiConfig = BitframeApiMockConfig(
            logger = logger
        )
        PiMonitorApiMock(apiConfig)
    }
}