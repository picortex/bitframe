@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE")

package presenters.cases

import kotlinx.collections.interoperable.List
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
        override val actions: List<SimpleAction> = kotlinx.collections.interoperable.emptyList()
    ) : Feedback(), Case.Failure {
        constructor(
            cause: Throwable? = null,
            message: String = cause?.message ?: Case.Failure.DEFAULT_MESSAGE,
            builder: SimpleActionsBuilder.() -> Unit
        ) : this(cause, message, SimpleActionsBuilder().apply(builder).actions)

        override val failure: Boolean = true
    }

    class Success(
        override val message: String = Case.Success.DEFAULT_MESSAGE,
        override val actions: List<SimpleAction> = kotlinx.collections.interoperable.emptyList()
    ) : Feedback(), Case.Success {
        constructor(
            message: String = Case.Success.DEFAULT_MESSAGE,
            builder: SimpleActionsBuilder.() -> Unit
        ) : this(message, SimpleActionsBuilder().apply(builder).actions)

        override val success = true
    }

    object None : Feedback() {
        override val message by lazy { "No Feedback" }
        override fun toString(): String = message
    }

    override val isLoading by lazy { this is Loading }

    override val asLoading by lazy { this as Loading }

    override val isSuccess by lazy { this is Success }

    override val asSuccess by lazy { this as Success }

    override val isFailure by lazy { this is Failure }

    override val asFailure by lazy { this as Failure }

    val isNone by lazy { this is None }

    val asNone by lazy { this as None }

    override fun hashCode(): Int = message.hashCode()

    override fun equals(other: Any?): Boolean = other is Feedback && other.message == message && this::class == other::class

    override fun toString(): String = "${this::class.simpleName}(message=$message)"
}