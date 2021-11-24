package bitframe.monitored

import kotlinx.serialization.Serializable
import bitframe.monitors.MonitorRef

@Serializable
class CreateBusinessRequestBody(
    val params: CreateMonitoredBusinessParams,
    val monitor: MonitorRef
)