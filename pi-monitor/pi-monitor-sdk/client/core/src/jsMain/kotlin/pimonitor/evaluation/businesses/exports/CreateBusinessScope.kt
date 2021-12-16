@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.evaluation.businesses.exports

import pimonitor.api.PiMonitorService
import pimonitor.evaluation.businesses.forms.CreateBusinessViewModel
import useViewModelState
import viewmodel.ViewModel
import pimonitor.evaluation.businesses.forms.CreateBusinessIntent as Intent
import pimonitor.evaluation.businesses.forms.CreateBusinessState as State

class CreateBusinessScope(service: PiMonitorService) {
    val viewModel: ViewModel<Intent, State> by lazy { CreateBusinessViewModel(service.monitors, service.businesses) }
    val showForm = { uid: String? -> viewModel.post(Intent.ShowForm(uid)) }

    val submitForm = { params: CreateBusinessFormParams ->
        viewModel.post(Intent.SubmitForm(params.toParams()))
    }

    val useStateFromViewModel = { useViewModelState(viewModel) }
}