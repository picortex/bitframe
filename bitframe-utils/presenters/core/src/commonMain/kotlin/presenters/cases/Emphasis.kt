@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "FunctionName")

package presenters.cases

import presenters.actions.SimpleActionsBuilder
import presenters.forms.Form
import presenters.modal.Dialog
import presenters.modal.dialog
import kotlin.js.JsExport
import kotlin.js.JsName

sealed class Emphasis {
    companion object {
        fun Loading(message: String) = Status(Feedback.Loading(message))

        fun Success(
            message: String = Case.Success.DEFAULT_MESSAGE,
            builder: (SimpleActionsBuilder.() -> Unit)? = null
        ) = Status(Feedback.Success(message, builder))

        fun Failure(
            cause: Throwable? = null,
            message: String = cause?.message ?: Case.Failure.DEFAULT_MESSAGE,
            builder: (SimpleActionsBuilder.() -> Unit)? = null
        ) = Status(Feedback.Failure(cause, message, builder))

        @JsName("fromDialog")
        fun Dialog(dialog: Dialog<*, *>) = Modal(dialog)

        @JsName("fromForm")
        fun Dialog(form: Form<*, *>) = Modal(dialog(form))
    }

    object None : Emphasis()
    data class Status(val feedback: Case) : Emphasis()
    data class Modal(val dialog: Dialog<*, *>) : Emphasis()

    val isStatus get() = this is Status
    val asStatus get() = this as Status

    val isNone get() = this is None

    val isLoading get() = this is Status && feedback.isLoading
    val asLoading get() = (this as Status).feedback.asLoading

    val isSuccess get() = this is Status && feedback.isSuccess
    val asSuccess get() = (this as Status).feedback.asSuccess

    val isFailure get() = this is Status && feedback.isFailure
    val asFailure get() = (this as Status).feedback.asFailure

    val isDialog get() = this is Modal
    val asDialog get() = (this as Modal).dialog
}
