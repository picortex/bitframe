@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import useEventHandler
import viewmodel.asState
import pimonitor.client.businesses.BusinessesIntent as Intent
import pimonitor.client.businesses.BusinessesState as State

class BusinessesReactScope internal constructor(
    override val config: UIScopeConfig<BusinessesService>
) : BusinessesScope(config), ReactUIScope<Intent, State> {
    override val useScopeState: () -> State = { viewModel.asState() }
    val useBusinessAddedEvent: (callback: (CreateMonitoredBusinessParams) -> Unit) -> Unit = { handler ->
        val conf = config.service.config
        useEventHandler(
            bus = conf.bus,
            BusinessAddedEvent.topic(conf.session.value.space?.uid ?: ""),
            callback = handler
        )
    }
}