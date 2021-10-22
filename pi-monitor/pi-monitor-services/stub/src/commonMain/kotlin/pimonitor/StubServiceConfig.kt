package pimonitor

import bitframe.daos.config.InMemoryDaoConfig
import bitframe.service.config.ServiceConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class StubServiceConfig(
    override val appId: String,
    val simulationTime: Long = 0L,
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob())
) : ServiceConfig(appId, scope) {
    fun toInMemoryDaoConfig() = InMemoryDaoConfig(simulationTime, scope)
}