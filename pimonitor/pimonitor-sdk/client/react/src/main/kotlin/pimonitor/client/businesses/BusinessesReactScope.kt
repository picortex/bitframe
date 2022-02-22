@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.PiMonitorApi
import pimonitor.core.businesses.BusinessesServiceCore
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import useEventHandler
import viewmodel.asState
import pimonitor.client.businesses.BusinessesIntent as Intent
import pimonitor.client.businesses.BusinessesState as State

class BusinessesReactScope internal constructor(
    override val config: UIScopeConfig<PiMonitorApi>
) : BusinessesScope(config), ReactUIScope<Intent, State> {
    override val useScopeState: () -> State = { viewModel.asState() }
    private val bus get() = config.service.config.bus
    val useBusinessAddedEvent: (callback: (CreateMonitoredBusinessParams) -> Unit) -> Unit = { callback ->
        useEventHandler(bus, BusinessesServiceCore.CREATE_BUSINESS_EVENT_TOPIC, callback)
    }
}