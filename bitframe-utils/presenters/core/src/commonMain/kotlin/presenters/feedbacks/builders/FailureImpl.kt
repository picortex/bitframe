package presenters.feedbacks.builders

import presenters.feedbacks.FormFeedback

internal data class FailureImpl(
    override val cause: Throwable,
    override val message: String = cause.message ?: FormFeedback.Failure.DEFAULT_MESSAGE
) : FormFeedback.Failure {
    override val failure: Boolean = true
}