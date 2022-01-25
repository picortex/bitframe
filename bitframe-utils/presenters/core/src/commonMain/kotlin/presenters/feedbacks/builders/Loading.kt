package presenters.feedbacks.builders

import presenters.feedbacks.FormFeedback

internal fun makeLoading(message: String) = object : FormFeedback.Loading {
    override val message = message
    override val loading = true

    override fun equals(other: Any?): Boolean {
        val o = other as? FormFeedback.Loading ?: return false
        return message == o.message
    }

    override fun hashCode(): Int = message.hashCode()

    override fun toString(): String = "Loading(message=$message)"
}