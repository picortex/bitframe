package pimonitor.client.business.overview

import pimonitor.core.business.utils.info.LoadInfoRawParams

sealed class BusinessOverviewIntent {
    data class LoadOverview(val params: LoadInfoRawParams) : BusinessOverviewIntent()
}