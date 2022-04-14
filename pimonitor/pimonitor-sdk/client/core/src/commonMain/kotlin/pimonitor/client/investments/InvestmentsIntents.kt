@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.investments

import pimonitor.core.investments.InvestmentSummary
import pimonitor.core.utils.disbursements.params.DisbursementRawParams
import viewmodel.ViewModel
import kotlin.js.JsExport

class InvestmentsIntents(private val vm: ViewModel<InvestmentsIntent, *>) {
    val loadAllInvestments = { businessId: String? -> vm.post(InvestmentsIntent.LoadAllInvestments(businessId)) }

    val showDisbursementForm = { investment: InvestmentSummary ->
        vm.post(InvestmentsIntent.ShowDisbursementForm(investment, null))
    }

    val sendDisbursementForm = { investment: InvestmentSummary, params: DisbursementRawParams ->
        vm.post(InvestmentsIntent.SendDisbursementForm(investment, params))
    }
}