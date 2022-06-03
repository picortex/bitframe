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
    abstract val data: S?

    data class Loading<out S>(
        override val message: String,
        override val data: S? = null
    ) : GenericState<S>(), LoadingCase {
        override val loading: Boolean = true
    }

    data class Failure<out S> internal constructor(
        override val cause: Throwable? = null,
        override val message: String = cause?.message ?: FailureCase.DEFAULT_MESSAGE,
        override val data: S? = null,
        override val actions: List<SimpleAction> = emptyList()
    ) : GenericState<S>(), FailureCase {
        internal constructor(
            cause: Throwable? = null,
            message: String = cause?.message ?: FailureCase.DEFAULT_MESSAGE,
            data: S? = null,
            builder: SimpleActionsBuilder.() -> Unit
        ) : this(cause, message, data, SimpleActionsBuilder().apply(builder).actions)

        override val failure: Boolean = true
    }

    data class Success<out S> internal constructor(
        override val message: String = SuccessCase.DEFAULT_MESSAGE,
        override val actions: List<SimpleAction> = emptyList(),
        override val data: S? = null
    ) : GenericState<S>(), SuccessCase {
        internal constructor(
            message: String = SuccessCase.DEFAULT_MESSAGE,
            data: S? = null,
            builder: SimpleActionsBuilder.() -> Unit
        ) : this(message, SimpleActionsBuilder().apply(builder).actions, data)

        override val success = true
    }

    data class Content<out S>(
        override val data: S,
        val dialog: Dialog<*, *>? = null
    ) : GenericState<S>() {
        override val message: String get() = "$data"
    }

    override val isLoading get() = this is Loading

    override val asLoading get() = this as Loading

    override val isSuccess get() = this is Success

    override val asSuccess get() = this as Success

    override val isFailure get() = this is Failure

    override val asFailure get() = this as Failure

    val isContent get() = this is Content

    val asContent get() = this as Content

    @Deprecated("In favour of data", replaceWith = ReplaceWith("data!!"))
    val contentValue
        get() = (this as Content).data
}