@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.forms

import presenters.cases.Failure as FailureCase
import kotlin.js.JsExport

sealed class FormState<out F : Form<*, *>> {
    abstract val form: F

    data class Fillable<out F : Form<*, *>>(override val form: F) : FormState<F>()
    data class Submitting<out F : Form<*, *>>(override val form: F) : FormState<F>()
    data class Submitted<out F : Form<*, *>>(override val form: F) : FormState<F>()
    data class Failure<out F : Form<*, *>>(
        override val form: F,
        val cause: Throwable? = null,
        val message: String? = cause?.message ?: FailureCase.DEFAULT_MESSAGE
    ) : FormState<F>()


    val isFillable get() = this is Fillable
    val isSubmitting get() = this is Submitting
    val isSubmitted get() = this is Submitted
    val isFailure get() = this is Failure

    val asFillable get() = this as Fillable
    val asSubmitting get() = this as Submitting
    val asSubmitted get() = this as Submitted
    val asFailure get() = this as Failure
}