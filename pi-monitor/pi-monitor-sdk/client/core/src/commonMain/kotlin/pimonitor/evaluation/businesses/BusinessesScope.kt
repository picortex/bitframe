@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.evaluation.businesses

import pimonitor.PiMonitorScopeConfig
import pimonitor.api.PiMonitorApi
import viewmodel.ViewModel
import kotlin.js.JsExport
import pimonitor.evaluation.businesses.BusinessesIntent as Intent
import pimonitor.evaluation.businesses.BusinessesState as State

open class BusinessesScope(config: PiMonitorScopeConfig) : UIScope<Intent, State> {

    override val api: PiMonitorApi = config.api

    override val viewModel: ViewModel<Intent, State> by lazy { BusinessViewModel(config) }

    val loadBusinesses: () -> Unit = { viewModel.post(Intent.LoadBusinesses) }

    val exitDialog: () -> Unit = { viewModel.post(Intent.ExitDialog) }

    val showCreateBusinessForm: () -> Unit = { viewModel.post(Intent.ShowCreateBusinessForm) }
}