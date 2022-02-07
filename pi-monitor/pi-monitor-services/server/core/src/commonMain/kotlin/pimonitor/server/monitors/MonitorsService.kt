package pimonitor.server.monitors

import bitframe.daos.conditions.isEqualTo
import bitframe.daos.get
import bitframe.service.server.config.ServiceConfig
import later.Later
import later.await
import later.later
import pimonitor.monitors.CooperateMonitor
import pimonitor.monitors.IndividualMonitor
import pimonitor.monitors.Monitor
import pimonitor.monitors.MonitorsService

class MonitorsService(
    override val config: ServiceConfig
) : MonitorsService(config) {

    private val individualMonitorsDao = config.daoFactory.get<IndividualMonitor>()
    private val cooperateMonitorsDao = config.daoFactory.get<CooperateMonitor>()

    private val scope get() = config.scope

    override fun load(uid: String): Later<Monitor?> = scope.later {
        val ind = individualMonitorsDao.loadOrNull(uid)
        val cop = cooperateMonitorsDao.loadOrNull(uid)
        ind.await() ?: cop.await()
    }

    override fun monitor(with: UserRef): Later<Monitor> = scope.later {
        val condition = "userRef.uid" isEqualTo with.uid
        val ind = individualMonitorsDao.all(condition)
        val cop = cooperateMonitorsDao.all(condition)
        ind.await().firstOrNull() ?: cop.await().firstOrNull() ?: throw RuntimeException("Monitor with userRef.uid is not found")
    }
}