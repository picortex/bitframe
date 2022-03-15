package pimonitor.client.business

sealed class Intent {
    data class LoadOperationDashboard(val businessId: String) : Intent()
    data class LoadFinancialDashboard(val businessId: String) : Intent()
}