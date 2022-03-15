package pimonitor.client.business

sealed class Intent {
    data class LoadOperationDashboard(val businessId: String) : Intent()
    data class LoadIncomeStatement(val businessId: String) : Intent()
}