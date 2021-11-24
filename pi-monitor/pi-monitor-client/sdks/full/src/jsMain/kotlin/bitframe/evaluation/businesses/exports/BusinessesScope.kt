@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.evaluation.businesses.exports

import bitframe.client.PiMonitorService
import bitframe.client.evaluation.businesses.BusinessesService
import bitframe.evaluation.businesses.BusinessViewModel
import bitframe.monitored.MonitoredBusiness
import useEventHandler
import viewmodel.ViewModel
import bitframe.evaluation.businesses.BusinessesIntent as Intent
import bitframe.evaluation.businesses.BusinessesState as State

class BusinessesScope(val service: PiMonitorService) {
    val viewModel: ViewModel<Intent, State> by lazy { BusinessViewModel(service.businesses) }

    val loadBusinesses: () -> Unit = { viewModel.post(Intent.LoadBusinesses) }

    val showCreateBusinessForm: () -> Unit = { viewModel.post(Intent.ShowBusinessForm) }

    val useBusinessAddedEvent: (callback: (MonitoredBusiness) -> Unit) -> Unit = { callback ->
        useEventHandler(service.signIn.config.bus, BusinessesService.CREATE_BUSINESS_EVENT_ID, callback)
    }
}