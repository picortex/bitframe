@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.investments

import pimonitor.core.investments.InvestmentSummary
import pimonitor.core.utils.disbursements.params.DisbursementRawParams
import viewmodel.ViewModel
import kotlin.js.JsExport

class InvestmentIntents(private val vm: ViewModel<InvestmentIntent, *>) {
    val loadAllInvestments = { businessId: String? -> vm.post(InvestmentIntent.LoadAllInvestments(businessId)) }

    val showDisbursementForm = { investment: InvestmentSummary ->
        vm.post(InvestmentIntent.ShowDisbursementForm(investment, null))
    }

    val sendDisbursementForm = { investment: InvestmentSummary, params: DisbursementRawParams ->
        vm.post(InvestmentIntent.SendDisbursementForm(investment, params))
    }
}