package bitframe.data

import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.spaces.SpacesDaoInMemory
import bitframe.authentication.users.UsersDao
import bitframe.authentication.users.UsersDaoInMemory
import bitframe.monitored.MonitoredBusinessDaoInMemory
import bitframe.monitored.MonitoredBusinessesDao
import bitframe.monitors.MonitorDao
import bitframe.monitors.MonitorDaoInMemory
import bitframe.server.PiMonitorDaoProvider

class InMemoryPiMonitorDaoProvider : PiMonitorDaoProvider {
    override val monitors: MonitorDao = MonitorDaoInMemory()
    override val businesses: MonitoredBusinessesDao = MonitoredBusinessDaoInMemory()
    override val users: UsersDao = UsersDaoInMemory()
    override val spaces: SpacesDao = SpacesDaoInMemory()
}