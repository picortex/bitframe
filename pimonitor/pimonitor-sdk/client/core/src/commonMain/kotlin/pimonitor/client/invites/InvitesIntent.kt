package pimonitor.client.invites

import pimonitor.core.sage.AcceptSageOneInviteRawFormParams

sealed class InvitesIntent {
    data class LoadIntegrationInfo(val inviteId: String) : InvitesIntent()
    object ShowSageOneInviteForm : InvitesIntent()
    data class SubmitSageOneInviteForm(val params: AcceptSageOneInviteRawFormParams) : InvitesIntent()
    object ExitDialog : InvitesIntent()
}