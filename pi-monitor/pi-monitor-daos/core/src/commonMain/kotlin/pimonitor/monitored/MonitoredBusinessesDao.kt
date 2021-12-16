package pimonitor.monitored

import kotlinx.collections.interoperable.List
import later.Later
import pimonitor.monitors.MonitorRef
import pimonitor.monitored.CreateMonitoredBusinessParams
import pimonitor.monitored.MonitoredBusiness

interface MonitoredBusinessesDao {
    fun create(params: CreateMonitoredBusinessParams, monitorRef: MonitorRef): Later<MonitoredBusiness>
    fun all(): Later<List<MonitoredBusiness>>
}