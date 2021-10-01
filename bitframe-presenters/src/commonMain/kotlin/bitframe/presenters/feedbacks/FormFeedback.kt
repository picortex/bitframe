@file:JsExport

package bitframe.presenters.feedbacks

import kotlin.js.JsExport

sealed class FormFeedback(open val message: String) {
    data class Loading(override val message: String) : FormFeedback(message)

    data class Failure(
        val cause: Throwable,
        override val message: String = cause.message ?: "Unknown failure"
    ) : FormFeedback(message)

    data class Success(override val message: String) : FormFeedback(message)
}