@file:JsExport

package pimonitor.client.businesses.forms

import presenters.feedbacks.Feedback
import kotlin.js.JsExport

sealed class CreateBusinessState {
    data class Loading(
        val message: String
    ) : CreateBusinessState() {
        val loading = true
    }

    data class Form(
        val fields: CreateBusinessFields,
        val status: Feedback?
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
