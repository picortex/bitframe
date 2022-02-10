@file:JsExport
@file:Suppress("EXPERIMENTAL_API_USAGE", "NON_EXPORTABLE_TYPE")

import kotlinext.js.jso
import logging.Appender
import logging.ConsoleAppender
import logging.Logger
import pimonitor.PiMonitorReactScope
import pimonitor.PiMonitorScopeConfig

fun scope(config: SDKConfiguration): PiMonitorReactScope {

    val serviceConfig = jso<ServiceConfiguration> {
        appId = config.appId
        url = config.url
        logging = config.serviceLoggers
    }

    val service = client(serviceConfig)

    val vmConfig = config.viewModel ?: jso {
        logging = jso {
            console = true
            sentry = false
        }
        recoveryTime = PiMonitorScopeConfig.DEFAULT_RECOVERY_TIME.toInt()
        transitionTime = PiMonitorScopeConfig.DEFAULT_TRANSITION_TIME.toInt()
    }

    // loggers
    val appenders = mutableListOf<Appender>()
    if (vmConfig.logging?.console != false) {
        appenders.add(ConsoleAppender())
    }

    val logger = Logger(*appenders.toTypedArray())

    val piMonitorViewModelConfig = PiMonitorScopeConfig(
        service = service,
        recoveryTime = vmConfig.recoveryTime?.toLong() ?: PiMonitorScopeConfig.DEFAULT_RECOVERY_TIME,
        transitionTime = vmConfig.transitionTime?.toLong() ?: PiMonitorScopeConfig.DEFAULT_TRANSITION_TIME,
        logger = logger
    )

    return PiMonitorReactScope(piMonitorViewModelConfig)
}