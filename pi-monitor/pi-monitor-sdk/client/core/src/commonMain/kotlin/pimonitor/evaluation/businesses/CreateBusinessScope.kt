@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.evaluation.businesses

import pimonitor.PiMonitorScopeConfig
import pimonitor.api.PiMonitorApi
import pimonitor.api.businesses.RawCreateBusinessFormParams
import pimonitor.api.businesses.toParams
import pimonitor.evaluation.businesses.forms.CreateBusinessViewModel
import viewmodel.ViewModel
import kotlin.js.JsExport
import pimonitor.evaluation.businesses.forms.CreateBusinessIntent as Intent
import pimonitor.evaluation.businesses.forms.CreateBusinessState as State

open class CreateBusinessScope(config: PiMonitorScopeConfig) : UIScope<Intent, State> {
    override val api: PiMonitorApi = config.api
    override val viewModel: ViewModel<Intent, State> by lazy { CreateBusinessViewModel(config) }

    val showForm = { uid: String? -> viewModel.post(Intent.ShowForm(uid)) }

    val submitForm = { params: RawCreateBusinessFormParams ->
        viewModel.post(Intent.SubmitForm(params.toParams()))
    }
}