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

sealed class MissionState<out T> : Case {
    abstract val data: T?

    data class Loading(
        override val message: String
    ) : MissionState<Nothing>(), LoadingCase {
        override val data = null
    }

    data class Failure(
        override val cause: Throwable? = null,
        override val message: String = cause?.message ?: FailureCase.DEFAULT_MESSAGE,
        override val actions: List<SimpleAction>
    ) : MissionState<Nothing>(), FailureCase {
        override val data = null

        @JsName("_ignore_builder")
        constructor(
            cause: Throwable? = null,
            message: String = cause?.message ?: FailureCase.DEFAULT_MESSAGE,
            builder: (SimpleActionsBuilder.() -> Unit)? = null
        ) : this(cause, message, builder?.let { SimpleActionsBuilder().apply(it).actions } ?: emptyList())

        override val failure: Boolean = true
    }

    data class Success<out T>(
        override val data: T
    ) : MissionState<T>(), SuccessCase {
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