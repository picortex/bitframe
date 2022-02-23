@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.PiMonitorApi
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import useEventHandler
import useViewModelState
import pimonitor.client.businesses.forms.CreateBusinessIntent as Intent
import pimonitor.client.businesses.forms.CreateBusinessState as State

class CreateBusinessReactScope internal constructor(
    override val config: UIScopeConfig<PiMonitorApi>
) : CreateBusinessScope(config), ReactUIScope<Intent, State> {
    override val useScopeState: () -> State = {
        useViewModelState(viewModel)
    }

    val useBusinessAddedEvent: (callback: (CreateMonitoredBusinessParams) -> Unit) -> Unit = { handler ->
        useEventHandler(
            bus = config.service.config.bus,
            topic = BusinessAddedEvent.topic(config.service.session.currentSpace?.uid ?: ""),
            callback = handler
        )
    }
}