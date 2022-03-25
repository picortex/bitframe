@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE")

package presenters.cases

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.emptyList
import presenters.actions.SimpleActionsBuilder
import presenters.actions.SimpleAction
import presenters.modal.Dialog
import kotlin.js.JsExport

sealed class State<out T> : Case {
    abstract override val message: String

    class Loading(
        override val message: String
    ) : State<Nothing>(), Case.Loading {
        override val loading: Boolean = true
    }

    class Failure(
        override val cause: Throwable? = null,
        override val message: String = cause?.message ?: Case.Failure.DEFAULT_MESSAGE,
        override val actions: List<SimpleAction> = emptyList()
    ) : State<Nothing>(), Case.Failure {
        constructor(
            cause: Throwable? = null,
            message: String = cause?.message ?: Case.Failure.DEFAULT_MESSAGE,
            builder: SimpleActionsBuilder.() -> Unit
        ) : this(cause, message, SimpleActionsBuilder().apply(builder).actions)

        override val failure: Boolean = true
    }

    class Success(
        override val message: String = Case.Success.DEFAULT_MESSAGE,
        override val actions: List<SimpleAction> = emptyList()
    ) : State<Nothing>(), Case.Success {

        constructor(
            message: String = Case.Success.DEFAULT_MESSAGE,
            builder: SimpleActionsBuilder.() -> Unit
        ) : this(message, SimpleActionsBuilder().apply(builder).actions)

        override val success = true
    }

    open class Content<out T>(
        open val value: T,
        open val dialog: Dialog<*, *>? = null
    ) : State<T>() {
        override val message: String by lazy { "$value" }

        open fun copy(
            value: @UnsafeVariance T = this.value,
            dialog: Dialog<*, *>? = this.dialog
        ): State<T> = Content(value, dialog)

        override fun equals(other: Any?): Boolean = other is Content<*> && value == other.value && dialog == other.dialog
        override fun hashCode(): Int = value.hashCode()
        override fun toString(): String = value.toString()
    }

    override val isLoading by lazy { this is Loading }

    override val asLoading by lazy { this as Loading }

    override val isSuccess by lazy { this is Success }

    override val asSuccess by lazy { this as Success }

    override val isFailure by lazy { this is Failure }

    override val asFailure by lazy { this as Failure }

    val isContent by lazy { this is Content }

    val asContent by lazy { this as Content }

    override fun hashCode(): Int = message.hashCode()

    override fun equals(other: Any?): Boolean = other is State<*> && other.message == message && this::class == other::class

    override fun toString(): String = "${this::class.simpleName}(message=$message)"
}