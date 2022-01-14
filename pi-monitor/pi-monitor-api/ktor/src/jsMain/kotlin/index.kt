@file:Suppress("EXPERIMENTAL_API_USAGE", "NON_EXPORTABLE_TYPE")
@file:JsExport

import bitframe.api.BitframeService
import bitframe.authentication.signin.exports.SignInServiceWrapper
import cache.AsyncStorageCache
import cache.BrowserCache
import cache.MockCache
import logging.Appender
import logging.ConsoleAppender
import logging.Logger
import pimonitor.api.PiMonitorService
import pimonitor.api.PiMonitorServiceKtor
import pimonitor.api.PiMonitorServiceKtorConfig
import pimonitor.authentication.signup.exports.SignUpServiceWrapper
import pimonitor.evaluation.businesses.exports.BusinessesServiceWrapper
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
        Platform.isBrowser -> BrowserCache()
        Platform.isReactNative -> AsyncStorageCache()
        else -> MockCache().also { logger.warn("Unknown javascript platform, using a non persistent cache") }
    }

    return PiMonitorServiceKtor(
        PiMonitorServiceKtorConfig(appId, url, cache, logger = logger),
    )
}

fun service(config: ServiceConfiguration): PiMonitorService = client(config)

fun signInService(client: BitframeService) = SignInServiceWrapper(client.signIn)

fun signUpService(client: PiMonitorService) = SignUpServiceWrapper(client.signUp)

fun businessesService(client: PiMonitorService) = BusinessesServiceWrapper(client.businesses)