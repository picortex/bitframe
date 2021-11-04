package pimonitor.monitored

import bitframe.daos.config.InMemoryDaoConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import later.Later
import later.later
import pimonitor.monitors.MonitorRef

interface MonitoredBusinessDaoInMemoryConfig : InMemoryDaoConfig {
    val businesses: MutableMap<String, MonitoredBusiness>

    companion object {
        operator fun invoke(
            businesses: MutableMap<String, MonitoredBusiness> = mutableMapOf(),
            simulationTime: Long = InMemoryDaoConfig.DEFAULT_SIMULTATION_TIME,
            scope: CoroutineScope = InMemoryDaoConfig.DEFAULT_SCOPE
        ): MonitoredBusinessDaoInMemoryConfig = object : MonitoredBusinessDaoInMemoryConfig,
            InMemoryDaoConfig by InMemoryDaoConfig(simulationTime, scope) {
            override val businesses: MutableMap<String, MonitoredBusiness> = businesses
        }
    }
}