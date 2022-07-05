package presenters.fields

import live.Live
import kotlin.js.JsExport

@JsExport
interface InputFieldWithValue<T> : InputField {
    var value: T
    val feedback: Live<InputFieldFeedback>
    val validator: (T) -> T
    fun validate()
}