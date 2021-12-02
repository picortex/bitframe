@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.evaluation.businesses.exports

import pimonitor.client.PiMonitorService
import pimonitor.evaluation.businesses.forms.CreateBusinessViewModel
import pimonitor.evaluation.businesses.exports.CreateBusinessFormParams
import pimonitor.evaluation.businesses.exports.toParams
import viewmodel.ViewModel
import pimonitor.evaluation.businesses.forms.CreateBusinessIntent as Intent
import pimonitor.evaluation.businesses.forms.CreateBusinessState as State

class CreateBusinessScope(service: PiMonitorService) {
    val viewModel: ViewModel<Intent, State> by lazy { CreateBusinessViewModel(service.monitors, service.businesses) }
    val showForm = { uid: String? -> viewModel.post(Intent.ShowForm(uid)) }

    val submitForm = { params: CreateBusinessFormParams ->
        viewModel.post(Intent.SubmitForm(params.toParams()))
    }
}