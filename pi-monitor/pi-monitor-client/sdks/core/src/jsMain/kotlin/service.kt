@file:Suppress("EXPERIMENTAL_API_USAGE")
@file:JsExport

import bitframe.authentication.TestClientConfiguration
import bitframe.authentication.TestClientConfiguration.Companion.DEFAULT_SIMULATION_TIME
import logging.ConsoleAppender
import logging.Logging
import pimonitor.PiMonitorService
import pimonitor.test.PiMonitorServiceTest

external interface ServiceConfiguration {
    var appId: String
    var simulationTime: Int?
    var disableViewModelLogs: Boolean?
}

private var isLoggingEnabled = false

fun service(config: ServiceConfiguration): PiMonitorService {
    if (config.disableViewModelLogs != true && !isLoggingEnabled) {
        Logging.init(ConsoleAppender())
        isLoggingEnabled = true
    }
    return PiMonitorServiceTest(
        TestClientConfiguration(
            appId = config.appId,
            simulationTime = config.simulationTime ?: DEFAULT_SIMULATION_TIME
        )
    )
}