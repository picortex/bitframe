@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses

import bitframe.client.UIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.PiMonitorApi
import pimonitor.client.businesses.forms.CreateBusinessViewModel
import viewmodel.ViewModel
import kotlin.js.JsExport
import pimonitor.client.businesses.forms.CreateBusinessIntent as Intent
import pimonitor.client.businesses.forms.CreateBusinessState as State

open class CreateBusinessScope(
    override val config: UIScopeConfig<PiMonitorApi>
) : UIScope<Intent, State> {
    override val viewModel: ViewModel<Intent, State> by lazy { CreateBusinessViewModel(config) }

    val showForm = { uid: String? -> viewModel.post(Intent.ShowForm(uid)) }

    val submitForm = { params: RawCreateBusinessParams ->
        viewModel.post(Intent.SubmitForm(params.toValidatedCreateBusinessParams()))
    }
}