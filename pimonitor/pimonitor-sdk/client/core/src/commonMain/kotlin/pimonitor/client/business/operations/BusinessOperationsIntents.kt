@file:JsExport

package pimonitor.client.business.operations

import pimonitor.core.business.utils.info.LoadInfoRawParams
import viewmodel.ViewModel
import kotlin.js.JsExport

class BusinessOperationsIntents internal constructor(
    private val viewModel: ViewModel<BusinessOperationsIntent, *>
) {
    val load = { params: LoadInfoRawParams ->
        viewModel.post(BusinessOperationsIntent.LoadOperationalDashboard(params))
    }
}