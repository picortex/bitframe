@file:Suppress("WRONG_EXPORTED_DECLARATION")

package presenters.feedbacks

//import presenters.feedbacks.builders.FailureImpl
//import presenters.feedbacks.builders.SuccessImpl
import kotlin.js.JsExport

@JsExport
sealed interface Feedback {
    val message: String

    open class Loading(
        override val message: String
    ) : Feedback {
        val loading: Boolean get() = true
        override fun hashCode(): Int = message.hashCode()

        override fun equals(other: Any?): Boolean = when (other) {
            is Loading -> other.message == message
            else -> false
        }

        override fun toString(): String = "Loading(message=$message)"
//        companion object {
//            operator fun invoke(message: String): Loading = LoadingImpl(message)
//        }
    }


    open class Failure(
        open val cause: Throwable,
        override val message: String = cause.message ?: "Unknown error"
    ) : Feedback {
        val failure: Boolean = true
        override fun hashCode(): Int = message.hashCode()

        override fun equals(other: Any?): Boolean = when (other) {
            is Loading -> other.message == message
            else -> false
        }

        override fun toString(): String = "Failure(message=$message)"
    }
//    interface Failure : FormFeedback {
//        val cause: Throwable
//        val failure: Boolean get() = true
//
//        companion object {
//            @JvmStatic
//            val DEFAULT_MESSAGE = "Unknown error"
//
//            @JsName("_init_")
//            operator fun invoke(
//                cause: Throwable
//            ): Failure = FailureImpl(cause, cause.message ?: DEFAULT_MESSAGE)
//
//            @JsName("_init_WithMessage")
//            operator fun invoke(
//                cause: Throwable,
//                message: String
//            ): Failure = FailureImpl(cause, message)
//        }
//    }

    open class Success(
        override val message: String = "Success"
    ) : Feedback {
        val success = true
        override fun hashCode(): Int = message.hashCode()

        override fun equals(other: Any?): Boolean = when (other) {
            is Loading -> other.message == message
            else -> false
        }

        override fun toString(): String = "Success(message=$message)"
    }
//    interface Success : FormFeedback {
//        val success: Boolean get() = true
//
//        companion object {
//            operator fun invoke(message: String = "Success"): Success = SuccessImpl(message)
//        }
//    }
}