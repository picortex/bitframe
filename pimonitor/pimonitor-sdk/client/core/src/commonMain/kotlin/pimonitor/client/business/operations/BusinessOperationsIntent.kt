package pimonitor.client.business.operations

sealed class BusinessOperationsIntent {
    data class LoadOperationalDashboard(val businessId: String) : BusinessOperationsIntent()
}