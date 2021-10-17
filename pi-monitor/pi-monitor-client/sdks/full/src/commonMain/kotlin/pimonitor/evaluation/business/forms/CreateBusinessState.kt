@file:JsExport

package pimonitor.evaluation.business.forms

import bitframe.presenters.feedbacks.FormFeedback
import kotlin.js.JsExport

sealed class CreateBusinessState {
    data class Loading(val message: String) : CreateBusinessState()
    data class Form(val fields: CreateBusinessFields, val status: FormFeedback?) : CreateBusinessState()
    data class Failure(val cause: Throwable, val message: String = cause.message ?: "Unknown error") : CreateBusinessState()
}
