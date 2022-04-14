@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.cases

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.emptyList
import presenters.actions.SimpleAction
import presenters.actions.SimpleActionsBuilder
import kotlin.js.JsExport
import kotlin.js.JsName
import presenters.cases.Failure as FailureCase
import presenters.cases.Loading as LoadingCase
import presenters.cases.Success as SuccessCase

sealed class Mission<out T> : Case {
    data class Loading(
        override val message: String
    ) : Mission<Nothing>(), LoadingCase

    data class Failure(
        override val cause: Throwable? = null,
        override val message: String = cause?.message ?: FailureCase.DEFAULT_MESSAGE,
        override val actions: List<SimpleAction>
    ) : Mission<Nothing>(), FailureCase {
        @JsName("_ignore_builder")
        constructor(
            cause: Throwable? = null,
            message: String = cause?.message ?: FailureCase.DEFAULT_MESSAGE,
            builder: (SimpleActionsBuilder.() -> Unit)? = null
        ) : this(cause, message, builder?.let { SimpleActionsBuilder().apply(it).actions } ?: emptyList())

        override val failure: Boolean = true
    }

    data class Success<out T>(
        val data: T
    ) : Mission<Nothing>(), SuccessCase {
        override val message: String = SuccessCase.DEFAULT_MESSAGE
        override val actions: List<SimpleAction> = emptyList()
    }

    override val isLoading get() = this is Loading
    override val asLoading get() = this as Loading

    override val isSuccess get() = this is Success<*>
    override val asSuccess get() = this as Success<T>

    override val isFailure get() = this is Failure
    override val asFailure get() = this as Failure
}