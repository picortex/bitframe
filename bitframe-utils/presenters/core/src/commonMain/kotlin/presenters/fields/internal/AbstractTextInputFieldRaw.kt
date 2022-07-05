@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.fields.internal

import presenters.fields.TextInputFieldRaw
import kotlin.js.JsExport

abstract class AbstractTextInputFieldRaw(
    override val name: String,
    override val label: String = name,
    override val hint: String = label,
    value: String? = null,
    override val isReadonly: Boolean = false,
    override val validator: (String?) -> String? = { it }
) : AbstractInputFieldWithValue<String?>(name, label, value, isReadonly, validator), TextInputFieldRaw