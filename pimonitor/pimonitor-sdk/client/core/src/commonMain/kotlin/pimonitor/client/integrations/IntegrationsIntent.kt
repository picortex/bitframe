package pimonitor.client.integrations

import pimonitor.core.sage.AcceptSageOneInviteRawParams

sealed class IntegrationsIntent {
    data class LoadIntegrationInfo(val inviteId: String) : IntegrationsIntent()
    object ShowSageOneInviteForm : IntegrationsIntent()
    object ExitDialog : IntegrationsIntent()
    data class SubmitSageOneInviteForm(val params: AcceptSageOneInviteRawParams) : IntegrationsIntent()
}