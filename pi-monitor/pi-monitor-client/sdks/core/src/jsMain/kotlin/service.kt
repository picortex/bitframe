@file:Suppress("EXPERIMENTAL_API_USAGE")

import bitframe.BitframeService
import bitframe.BitframeTestClient
import bitframe.authentication.TestClientConfiguration
import bitframe.authentication.TestClientConfiguration.Companion.DEFAULT_SIMULATION_TIME
import logging.ConsoleAppender
import logging.Logging

external interface Configuration {
    var appId: String
    var simulationTime: Int?
    var disableViewModelLogs: Boolean?
}

private var isLoggingEnabled = false

@JsExport
fun service(config: Configuration): BitframeService {
    if (config.disableViewModelLogs == false && !isLoggingEnabled) {
        Logging.init(ConsoleAppender())
        isLoggingEnabled = true
    }
    return BitframeTestClient(
        TestClientConfiguration(
            appId = config.appId,
            simulationTime = config.simulationTime ?: DEFAULT_SIMULATION_TIME
        )
    )
}