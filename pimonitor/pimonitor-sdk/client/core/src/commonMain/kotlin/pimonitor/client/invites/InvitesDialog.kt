@file:JsExport

package pimonitor.client.invites

import pimonitor.client.invites.fields.SageOneAcceptInviteFields
import pimonitor.core.sage.AcceptSageOneInviteRawParams
import presenters.modal.builders.FormDialogBuildingBlock
import presenters.modal.formDialog
import kotlin.js.JsExport

object InvitesDialog {
    val SageIntegration = "Sage Integration"

    internal fun sageIntegrationDialog(
        block: FormDialogBuildingBlock<AcceptSageOneInviteRawParams>
    ) = formDialog(
        heading = SageIntegration,
        details = "Share your financial information by integrating PiMonitor directly to sage",
        fields = SageOneAcceptInviteFields(),
        block
    )
}