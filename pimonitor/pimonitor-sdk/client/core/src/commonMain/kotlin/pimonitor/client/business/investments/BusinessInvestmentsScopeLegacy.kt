@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.investments

import bitframe.client.UIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.PiMonitorApi
import pimonitor.client.business.investments.params.InvestmentsRawParams
import pimonitor.core.investments.Investment
import pimonitor.core.utils.disbursements.params.DisbursementRawParams
import presenters.cases.CrowdState
import kotlin.js.JsExport
import pimonitor.client.business.investments.BusinessInvestmentsIntent as Intent

open class BusinessInvestmentsScopeLegacy(
    override val config: UIScopeConfig<PiMonitorApi>
) : UIScope<CrowdState<Investment>> {
    override val viewModel by lazy { BusinessInvestmentsViewModel(config) }

    val loadAllInvestment = { businessId: String ->
        viewModel.post(Intent.LoadAllInvestments(businessId))
    }

    val showCreateInvestmentForm = { businessId: String ->
        viewModel.post(Intent.ShowCreateInvestmentForm(businessId))
    }

    val sendCreateInvestmentForm = { params: InvestmentsRawParams ->
        viewModel.post(Intent.SendCreateInvestmentForm(params))
    }

    val showCreateDisbursementForm = { investment: Investment ->
        viewModel.post(Intent.ShowCreateDisbursementForm(investment))
    }

    val sendCreateDisbursementForm = { params: DisbursementRawParams ->
        viewModel.post(Intent.SendCreateDisbursementForm(params))
    }

    val exitDialog = { viewModel.post(Intent.ExitDialog) }
}