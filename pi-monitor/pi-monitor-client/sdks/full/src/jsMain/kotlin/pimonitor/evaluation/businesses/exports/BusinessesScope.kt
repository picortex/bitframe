@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.evaluation.businesses.exports

import pimonitor.PiMonitorService
import pimonitor.evaluation.business.BusinessViewModel
import pimonitor.evaluation.businesses.BusinessesService
import pimonitor.monitored.MonitoredBusiness
import useEventHandler
import viewmodel.ViewModel
import pimonitor.evaluation.business.BusinessesIntent as Intent
import pimonitor.evaluation.business.BusinessesState as State

class BusinessesScope(val service: PiMonitorService) {
    val viewModel: ViewModel<Intent, State> by lazy { BusinessViewModel(service.businesses) }

    val loadBusinesses: () -> Unit = { viewModel.post(Intent.LoadBusinesses) }

    val showCreateBusinessForm: () -> Unit = { viewModel.post(Intent.ShowBusinessForm) }

    val useBusinessAddedEvent: (callback: (MonitoredBusiness) -> Unit) -> Unit = { callback ->
        useEventHandler(service.bus, BusinessesService.CREATE_BUSINESS_EVENT_ID, callback)
    }
}