package bitframe.monitored

import later.Later
import bitframe.monitors.MonitorRef

interface MonitoredBusinessesDao {
    fun create(params: CreateMonitoredBusinessParams, monitorRef: MonitorRef): Later<MonitoredBusiness>
    fun all(): Later<List<MonitoredBusiness>>
}