package pimonitor.client.business.financials

sealed class BusinessFinancialIntent {
    data class LoadAvailableReports(val businessId: String) : BusinessFinancialIntent()
    data class LoadIncomeStatement(val businessId: String) : BusinessFinancialIntent()
    data class LoadBalanceSheet(val businessId: String) : BusinessFinancialIntent()
}