@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE")

package presenters.cases

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.emptyList
import presenters.actions.SimpleActionsBuilder
import presenters.actions.SimpleAction
import presenters.modal.Dialog
import kotlin.js.JsExport
import kotlin.js.JsName
import presenters.cases.Loading as LoadingCase
import presenters.cases.Success as SuccessCase
import presenters.cases.Failure as FailureCase

sealed class GenericState<out S> : Case {
    abstract override val message: String

    data class Loading(
        override val message: String
    ) : GenericState<Nothing>(), LoadingCase {
        override val loading: Boolean = true
    }

    data class Failure(
        override val cause: Throwable? = null,
        override val message: String = cause?.message ?: FailureCase.DEFAULT_MESSAGE,
        override val actions: List<SimpleAction> = emptyList()
    ) : GenericState<Nothing>(), FailureCase {
        constructor(
            cause: Throwable? = null,
            message: String = cause?.message ?: FailureCase.DEFAULT_MESSAGE,
            builder: SimpleActionsBuilder.() -> Unit
        ) : this(cause, message, SimpleActionsBuilder().apply(builder).actions)

        override val failure: Boolean = true
    }

    data class Success(
        override val message: String = SuccessCase.DEFAULT_MESSAGE,
        override val actions: List<SimpleAction> = emptyList()
    ) : GenericState<Nothing>(), SuccessCase {

        constructor(
            message: String = SuccessCase.DEFAULT_MESSAGE,
            builder: SimpleActionsBuilder.() -> Unit
        ) : this(message, SimpleActionsBuilder().apply(builder).actions)

        override val success = true
    }

    data class Content<out T>(
        val data: T,
        val dialog: Dialog<*, *>? = null
    ) : GenericState<T>() {
        override val message: String get() = "$data"
    }

    @JsName("_ignore_copyDialog")
    fun copy(dialog: Dialog<*, *>?): GenericState<S> = when (this) {
        is Content -> copy(data = this.data, dialog = dialog)
        else -> this
    }

    @JsName("_ignore_copyData")
    fun copy(data: @UnsafeVariance S): GenericState<S> = when (this) {
        is Content -> copy(data = data, dialog = this.dialog)
        else -> Content(data, dialog = null)
    }

    override val isLoading get() = this is Loading

    override val asLoading get() = this as Loading

    override val isSuccess get() = this is Success

    override val asSuccess get() = this as Success

    override val isFailure get() = this is Failure

    override val asFailure get() = this as Failure

    val isContent get() = this is Content

    val asContent get() = this as Content

    val contentValue get() = (this as Content).data
}