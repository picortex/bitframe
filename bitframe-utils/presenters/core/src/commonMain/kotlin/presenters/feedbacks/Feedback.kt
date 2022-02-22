@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package presenters.feedbacks

import kotlin.js.JsExport

sealed class Feedback {
    abstract val message: String

    data class Loading(
        override val message: String
    ) : Feedback() {
        val loading: Boolean = true
    }

    data class Failure(
        val cause: Throwable? = null,
        override val message: String = cause?.message ?: "Unknown error"
    ) : Feedback() {
        val failure: Boolean = true
    }

    data class Success(
        override val message: String = "Success"
    ) : Feedback() {
        val success = true
    }
}