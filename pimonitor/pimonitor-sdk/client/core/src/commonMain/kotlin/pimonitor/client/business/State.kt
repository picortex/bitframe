@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business

import pimonitor.core.dashboards.OperationalDashboard
import presenters.feedbacks.Feedback
import kotlin.js.JsExport

data class State(
    val status: Feedback = Feedback.None,
    val operationDashboard: OperationalDashboard? = null
)