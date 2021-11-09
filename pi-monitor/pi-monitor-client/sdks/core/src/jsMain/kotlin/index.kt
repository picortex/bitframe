@file:Suppress("EXPERIMENTAL_API_USAGE", "NON_EXPORTABLE_TYPE")
@file:JsExport

import bitframe.authentication.signin.exports.SignInServiceWrapper
import bitframe.client.BitframeService
import cache.AsyncStorageCache
import cache.BrowserCache
import cache.MockCache
import logging.ConsoleAppender
import logging.Logging
import pimonitor.PiMonitorServiceStub
import pimonitor.PiMonitorServiceStubConfig
import pimonitor.authentication.signup.exports.SignUpServiceWrapper
import pimonitor.client.PiMonitorService
import pimonitor.client.PiMonitorServiceKtor
import pimonitor.client.PiMonitorServiceKtorConfig
import pimonitor.evaluation.businesses.exports.BusinessesServiceWrapper
import platform.Platform

external interface ServiceConfiguration {
    var appId: String
    var url: String?
    var simulationTime: Int?
    var disableViewModelLogs: Boolean?
}

private var isLoggingEnabled = false

@JsName("clientWithConfigBlock")
fun client(
    block: ServiceConfiguration.() -> Unit
): PiMonitorService = client(
    js("{}").unsafeCast<ServiceConfiguration>().apply(block)
)

fun client(config: ServiceConfiguration): PiMonitorService {
    if (config.disableViewModelLogs != true && !isLoggingEnabled) {
        Logging.init(ConsoleAppender())
        isLoggingEnabled = true
    }
    val url = config.url
    val appId = config.appId
    val simulationTime = config.simulationTime?.toLong() ?: 2000L
    val cache = when {
        Platform.isBrowser -> BrowserCache()
        Platform.isReactNative -> AsyncStorageCache()
        else -> MockCache().also { console.log("Unknown platform, using a non persitient cach") }
    }
    return if (url == null) PiMonitorServiceStub(
        PiMonitorServiceStubConfig(appId, simulationTime, cache),
    ) else PiMonitorServiceKtor(
        PiMonitorServiceKtorConfig(appId, url, cache),
    )
}

fun service(config: ServiceConfiguration): PiMonitorService = client(config)

fun signInService(client: BitframeService) = SignInServiceWrapper(client.signIn)

fun signUpService(client: PiMonitorService) = SignUpServiceWrapper(client.signUp)

fun businessesService(client: PiMonitorService) = BusinessesServiceWrapper(client.businesses)