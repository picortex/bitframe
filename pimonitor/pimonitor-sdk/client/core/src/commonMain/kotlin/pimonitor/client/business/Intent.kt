package pimonitor.client.business

sealed class Intent {
    data class LoadOperationDashboard(val businessId: String) : Intent()
}