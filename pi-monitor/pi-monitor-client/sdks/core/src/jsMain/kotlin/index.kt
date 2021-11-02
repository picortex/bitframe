@file:Suppress("EXPERIMENTAL_API_USAGE", "NON_EXPORTABLE_TYPE")
@file:JsExport

import bitframe.BitframeService
import bitframe.authentication.signin.exports.SignInServiceWrapper
import bitframe.service.config.KtorClientConfiguration
import cache.AsyncStorageCache
import cache.BrowserCache
import cache.MockCache
import logging.ConsoleAppender
import logging.Logging
import pimonitor.PiMonitorService
import pimonitor.PiMonitorServiceKtor
import pimonitor.PiMonitorServiceStub
import pimonitor.StubServiceConfig
import pimonitor.authentication.signup.exports.SignUpServiceWrapper
import pimonitor.evaluation.businesses.exports.BusinessesServiceWrapper
import platform.Platform

external interface ServiceConfiguration {
    var appId: String
    var url: String?
    var simulationTime: Int?
    var disableViewModelLogs: Boolean?
}

private var isLoggingEnabled = false

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
        config = StubServiceConfig(appId, simulationTime),
        cache = cache
    ) else PiMonitorServiceKtor(
        config = KtorClientConfiguration(url, appId),
        cache = cache
    )
}

fun service(config: ServiceConfiguration): PiMonitorService = client(config)

fun signInService(client: BitframeService) = SignInServiceWrapper(client.signIn)

fun signUpService(client: PiMonitorService) = SignUpServiceWrapper(client.signUp)

fun businessesService(client: PiMonitorService) = BusinessesServiceWrapper(client.businesses)