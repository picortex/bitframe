@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package presenters.feedbacks

import presenters.feedbacks.builders.FailureImpl
import presenters.feedbacks.builders.LoadingImpl
import presenters.feedbacks.builders.SuccessImpl
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.jvm.JvmStatic

sealed interface FormFeedback {
    val message: String

    interface Loading : FormFeedback {
        val loading: Boolean get() = true

        companion object {
            operator fun invoke(message: String): Loading = LoadingImpl(message)
        }
    }

    interface Failure : FormFeedback {
        val cause: Throwable
        val failure: Boolean get() = true

        companion object {
            @JvmStatic
            val DEFAULT_MESSAGE = "Unknown error"

            @JsName("_init_")
            operator fun invoke(
                cause: Throwable
            ): Failure = FailureImpl(cause, cause.message ?: DEFAULT_MESSAGE)

            @JsName("_init_WithMessage")
            operator fun invoke(
                cause: Throwable,
                message: String
            ): Failure = FailureImpl(cause, message)
        }
    }

    interface Success : FormFeedback {
        val success: Boolean get() = true

        companion object {
            operator fun invoke(message: String = "Success"): Success = SuccessImpl(message)
        }
    }
}