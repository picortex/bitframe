@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.evaluation.businesses.exports

import pimonitor.PiMonitorScopeConfig
import pimonitor.evaluation.businesses.BusinessesScope
import pimonitor.evaluation.businesses.BusinessesService
import pimonitor.monitored.MonitoredBusiness
import useEventHandler
import useViewModelState
import pimonitor.evaluation.businesses.BusinessesIntent as Intent
import pimonitor.evaluation.businesses.BusinessesState as State

open class BusinessesReactScope internal constructor(
    private val config: PiMonitorScopeConfig
) : BusinessesScope(config), ReactUIScope<Intent, State> {

    override val useStateFromViewModel: () -> State = {
        useViewModelState(viewModel)
    }

    val useBusinessAddedEvent: (callback: (MonitoredBusiness) -> Unit) -> Unit = { callback ->
        useEventHandler(config.bus, BusinessesService.CREATE_BUSINESS_EVENT_TOPIC, callback)
    }
}