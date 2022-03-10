@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.invites

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.PiMonitorApi
import viewmodel.asState
import pimonitor.client.invites.InvitesIntent as Intent
import pimonitor.client.invites.InvitesState as State

class InvitesReactScope(
    override val config: UIScopeConfig<PiMonitorApi>
) : InvitesScope(config), ReactUIScope<Intent, State> {
    override val useScopeState: () -> State = { viewModel.asState() }
}