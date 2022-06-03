@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.invites

import pimonitor.core.invites.InviteInfo
import presenters.cases.Feedback
import presenters.modal.Dialog
import kotlin.js.JsExport

data class InvitesState(
    val status: Feedback = Feedback.Loading("Preparing integration information, please wait . . ."),
    val title: String? = null,
    val info: InviteInfo? = null,
    val dialog: Dialog<*, *>? = null
)