@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.PiMonitorApi
import pimonitor.core.businesses.BusinessesService
import pimonitor.core.monitored.MonitoredBusiness
import useEventHandler
import useViewModelState
import pimonitor.client.businesses.BusinessesIntent as Intent
import pimonitor.client.businesses.BusinessesState as State

@JsExport
class BusinessesReactScope internal constructor(
    override val config: UIScopeConfig<PiMonitorApi>
) : BusinessesScope(config), ReactUIScope<Intent, State> {

    override val useScopeState: () -> State = {
        useViewModelState(viewModel)
    }

    private val bus get() = config.service.config.bus
    val useBusinessAddedEvent: (callback: (MonitoredBusiness) -> Unit) -> Unit = { callback ->
        useEventHandler(bus, BusinessesService.CREATE_BUSINESS_EVENT_TOPIC, callback)
    }
}