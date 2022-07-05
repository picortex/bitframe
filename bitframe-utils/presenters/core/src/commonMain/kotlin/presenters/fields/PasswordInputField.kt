@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.fields

import presenters.fields.internal.AbstractTextInputFieldRaw
import kotlin.js.JsExport

@JsExport
data class PasswordInputField(
    override val name: String,
    override val label: String = name,
    override val hint: String = label,
    override var value: String? = null,
    override val isReadonly: Boolean = false,
    override val validator: (String?) -> String? = { it }
) : AbstractTextInputFieldRaw(name, label, hint, value, isReadonly, validator)