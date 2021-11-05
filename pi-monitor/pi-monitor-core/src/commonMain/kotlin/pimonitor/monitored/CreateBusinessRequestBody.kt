package pimonitor.monitored

import kotlinx.serialization.Serializable
import pimonitor.monitors.MonitorRef

@Serializable
class CreateBusinessRequestBody(
    val params: CreateMonitoredBusinessParams,
    val monitor: MonitorRef
)