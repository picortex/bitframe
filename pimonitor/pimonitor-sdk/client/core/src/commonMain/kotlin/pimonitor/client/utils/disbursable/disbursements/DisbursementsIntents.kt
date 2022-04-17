@file:JsExport

package pimonitor.client.utils.disbursable.disbursements

import viewmodel.ViewModel
import kotlin.js.JsExport

class DisbursementsIntents internal constructor(
    private val viewModel: ViewModel<DisbursementsIntent, *>
) {
    val loadDisbursable = { investmentId: String ->
        viewModel.post(DisbursementsIntent.LoadDisbursements(investmentId))
    }
}