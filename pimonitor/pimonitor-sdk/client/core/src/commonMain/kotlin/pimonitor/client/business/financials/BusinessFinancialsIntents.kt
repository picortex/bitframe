@file:JsExport

package pimonitor.client.business.financials

import viewmodel.ViewModel
import kotlin.js.JsExport

class BusinessFinancialsIntents internal constructor(
    private val viewModel: ViewModel<BusinessFinancialsIntent, *>
) {
    val loadAvailableReports = { businessId: String ->
        viewModel.post(BusinessFinancialsIntent.LoadAvailableReports(businessId))
    }

    val loadIncomeStatement = { businessId: String ->
        viewModel.post(BusinessFinancialsIntent.LoadIncomeStatement(businessId))
    }

    val loadBalanceString = { businessId: String ->
        viewModel.post(BusinessFinancialsIntent.LoadBalanceSheet(businessId))
    }
}