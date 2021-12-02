package pimonitor.server

import bitframe.server.BitframeDaoProvider
import pimonitor.monitored.MonitoredBusinessesDao
import pimonitor.monitors.MonitorDao

interface PiMonitorDaoProvider : BitframeDaoProvider {
    val monitors: MonitorDao
    val businesses: MonitoredBusinessesDao
}