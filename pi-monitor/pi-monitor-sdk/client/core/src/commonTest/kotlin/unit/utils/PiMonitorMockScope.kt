package unit.utils

import pimonitor.PiMonitorAppScope
import pimonitor.PiMonitorScopeConfig
import pimonitor.api.PiMonitorApiMock

fun PiMonitorMockScope(): PiMonitorAppScope {
    val service = PiMonitorApiMock()
    val config = PiMonitorScopeConfig(service)
    return PiMonitorAppScope(config)
}