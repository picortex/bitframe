package pimonitor.core.business.params

import kotlinx.serialization.Serializable

@Serializable // TODO: This is not serialized well
data class LoadReportParams(
    override val businessId: String,
    override val start: Double,
    override val end: Double
) : LoadReportRawParams
