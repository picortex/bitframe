@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE")

package presenters.state

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.emptyList
import presenters.actions.SimpleActionsBuilder
import presenters.actions.SimpleAction
import presenters.modal.Dialog
import kotlin.js.JsExport

sealed class State<out T> {
    abstract val message: String

    class Loading(
        override val message: String
    ) : State<Nothing>() {
        val loading: Boolean = true
    }

    class Failure(
        val cause: Throwable? = null,
        override val message: String = cause?.message ?: DEFAULT_FAILURE_MESSAGE,
        val actions: List<SimpleAction> = emptyList()
    ) : State<Nothing>() {
        companion object {
            const val DEFAULT_FAILURE_MESSAGE = "Unknown Error"
        }

        constructor(
            cause: Throwable? = null,
            message: String = cause?.message ?: DEFAULT_FAILURE_MESSAGE,
            builder: SimpleActionsBuilder.() -> Unit
        ) : this(cause, message, SimpleActionsBuilder().apply(builder).actions)

        val failure: Boolean = true
    }

    class Success(
        override val message: String = DEFAULT_SUCCESS_MESSAGE,
        val actions: List<SimpleAction> = emptyList()
    ) : State<Nothing>() {
        companion object {
            const val DEFAULT_SUCCESS_MESSAGE = "Success"
        }

        constructor(
            message: String = DEFAULT_SUCCESS_MESSAGE,
            builder: SimpleActionsBuilder.() -> Unit
        ) : this(message, SimpleActionsBuilder().apply(builder).actions)

        val success = true
    }

    open class Content<out T>(
        open val value: T,
        open val dialog: Dialog? = null
    ) : State<T>() {
        override val message: String by lazy { "$value" }

        open fun copy(
            value: @UnsafeVariance T = this.value,
            dialog: Dialog? = this.dialog
        ): State<T> = Content(value, dialog)

        override fun equals(other: Any?): Boolean = other is Content<*> && value == other.value && dialog == other.dialog
        override fun hashCode(): Int = value.hashCode()
        override fun toString(): String = value.toString()
    }

    override fun hashCode(): Int = message.hashCode()

    override fun equals(other: Any?): Boolean = other is State<*> && other.message == message && this::class == other::class

    override fun toString(): String = "${this::class.simpleName}(message=$message)"
}