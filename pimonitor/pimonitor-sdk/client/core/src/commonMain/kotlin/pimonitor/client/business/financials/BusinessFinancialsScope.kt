@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.financials

import bitframe.client.UIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.businesses.BusinessesService
import kotlin.js.JsExport
import pimonitor.client.business.financials.BusinessFinancialIntent as Intent

open class BusinessFinancialsScope(
    override val config: UIScopeConfig<BusinessesService>
) : UIScope<BusinessFinancialsState> {
    override val viewModel by lazy { BusinessFinancialsViewModel(config) }

    val loadAvailableReports = { businessId: String ->
        viewModel.post(Intent.LoadAvailableReports(businessId))
    }

    val loadBalanceSheet = { businessId: String ->
        viewModel.post(Intent.LoadBalanceSheet(businessId))
    }

    val loadIncomeStatement = { businessId: String ->
        viewModel.post(Intent.LoadIncomeStatement(businessId))
    }
}