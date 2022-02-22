package pimonitor.core.businesses

import kotlinx.collections.interoperable.List
import later.Later

interface BusinessesLoaderUseCase {
    fun loadAllBusinessMonitoredBy(spaceId: String): Later<List<MonitorBusinessBasicInfo>>
}