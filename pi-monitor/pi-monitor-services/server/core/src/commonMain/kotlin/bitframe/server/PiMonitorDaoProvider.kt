package bitframe.server

import bitframe.monitored.MonitoredBusinessesDao
import bitframe.monitors.MonitorDao

interface PiMonitorDaoProvider : BitframeDaoProvider {
    val monitors: MonitorDao
    val businesses: MonitoredBusinessesDao
}