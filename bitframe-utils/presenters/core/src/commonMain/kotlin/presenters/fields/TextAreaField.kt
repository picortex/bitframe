@file:JsExport

package presenters.fields

import kotlin.js.JsExport

data class TextAreaField(
    override val name: String,
    override val label: String = name,
    override val hint: String = label,
    override var value: String? = null
) : TextInputRawField