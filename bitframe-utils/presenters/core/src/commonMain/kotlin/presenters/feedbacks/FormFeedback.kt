@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package presenters.feedbacks

import presenters.feedbacks.builders.makeFailure
import presenters.feedbacks.builders.makeLoading
import presenters.feedbacks.builders.makeSuccess
import kotlin.js.JsExport

sealed interface FormFeedback {
    val message: String

    interface Loading : FormFeedback {
        val loading: Boolean get() = true

        companion object {
            operator fun invoke(message: String) = makeLoading(message)
        }
    }

    interface Failure : FormFeedback {
        val cause: Throwable
        val failure: Boolean get() = true

        companion object {
            operator fun invoke(
                cause: Throwable,
                message: String? = null
            ): Failure = makeFailure(cause, message)
        }
    }

    interface Success : FormFeedback {
        val success: Boolean get() = true

        companion object {
            operator fun invoke(message: String) = makeSuccess(message)
        }
    }
}