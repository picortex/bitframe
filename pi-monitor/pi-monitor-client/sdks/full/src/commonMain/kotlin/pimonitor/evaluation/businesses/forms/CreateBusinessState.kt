@file:JsExport

package pimonitor.evaluation.businesses.forms

import bitframe.presenters.feedbacks.FormFeedback
import kotlin.js.JsExport

sealed class CreateBusinessState {
    data class Loading(
        val message: String
    ) : CreateBusinessState() {
        val loading = true
    }

    data class Form(
        val fields: CreateBusinessFields,
        val status: FormFeedback?
    ) : CreateBusinessState()

    data class Failure(
        val cause: Throwable,
        val message: String = cause.message ?: "Unknown error"
    ) : CreateBusinessState() {
        val failure = true
    }

    data class Success(
        val message: String
    ) : CreateBusinessState() {
        val success = true
    }
}
