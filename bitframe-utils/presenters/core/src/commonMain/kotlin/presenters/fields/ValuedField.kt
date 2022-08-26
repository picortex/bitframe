@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package presenters.fields

import live.Live
import kotlin.js.JsExport

@JsExport
interface ValuedField : InputField {
    val isReadonly: Boolean
    val isRequired: Boolean
    val feedback: Live<InputFieldState>

    fun clear()

    val asteriskedLabel get() = labelWithAsterisks

    val labelWithAsterisks get() = (if (isRequired) "*" else "") + label.replaceFirstChar { it.uppercase() }

    companion object {
        val DEFAULT_IS_READONLY = false
        val DEFAULT_IS_REQUIRED = false
        val DEFAULT_VALIDATOR: Nothing? = null
        val DEFAULT_VALUE: Nothing? = null
    }
}