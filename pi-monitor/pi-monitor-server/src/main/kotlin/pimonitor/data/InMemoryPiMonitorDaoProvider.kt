package pimonitor.data

import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.spaces.SpacesDaoInMemory
import bitframe.authentication.users.UsersDao
import bitframe.authentication.users.UsersDaoInMemory
import pimonitor.monitors.MonitorDao
import pimonitor.monitors.MonitorDaoInMemory

class InMemoryPiMonitorDaoProvider : PiMonitorDAOProvider {
    override val monitorsDao: MonitorDao = MonitorDaoInMemory()
    override val users: UsersDao = UsersDaoInMemory()
    override val spaces: SpacesDao = SpacesDaoInMemory()
}