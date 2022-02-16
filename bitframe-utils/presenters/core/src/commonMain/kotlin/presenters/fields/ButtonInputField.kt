package presenters.fields

import kotlin.js.JsExport

@JsExport
data class ButtonInputField(
    val text: String,
    override val name: String = text,
    var handler: (() -> Unit)? = null
) : InputField