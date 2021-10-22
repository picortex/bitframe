package pimonitor.monitored

import later.Later
import pimonitor.monitors.MonitorRef

interface MonitoredBusinessDao {
    fun create(params: CreateMonitoredBusinessParams, monitorRef: MonitorRef): Later<MonitoredBusiness>
    fun all(): Later<List<MonitoredBusiness>>
}