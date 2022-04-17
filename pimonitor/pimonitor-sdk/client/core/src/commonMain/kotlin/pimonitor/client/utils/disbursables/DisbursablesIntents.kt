@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.utils.disbursables

import pimonitor.core.investments.InvestmentSummary
import pimonitor.core.utils.disbursables.disbursements.params.DisbursementRawParams
import viewmodel.ViewModel
import kotlin.js.JsExport

class DisbursablesIntents(private val vm: ViewModel<DisbursablesIntent, *>) {
    val loadAllInvestments = { businessId: String? -> vm.post(DisbursablesIntent.LoadAllDisbursables(businessId)) }

    val showDisbursementForm = { investment: InvestmentSummary ->
        vm.post(DisbursablesIntent.ShowDisbursementForm(investment, null))
    }

    val sendDisbursementForm = { investment: InvestmentSummary, params: DisbursementRawParams ->
        vm.post(DisbursablesIntent.SendDisbursementForm(investment, params))
    }
}