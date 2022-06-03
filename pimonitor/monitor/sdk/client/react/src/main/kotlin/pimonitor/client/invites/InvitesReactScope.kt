@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.invites

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.MonitorApi
import viewmodel.asState
import pimonitor.client.invites.InvitesState as State

class InvitesReactScope(
    override val config: UIScopeConfig<MonitorApi>
) : InvitesScope(config), ReactUIScope<State> {
    override val useScopeState = { viewModel.asState() }
}