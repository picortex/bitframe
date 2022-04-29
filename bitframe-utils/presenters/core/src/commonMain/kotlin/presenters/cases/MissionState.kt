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

sealed class MissionState<out C, out T> : Case {
    abstract val data: T?
    abstract val context: C?

    data class Loading<out C, out T>(
        override val message: String,
        override val context: C? = null,
        override val data: T? = null,
    ) : MissionState<C, T>(), LoadingCase

    data class Failure<out C, out T> internal constructor(
        override val cause: Throwable? = null,
        override val message: String = cause?.message ?: FailureCase.DEFAULT_MESSAGE,
        override val context: C? = null,
        override val data: T? = null,
        override val actions: List<SimpleAction>
    ) : MissionState<C, T>(), FailureCase {
        @JsName("_ignore_builder")
        internal constructor(
            cause: Throwable? = null,
            message: String = cause?.message ?: FailureCase.DEFAULT_MESSAGE,
            context: C? = null,
            data: T? = null,
            builder: (SimpleActionsBuilder.() -> Unit)? = null
        ) : this(cause, message, context, data, builder?.let { SimpleActionsBuilder().apply(it).actions } ?: emptyList())

        override val failure: Boolean = true
    }

    data class Success<out C, out T> internal constructor(
        override val context: C,
        override val data: T
    ) : MissionState<C, T>(), SuccessCase {
        override val message: String = SuccessCase.DEFAULT_MESSAGE
        override val actions: List<SimpleAction> = emptyList()
    }

    override val isLoading get() = this is Loading
    override val asLoading get() = this as Loading

    override val isSuccess get() = this is Success
    override val asSuccess get() = this as Success

    override val isFailure get() = this is Failure
    override val asFailure get() = this as Failure
}