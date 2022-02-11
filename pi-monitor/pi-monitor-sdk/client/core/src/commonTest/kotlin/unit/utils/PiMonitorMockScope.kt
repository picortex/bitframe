package unit.utils

import pimonitor.PiMonitorScope
import pimonitor.PiMonitorScopeConfig
import pimonitor.api.PiMonitorServiceMock

fun PiMonitorMockScope(): PiMonitorScope {
    val service = PiMonitorServiceMock()
    val config = PiMonitorScopeConfig(service)
    return PiMonitorScope(config)
}