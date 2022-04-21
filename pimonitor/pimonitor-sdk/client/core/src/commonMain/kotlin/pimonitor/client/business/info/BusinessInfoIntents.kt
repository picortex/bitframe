@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.info

import viewmodel.ViewModel
import kotlin.js.JsExport
import pimonitor.client.business.info.BusinessInfoIntent as Intent

class BusinessInfoIntents(private val viewModel: ViewModel<Intent, *>) {
    val loadBusinessInfo = { businessId: String ->
        viewModel.post(Intent.LoadInfo(businessId))
    }
}