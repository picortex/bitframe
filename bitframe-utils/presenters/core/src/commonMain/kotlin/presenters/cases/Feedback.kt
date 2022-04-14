@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE")

package presenters.cases

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.emptyList
import presenters.actions.SimpleAction
import presenters.actions.SimpleActionsBuilder
import kotlin.js.JsExport

sealed class Feedback : Case {
    abstract override val message: String

    class Loading(
        override val message: String
    ) : Feedback(), Case.Loading {
        override val loading: Boolean = true
    }

    class Failure(
        override val cause: Throwable? = null,
        override val message: String = cause?.message ?: Case.Failure.DEFAULT_MESSAGE,
        override val actions: List<SimpleAction>
    ) : Feedback(), Case.Failure {
        constructor(
            cause: Throwable? = null,
            message: String = cause?.message ?: Case.Failure.DEFAULT_MESSAGE,
            builder: (SimpleActionsBuilder.() -> Unit)? = null
        ) : this(cause, message, builder?.let { SimpleActionsBuilder().apply(it).actions } ?: emptyList())

        override val failure: Boolean = true
    }

    class Success(
        override val message: String = Case.Success.DEFAULT_MESSAGE,
        override val actions: List<SimpleAction>
    ) : Feedback(), Case.Success {
        constructor(
            message: String = Case.Success.DEFAULT_MESSAGE,
            builder: (SimpleActionsBuilder.() -> Unit)? = null
        ) : this(message, builder?.let { SimpleActionsBuilder().apply(it).actions } ?: emptyList())

        override val success = true
    }

    object None : Feedback() {
        override val message get() = "No Feedback"
        override fun toString(): String = message
    }

    override val isLoading get() = this is Loading

    override val asLoading get() = this as Loading

    override val isSuccess get() = this is Success

    override val asSuccess get() = this as Success

    override val isFailure get() = this is Failure

    override val asFailure get() = this as Failure

    val isNone get() = this is None

    val asNone get() = this as None

    override fun hashCode(): Int = message.hashCode()

    override fun equals(other: Any?): Boolean = other is Feedback && other.message == message && this::class == other::class

    override fun toString(): String = "${this::class.simpleName}(message=$message)"
}