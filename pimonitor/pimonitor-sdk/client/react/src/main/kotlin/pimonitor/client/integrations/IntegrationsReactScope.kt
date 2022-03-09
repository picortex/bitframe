@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.integrations

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.PiMonitorApi
import viewmodel.asState
import pimonitor.client.integrations.IntegrationsIntent as Intent
import pimonitor.client.integrations.IntegrationsState as State

class IntegrationsReactScope(
    override val config: UIScopeConfig<PiMonitorApi>
) : IntegrationsScope(config), ReactUIScope<Intent, State> {
    override val useScopeState: () -> State = { viewModel.asState() }
}