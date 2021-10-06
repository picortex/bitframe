@file:Suppress("EXPERIMENTAL_API_USAGE", "NON_EXPORTABLE_TYPE")
@file:JsExport

import bitframe.authentication.signin.exports.SignInServiceWrapper
import logging.ConsoleAppender
import logging.Logging
import pimonitor.PiMonitorService
import pimonitor.PiMonitorServiceStub
import pimonitor.StubServiceConfig
import pimonitor.authentication.signup.exports.SignUpServiceWrapper
import pimonitor.evaluation.business.exports.BusinessesServiceWrapper

external interface ServiceConfiguration {
    var appId: String
    var simulationTime: Int?
    var disableViewModelLogs: Boolean?
}

private var isLoggingEnabled = false

fun client(config: ServiceConfiguration): PiMonitorService {
    if (config.disableViewModelLogs != true && !isLoggingEnabled) {
        Logging.init(ConsoleAppender())
        isLoggingEnabled = true
    }
    return PiMonitorServiceStub(
        StubServiceConfig(
            appId = config.appId,
            simulationTime = config.simulationTime?.toLong() ?: 2000L
        )
    )
}

fun service(config: ServiceConfiguration): PiMonitorService = client(config)

fun signInService(client: PiMonitorService) = SignInServiceWrapper(client.signIn)

fun signUpService(client: PiMonitorService) = SignUpServiceWrapper(client.signUp)

fun businessesService(client: PiMonitorService) = BusinessesServiceWrapper(client.businesses)