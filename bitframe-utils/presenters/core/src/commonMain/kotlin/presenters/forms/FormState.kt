@file:JsExport

package presenters.forms

import presenters.cases.Failure as FailureCase
import kotlin.js.JsExport

sealed class FormState {
    object Fillable : FormState()
    object Submitting : FormState()
    object Submitted : FormState()
    data class Failure(
        val cause: Throwable? = null,
        val message: String? = cause?.message ?: FailureCase.DEFAULT_MESSAGE
    ) : FormState()


    val isFillable get() = this is Fillable
    val isSubmitting get() = this is Submitting
    val isSubmitted get() = this is Submitted
    val isFailure get() = this is Failure

    val asFillable get() = this as Fillable
    val asSubmitting get() = this as Submitting
    val asSubmitted get() = this as Submitted
    val asFailure get() = this as Failure
}