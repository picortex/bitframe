@file:Suppress("EXPERIMENTAL_API_USAGE", "NON_EXPORTABLE_TYPE")
@file:JsExport

import bitframe.authentication.signin.exports.SignInServiceWrapper
import bitframe.service.config.KtorClientConfiguration
import logging.ConsoleAppender
import logging.Logging
import pimonitor.PiMonitorService
import pimonitor.PiMonitorServiceKtor
import pimonitor.PiMonitorServiceStub
import pimonitor.StubServiceConfig
import pimonitor.authentication.signup.exports.SignUpServiceWrapper
import pimonitor.evaluation.business.exports.BusinessesServiceWrapper

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
    return if (url == null) PiMonitorServiceStub(
        StubServiceConfig(appId, simulationTime)
    ) else PiMonitorServiceKtor(
        KtorClientConfiguration(url, appId)
    )
}

fun service(config: ServiceConfiguration): PiMonitorService = client(config)

fun signInService(client: PiMonitorService) = SignInServiceWrapper(client.signIn)

fun signUpService(client: PiMonitorService) = SignUpServiceWrapper(client.signUp)

fun businessesService(client: PiMonitorService) = BusinessesServiceWrapper(client.businesses)