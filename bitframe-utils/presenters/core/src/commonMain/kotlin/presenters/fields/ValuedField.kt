@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package presenters.fields

import live.Live
import kotlin.js.JsExport

@JsExport
interface ValuedField<T : Any> : InputField {
    val isReadonly: Boolean
    val isRequired: Boolean
    val defaultValue: T?
    val value: T?
    val feedback: Live<InputFieldState>
    val validator: ((T) -> Unit)?
    fun validate(value: T? = this.value)
    fun validateWithFeedback(value: T? = this.value)

    val asteriskedLabel get() = labelWithAsterisks

    val labelWithAsterisks get() = (if (isRequired) "*" else "") + label.replaceFirstChar { it.uppercase() }

    companion object {
        val DEFAULT_IS_READONLY = false
        val DEFAULT_IS_REQUIRED = false
        val DEFAULT_VALIDATOR: Nothing? = null
        val DEFAULT_VALUE: Nothing? = null
    }
}