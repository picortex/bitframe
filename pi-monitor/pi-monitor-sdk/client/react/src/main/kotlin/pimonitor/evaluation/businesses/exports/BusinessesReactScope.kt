@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.evaluation.businesses.exports

import bitframe.client.ReactUIScope
import pimonitor.PiMonitorViewModelConfig
import pimonitor.api.PiMonitorService
import pimonitor.client.evaluation.businesses.BusinessesService
import pimonitor.evaluation.businesses.BusinessViewModel
import pimonitor.monitored.MonitoredBusiness
import useEventHandler
import useViewModelState
import viewmodel.ViewModel
import pimonitor.evaluation.businesses.BusinessesIntent as Intent
import pimonitor.evaluation.businesses.BusinessesState as State

open class BusinessesReactScope(
    private val config: PiMonitorViewModelConfig
) : BusinessesScope(config), ReactUIScope<Intent, State> {
    val service get() = config.service
    val useBusinessAddedEvent: (callback: (MonitoredBusiness) -> Unit) -> Unit = { callback ->
        useEventHandler(config.service.bus, BusinessesService.CREATE_BUSINESS_EVENT_ID, callback)
    }
}