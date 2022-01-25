@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package presenters.feedbacks

import kotlin.js.JsExport

sealed interface FormFeedback {
    val message: String

    interface Loading : FormFeedback {
        val loading: Boolean get() = true

        companion object {
            operator fun invoke(message: String) = object : Loading {
                override val message = message
                override val loading = true
            }
        }
    }

    interface Failure : FormFeedback {
        val cause: Throwable
        val failure: Boolean get() = true

        companion object {
            operator fun invoke(
                cause: Throwable,
                message: String = cause.message ?: "Unknown failure"
            ): Failure = object : Failure {
                override val cause = cause
                override val message = message
                override val failure = true
            }
        }
    }

    interface Success : FormFeedback {
        val success: Boolean get() = true

        companion object {
            operator fun invoke(message: String) = object : Loading {
                override val message = message
                override val loading = true
            }
        }
    }
}