package pimonitor.core.businesses.results

import kotlinx.collections.interoperable.List
import kotlinx.serialization.Serializable
import pimonitor.core.businesses.MonitoredBusinessBasicInfo

@Serializable
data class AvailableReportsResults(
    val business: MonitoredBusinessBasicInfo,
    val reports: List<String>
)