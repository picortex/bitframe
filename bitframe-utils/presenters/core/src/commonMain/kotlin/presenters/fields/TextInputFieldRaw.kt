package presenters.fields

import kotlin.js.JsExport

@JsExport
interface TextInputFieldRaw : InputFieldWithValue<String?> {
    val label: String
    val hint: String
    val isReadonly: Boolean
}