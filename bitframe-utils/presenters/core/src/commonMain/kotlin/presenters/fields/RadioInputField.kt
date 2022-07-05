@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.fields

import presenters.fields.internal.AbstractBooleanInputFieldRaw
import kotlin.js.JsExport

class RadioInputField(
    override val name: String,
    override val label: String = name,
    value: Boolean? = null,
    override val isReadonly: Boolean = false,
    override val validator: (Boolean?) -> Boolean? = { it }
) : AbstractBooleanInputFieldRaw(name, label, value, isReadonly, validator)