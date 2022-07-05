@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.fields

import live.Live
import kotlin.js.JsExport

@JsExport
interface InputFieldWithValue<T> : InputField {
    val label: String
    val isReadonly: Boolean
    var value: T
    val feedback: Live<InputFieldFeedback>
    val validator: (T) -> T
    fun validate()
}