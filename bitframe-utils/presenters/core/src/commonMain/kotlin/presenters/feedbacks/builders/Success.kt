package presenters.feedbacks.builders

import presenters.feedbacks.FormFeedback

internal fun makeSuccess(message: String) = object : FormFeedback.Success {
    override val message = message
    override val success = true

    override fun equals(other: Any?): Boolean {
        val o = other as? FormFeedback.Success ?: return false
        return message == o.message
    }

    override fun hashCode(): Int = message.hashCode()

    override fun toString(): String = "Success(message=$message)"
}