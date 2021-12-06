@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.evaluation.businesses.exports

import pimonitor.client.PiMonitorService
import pimonitor.client.evaluation.businesses.BusinessesService
import pimonitor.evaluation.businesses.BusinessViewModel
import pimonitor.monitored.MonitoredBusiness
import useEventHandler
import useViewModelState
import viewmodel.ViewModel
import pimonitor.evaluation.businesses.BusinessesIntent as Intent
import pimonitor.evaluation.businesses.BusinessesState as State

class BusinessesScope(val service: PiMonitorService) {
    val viewModel: ViewModel<Intent, State> by lazy { BusinessViewModel(service.businesses) }

    val loadBusinesses: () -> Unit = { viewModel.post(Intent.LoadBusinesses) }

    val showCreateBusinessForm: () -> Unit = { viewModel.post(Intent.ShowBusinessForm) }

    val useBusinessAddedEvent: (callback: (MonitoredBusiness) -> Unit) -> Unit = { callback ->
        useEventHandler(service.signIn.config.bus, BusinessesService.CREATE_BUSINESS_EVENT_ID, callback)
    }

    val useStateFromViewModel = { useViewModelState(viewModel) }
}