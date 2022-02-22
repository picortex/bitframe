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
        override fun hashCode(): Int = message.hashCode()
        override fun equals(other: Any?): Boolean = when (other) {
            is Loading -> other.message == message
            else -> false
        }

        override fun toString(): String = "Loading(message=$message)"
    }

    class Failure(
        val cause: Throwable? = null,
        override val message: String = cause?.message ?: "Unknown error"
    ) : Feedback() {
        val failure: Boolean = true
        override fun hashCode(): Int = message.hashCode()
        override fun equals(other: Any?): Boolean = when (other) {
            is Failure -> other.message == message
            else -> false
        }

        override fun toString(): String = "Failure(message=$message)"
    }

    class Success(
        override val message: String = "Success"
    ) : Feedback() {
        val success = true
        override fun hashCode(): Int = message.hashCode()

        override fun equals(other: Any?): Boolean = when (other) {
            is Success -> other.message == message
            else -> false
        }

        override fun toString(): String = "Success(message=$message)"
    }
}