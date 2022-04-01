package presenters.fields

import kotlin.js.JsExport

@JsExport
interface TextInputRawField : InputField {
    val label: String
    val hint: String
    val isReadonly: Boolean
    var value: String?
}