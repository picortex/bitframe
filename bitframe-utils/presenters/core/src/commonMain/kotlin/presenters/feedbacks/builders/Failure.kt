package presenters.feedbacks.builders

import presenters.feedbacks.FormFeedback

internal fun makeFailure(cause: Throwable, message: String = cause.message ?: "Unknown error") = object : FormFeedback.Failure {
    override val cause = cause
    override val message = message

    override fun equals(other: Any?): Boolean {
        val o = other as? FormFeedback.Failure ?: return false
        return message == o.message && cause == o.cause
    }

    override fun hashCode(): Int = message.hashCode()

    override fun toString(): String = "Failure(message=$message)"
}