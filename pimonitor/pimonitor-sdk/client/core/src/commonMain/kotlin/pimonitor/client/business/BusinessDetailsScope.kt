@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business

import bitframe.client.UIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.businesses.BusinessesService
import kotlin.js.JsExport
import pimonitor.client.business.State as State
import pimonitor.client.business.Intent as Intent

open class BusinessDetailsScope(
    override val config: UIScopeConfig<BusinessesService>
) : UIScope<Intent, State> {
    override val viewModel by lazy { BusinessDetailsViewModel(config) }

    val loadOperationalDashboard = { businessId: String ->
        viewModel.post(Intent.LoadOperationDashboard(businessId))
    }

    val loadFinancialDashboard = { businessId: String ->
        viewModel.post(Intent.LoadIncomeStatement(businessId))
    }

    val loadBalanceSheet = { businessId: String ->
        viewModel.post(Intent.LoadBalanceSheet(businessId))
    }
}