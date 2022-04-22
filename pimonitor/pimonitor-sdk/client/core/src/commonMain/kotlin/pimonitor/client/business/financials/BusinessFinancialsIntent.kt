package pimonitor.client.business.financials

sealed class BusinessFinancialsIntent {
    data class LoadAvailableReports(val businessId: String) : BusinessFinancialsIntent()
    data class LoadIncomeStatement(val businessId: String) : BusinessFinancialsIntent()
    data class LoadBalanceSheet(val businessId: String) : BusinessFinancialsIntent()
}