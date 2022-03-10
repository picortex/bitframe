package presenters.fields

import kotlin.js.JsExport

@JsExport
data class DateInputField(
    override val name: String,
    override val label: String = name,
    override val hint: String = label,
    override var value: String? = null
) : RawTextInputField