@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.invites

import bitframe.client.UIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.PiMonitorApi
import pimonitor.core.sage.AcceptSageOneInviteRawFormParams
import kotlin.js.JsExport
import pimonitor.client.invites.InvitesIntent as Intent
import pimonitor.client.invites.InvitesState as State

open class InvitesScope(
    override val config: UIScopeConfig<PiMonitorApi>
) : UIScope<State> {
    override val viewModel by lazy { InvitesViewModel(config) }

    val loadInviteInfo = { inviteId: String ->
        viewModel.post(Intent.LoadIntegrationInfo(inviteId))
    }

    val showSageInvite = { viewModel.post(Intent.ShowSageOneInviteForm) }

    val acceptSageInvite = { params: AcceptSageOneInviteRawFormParams ->
        viewModel.post(Intent.SubmitSageOneInviteForm(params))
    }

    val exitDialog = { viewModel.post(Intent.ExitDialog) }
}