@file:Suppress("EXPERIMENTAL_API_USAGE")

import bitframe.authentication.TestClientConfiguration
import bitframe.authentication.TestClientConfiguration.Companion.DEFAULT_SIMULATION_TIME
import logging.ConsoleAppender
import logging.Logging
import pimonitor.PiMonitorService
import pimonitor.test.PiMonitorServiceTestImpl

external interface Configuration {
    var appId: String
    var simulationTime: Int?
    var disableViewModelLogs: Boolean?
}

private var isLoggingEnabled = false

@JsExport
fun service(config: Configuration): PiMonitorService {
    if (config.disableViewModelLogs == false && !isLoggingEnabled) {
        Logging.init(ConsoleAppender())
        isLoggingEnabled = true
    }
    return PiMonitorServiceTestImpl(
        TestClientConfiguration(
            appId = config.appId,
            simulationTime = config.simulationTime ?: DEFAULT_SIMULATION_TIME
        )
    )
}