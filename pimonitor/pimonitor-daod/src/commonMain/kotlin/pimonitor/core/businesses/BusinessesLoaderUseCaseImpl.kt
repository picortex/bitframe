package pimonitor.core.businesses

import bitframe.core.DaodServiceConfig
import bitframe.core.get
import bitframe.core.isEqualTo
import kotlinx.collections.interoperable.List
import later.Later

class BusinessesLoaderUseCaseImpl(
    val config: DaodServiceConfig
) : BusinessesLoaderUseCase {
    val businessDao by lazy { config.daoFactory.get<MonitorBusinessBasicInfo>() }
    override fun loadAllBusinessMonitoredBy(spaceId: String): Later<List<MonitorBusinessBasicInfo>> {
        val condition = MonitoredBusinessBasicInfo::owningSpaceId isEqualTo spaceId
        return businessDao.all(condition)
    }
}