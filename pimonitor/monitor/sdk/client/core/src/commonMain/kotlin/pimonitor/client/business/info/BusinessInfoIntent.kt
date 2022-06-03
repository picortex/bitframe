package pimonitor.client.business.info

import pimonitor.core.business.info.params.BusinessInfoRawFormParams
import pimonitor.core.businesses.MonitoredBusinessBasicInfo

sealed class BusinessInfoIntent {
    data class LoadInfo(val businessId: String) : BusinessInfoIntent()

    data class ShowEditForm(val business: MonitoredBusinessBasicInfo, val params: BusinessInfoRawFormParams?) : BusinessInfoIntent()
    data class SendEditForm(val business: MonitoredBusinessBasicInfo, val params: BusinessInfoRawFormParams) : BusinessInfoIntent()
}
