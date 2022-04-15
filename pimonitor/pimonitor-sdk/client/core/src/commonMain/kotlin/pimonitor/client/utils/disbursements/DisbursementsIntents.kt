@file:JsExport

package pimonitor.client.utils.disbursements

import viewmodel.ViewModel
import kotlin.js.JsExport

class DisbursementsIntents internal constructor(
    private val viewModel: ViewModel<DisbursementsIntent, *>
) {
    val loadInvestment = { investmentId: String ->
        viewModel.post(DisbursementsIntent.LoadDisbursements(investmentId))
    }
}