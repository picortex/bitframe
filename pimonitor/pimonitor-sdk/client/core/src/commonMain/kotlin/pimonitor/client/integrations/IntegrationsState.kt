@file:JsExport

package pimonitor.client.integrations

import presenters.feedbacks.Feedback
import presenters.modal.Dialog
import kotlin.js.JsExport

data class IntegrationsState(
    val status: Feedback = Feedback.Loading("Preparing integration information, please wait . . ."),
    val info: String? = null,
    val dialog: Dialog? = null
)