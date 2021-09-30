@file:JsExport

package bitframe.presenters.feedbacks

import kotlin.js.JsExport

sealed class FormFeedback {
    data class Loading(val message: String) : FormFeedback()

    data class Failure(val cause: Throwable, val message: String = cause.message ?: "Unknown failure") : FormFeedback()

    data class Success(val message: String) : FormFeedback()
}