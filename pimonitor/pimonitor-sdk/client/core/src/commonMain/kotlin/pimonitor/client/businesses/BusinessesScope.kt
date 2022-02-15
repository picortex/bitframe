@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses

import bitframe.client.UIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.PiMonitorApi
import viewmodel.ViewModel
import kotlin.js.JsExport
import pimonitor.client.businesses.BusinessesIntent as Intent
import pimonitor.client.businesses.BusinessesState as State

open class BusinessesScope(
    override val config: UIScopeConfig<PiMonitorApi>
) : UIScope<Intent, State> {

    override val viewModel: ViewModel<Intent, State> by lazy { BusinessViewModel(config) }

    val loadBusinesses: () -> Unit = { viewModel.post(Intent.LoadBusinesses) }

    val exitDialog: () -> Unit = { viewModel.post(Intent.ExitDialog) }

    val showCreateBusinessForm: () -> Unit = { viewModel.post(Intent.ShowCreateBusinessForm) }
}