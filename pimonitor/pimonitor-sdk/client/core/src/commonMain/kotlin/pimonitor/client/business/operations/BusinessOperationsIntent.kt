package pimonitor.client.business.operations

import pimonitor.core.business.utils.info.LoadInfoRawParams

sealed class BusinessOperationsIntent {
    data class LoadOperationalDashboard(val params: LoadInfoRawParams) : BusinessOperationsIntent()
}