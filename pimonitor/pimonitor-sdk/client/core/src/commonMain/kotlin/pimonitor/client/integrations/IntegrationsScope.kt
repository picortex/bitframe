@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.integrations

import bitframe.client.UIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.PiMonitorApi
import pimonitor.core.sage.AcceptSageOneInviteRawParams
import kotlin.js.JsExport
import pimonitor.client.integrations.IntegrationsIntent as Intent
import pimonitor.client.integrations.IntegrationsState as State

open class IntegrationsScope(
    override val config: UIScopeConfig<PiMonitorApi>
) : UIScope<Intent, State> {
    override val viewModel by lazy { IntegrationsViewModel(config) }

    val loadInviteInfo = { inviteId: String ->
        viewModel.post(Intent.LoadIntegrationInfo(inviteId))
    }

    val exitDialog = {
        viewModel.post(Intent.ExitDialog)
    }

    val acceptSageInvite = { params: AcceptSageOneInviteRawParams ->
        viewModel.post(Intent.SubmitSageOneInviteForm(params))
    }
}