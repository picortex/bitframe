@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses.forms

import presenters.feedbacks.Feedback
import kotlin.js.JsExport

data class CreateBusinessState(
    val status: Feedback = Feedback.None,
    val fields: CreateBusinessFields = CreateBusinessFields()
)
