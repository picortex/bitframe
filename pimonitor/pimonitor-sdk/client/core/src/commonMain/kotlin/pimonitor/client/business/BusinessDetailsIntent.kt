package pimonitor.client.business

sealed class BusinessDetailsIntent {
    data class LoadOperationDashboard(val businessId: String) : BusinessDetailsIntent()
    data class LoadIncomeStatement(val businessId: String) : BusinessDetailsIntent()
    data class LoadBalanceSheet(val businessId: String) : BusinessDetailsIntent()
}