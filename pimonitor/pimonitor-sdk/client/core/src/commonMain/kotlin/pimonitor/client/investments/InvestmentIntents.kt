@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.investments

import pimonitor.client.business.utils.disbursements.DisbursementRawFormParams
import pimonitor.core.investments.InvestmentSummary
import viewmodel.ViewModel
import kotlin.js.JsExport

class InvestmentIntents(private val vm: ViewModel<InvestmentIntent, *>) {
    val loadAllIntents = { vm.post(InvestmentIntent.LoadAllInvestments) }

    val showDisbursementForm = { investment: InvestmentSummary ->
        vm.post(InvestmentIntent.ShowDisbursementForm(investment, null))
    }

    val sendDisbursementForm = { investment: InvestmentSummary, params: DisbursementRawFormParams ->
        vm.post(InvestmentIntent.SendDisbursementForm(investment, params))
    }
}