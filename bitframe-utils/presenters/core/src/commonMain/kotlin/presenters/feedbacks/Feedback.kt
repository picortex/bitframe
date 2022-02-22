@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package presenters.feedbacks

import kotlin.js.JsExport

sealed class Feedback {
    abstract val message: String

    class Loading(
        override val message: String
    ) : Feedback() {
        val loading: Boolean = true
    }

    class Failure(
        val cause: Throwable? = null,
        override val message: String = cause?.message ?: "Unknown error"
    ) : Feedback() {
        val failure: Boolean = true
    }

    class Success(
        override val message: String = "Success"
    ) : Feedback() {
        val success = true
    }

    object None : Feedback() {
        override val message = "No Feedback"
        override fun toString(): String = "NoFeedback"
    }

    override fun hashCode(): Int = message.hashCode()

    override fun equals(other: Any?): Boolean = when (other) {
        is Feedback -> other.message == message && this::class == other::class
        else -> false
    }

    override fun toString(): String = "${this::class.simpleName}(message=$message)"
}