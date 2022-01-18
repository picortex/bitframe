@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.evaluation.businesses.exports

import bitframe.client.UIScope
import pimonitor.PiMonitorViewModelConfig
import pimonitor.api.PiMonitorService
import pimonitor.evaluation.businesses.forms.CreateBusinessViewModel
import viewmodel.ViewModel
import pimonitor.evaluation.businesses.forms.CreateBusinessIntent as Intent
import pimonitor.evaluation.businesses.forms.CreateBusinessState as State

open class CreateBusinessScope(config: PiMonitorViewModelConfig) : UIScope<Intent, State> {
    override val service: PiMonitorService = config.service
    override val viewModel: ViewModel<Intent, State> by lazy { CreateBusinessViewModel(config) }

    val showForm = { uid: String? -> viewModel.post(Intent.ShowForm(uid)) }

    val submitForm = { params: CreateBusinessFormParams ->
        viewModel.post(Intent.SubmitForm(params.toParams()))
    }
}