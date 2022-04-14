@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE")

package presenters.cases

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.emptyList
import presenters.actions.SimpleActionsBuilder
import presenters.actions.SimpleAction
import presenters.modal.Dialog
import kotlin.js.JsExport
import presenters.cases.Loading as LoadingCase
import presenters.cases.Success as SuccessCase
import presenters.cases.Failure as FailureCase

sealed class State<out T> : Case {
    abstract override val message: String

    class Loading(
        override val message: String
    ) : State<Nothing>(), LoadingCase {
        override val loading: Boolean = true
    }

    class Failure(
        override val cause: Throwable? = null,
        override val message: String = cause?.message ?: FailureCase.DEFAULT_MESSAGE,
        override val actions: List<SimpleAction> = emptyList()
    ) : State<Nothing>(), FailureCase {
        constructor(
            cause: Throwable? = null,
            message: String = cause?.message ?: FailureCase.DEFAULT_MESSAGE,
            builder: SimpleActionsBuilder.() -> Unit
        ) : this(cause, message, SimpleActionsBuilder().apply(builder).actions)

        override val failure: Boolean = true
    }

    class Success(
        override val message: String = SuccessCase.DEFAULT_MESSAGE,
        override val actions: List<SimpleAction> = emptyList()
    ) : State<Nothing>(), SuccessCase {

        constructor(
            message: String = SuccessCase.DEFAULT_MESSAGE,
            builder: SimpleActionsBuilder.() -> Unit
        ) : this(message, SimpleActionsBuilder().apply(builder).actions)

        override val success = true
    }

    open class Content<out T>(
        open val value: T,
        open val dialog: Dialog<*, *>? = null
    ) : State<T>() {
        override val message: String get() = "$value"

        open fun copy(
            value: @UnsafeVariance T = this.value,
            dialog: Dialog<*, *>? = this.dialog
        ): State<T> = Content(value, dialog)

        override fun equals(other: Any?): Boolean = other is Content<*> && value == other.value && dialog == other.dialog
        override fun hashCode(): Int = value.hashCode()
        override fun toString(): String = value.toString()
    }

    override val isLoading get() = this is Loading

    override val asLoading get() = this as Loading

    override val isSuccess get() = this is Success

    override val asSuccess get() = this as Success

    override val isFailure get() = this is Failure

    override val asFailure get() = this as Failure

    val isContent get() = this is Content

    val asContent get() = this as Content

    val contentValue get() = (this as Content).value

    override fun hashCode(): Int = message.hashCode()

    override fun equals(other: Any?): Boolean = other is State<*> && other.message == message && this::class == other::class

    override fun toString(): String = "${this::class.simpleName}(message=$message)"
}