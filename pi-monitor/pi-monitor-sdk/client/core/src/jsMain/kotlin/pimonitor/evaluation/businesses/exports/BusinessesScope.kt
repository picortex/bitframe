@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.evaluation.businesses.exports

import bitframe.client.UIScope
import pimonitor.PiMonitorViewModelConfig
import pimonitor.api.PiMonitorService
import pimonitor.evaluation.businesses.BusinessViewModel
import viewmodel.ViewModel
import pimonitor.evaluation.businesses.BusinessesIntent as Intent
import pimonitor.evaluation.businesses.BusinessesState as State

open class BusinessesScope(config: PiMonitorViewModelConfig) : UIScope<Intent, State> {
    override val viewModel: ViewModel<Intent, State> by lazy { BusinessViewModel(config) }

    val loadBusinesses: () -> Unit = { viewModel.post(Intent.LoadBusinesses) }

    val exitDialog: () -> Unit = { viewModel.post(Intent.ExitDialog) }

    val showCreateBusinessForm: () -> Unit = { viewModel.post(Intent.ShowCreateBusinessForm) }
}