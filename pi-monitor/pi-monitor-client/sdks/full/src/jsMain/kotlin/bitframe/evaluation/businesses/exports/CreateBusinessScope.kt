@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.evaluation.businesses.exports

import bitframe.client.PiMonitorService
import bitframe.evaluation.businesses.forms.CreateBusinessViewModel
import viewmodel.ViewModel
import bitframe.evaluation.businesses.forms.CreateBusinessIntent as Intent
import bitframe.evaluation.businesses.forms.CreateBusinessState as State

class CreateBusinessScope(service: PiMonitorService) {
    val viewModel: ViewModel<Intent, State> by lazy { CreateBusinessViewModel(service.monitors, service.businesses) }
    val showForm = { uid: String? -> viewModel.post(Intent.ShowForm(uid)) }

    val submitForm = { params: CreateBusinessFormParams ->
        viewModel.post(Intent.SubmitForm(params.toParams()))
    }
}