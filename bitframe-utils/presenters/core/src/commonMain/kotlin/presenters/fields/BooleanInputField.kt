package presenters.fields

import kotlin.js.JsExport

@JsExport
data class BooleanInputField(
    override val name: String,
    val label: String = name,
    var value: Boolean? = null
) : InputField