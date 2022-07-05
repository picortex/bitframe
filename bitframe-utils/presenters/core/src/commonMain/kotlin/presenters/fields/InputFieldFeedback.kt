@file:JsExport

package presenters.fields

import kotlin.js.JsExport

sealed class InputFieldFeedback {
    object Empty : InputFieldFeedback() {
        override fun toString() = "Empty"
    }

    object Valid : InputFieldFeedback() {
        override fun toString() = "Valid"
    }

    data class Warning(val message: String) : InputFieldFeedback()
    data class Info(val message: String) : InputFieldFeedback()
    data class Error(val message: String) : InputFieldFeedback()

    val isEmpty get() = this is Empty
    val isValid get() = this is Valid
    val isWarning get() = this is Warning
    val isInfo get() = this is Info
    val isError get() = this is Error

    val asEmpty get() = this as Empty
    val asValid get() = this as Valid
    val asWarning get() = this as Warning
    val asInfo get() = this as Info
    val asError get() = this as Error
}