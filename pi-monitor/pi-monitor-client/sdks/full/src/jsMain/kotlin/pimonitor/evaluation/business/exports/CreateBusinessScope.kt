@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.evaluation.business.exports

import pimonitor.PiMonitorService
import pimonitor.evaluation.business.forms.CreateBusinessViewModel
import viewmodel.ViewModel
import pimonitor.evaluation.business.forms.CreateBusinessIntent as Intent
import pimonitor.evaluation.business.forms.CreateBusinessState as State

class CreateBusinessScope(service: PiMonitorService) {
    val viewModel: ViewModel<Intent, State> by lazy { CreateBusinessViewModel(service.monitors, service.businesses) }
    val showForm = { uid: String? -> viewModel.post(Intent.ShowForm(uid)) }

    val submitForm = { params: CreateBusinessFormParams ->
        viewModel.post(Intent.SubmitForm(params.toParams()))
    }
}