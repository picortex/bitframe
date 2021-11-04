package pimonitor.data

import bitframe.server.data.DAOProvider
import pimonitor.monitors.MonitorDao

interface PiMonitorDAOProvider : DAOProvider {
    val monitorsDao: MonitorDao
}