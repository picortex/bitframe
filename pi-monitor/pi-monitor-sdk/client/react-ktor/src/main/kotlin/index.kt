@file:JsExport
@file:Suppress("EXPERIMENTAL_API_USAGE", "NON_EXPORTABLE_TYPE")

import logging.ConsoleAppender
import logging.Logger
import pimonitor.PiMonitorReactScope
import pimonitor.PiMonitorViewModelConfig

fun scope(config: ServiceConfiguration): PiMonitorReactScope {
    val vmConfig = PiMonitorViewModelConfig(
        service = service(config),
        logger = if (config.disableViewModelLogs == true) Logger() else Logger(ConsoleAppender())
    )
    return PiMonitorReactScope(vmConfig)
}