package pimonitor.data

import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.spaces.SpacesDaoInMemory
import bitframe.authentication.users.UsersDao
import bitframe.authentication.users.UsersDaoInMemory
import pimonitor.monitored.MonitoredBusinessDaoInMemory
import pimonitor.monitored.MonitoredBusinessesDao
import pimonitor.monitors.MonitorDao
import pimonitor.monitors.MonitorDaoInMemory
import pimonitor.server.PiMonitorDaoProvider

class InMemoryPiMonitorDaoProvider : PiMonitorDaoProvider {
    override val monitors: MonitorDao = MonitorDaoInMemory()
    override val businesses: MonitoredBusinessesDao = MonitoredBusinessDaoInMemory()
    override val users: UsersDao = UsersDaoInMemory()
    override val spaces: SpacesDao = SpacesDaoInMemory()
}