package presenters.fields

import kotlin.js.JsExport

@JsExport
interface RawTextInputField : InputField {
    val label: String
    val hint: String
    var value: String?
}