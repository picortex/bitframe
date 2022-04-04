@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.info

import pimonitor.core.business.info.params.BusinessInfoRawFormParams
import viewmodel.ViewModel
import kotlin.js.JsExport
import pimonitor.client.business.info.BusinessInfoIntent as Intent

class BusinessInfoIntents(private val viewModel: ViewModel<Intent, *>) {
    val loadInfo = { businessId: String ->
        viewModel.post(Intent.LoadInfo(businessId))
    }

    val showEditForm = { businessId: String ->
        viewModel.post(Intent.ShowEditForm(businessId))
    }

    val sendEditForm = { params: BusinessInfoRawFormParams ->
        viewModel.post(Intent.SendEditForm(params))
    }
}