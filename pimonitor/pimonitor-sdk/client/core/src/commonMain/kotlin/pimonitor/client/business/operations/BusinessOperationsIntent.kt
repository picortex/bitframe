package pimonitor.client.business.operations

import pimonitor.core.business.utils.params.LoadReportRawParams

sealed class BusinessOperationsIntent {
    data class LoadOperationalDashboard(val params: LoadReportRawParams) : BusinessOperationsIntent()
}