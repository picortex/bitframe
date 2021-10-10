package pimonitor.monitored

import later.Later

interface MonitoredBusinessDao {
    fun all(): Later<List<MonitoredBusiness>>
}