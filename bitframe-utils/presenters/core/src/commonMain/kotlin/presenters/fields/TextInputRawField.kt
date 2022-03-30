package presenters.fields

import kotlin.js.JsExport

@JsExport
interface TextInputRawField : InputField {
    val label: String
    val hint: String
    var value: String?
}