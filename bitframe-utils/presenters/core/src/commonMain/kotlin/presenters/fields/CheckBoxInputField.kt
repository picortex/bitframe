@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.fields

import presenters.fields.internal.AbstractBooleanInputFieldRaw
import kotlin.js.JsExport

class CheckBoxInputField(
    override val name: String,
    override val label: String = name,
    value: Boolean? = null,
    override val isReadonly: Boolean = InputFieldWithValue.DEFAULT_IS_READONLY,
    override val isRequired: Boolean = InputFieldWithValue.DEFAULT_IS_REQUIRED,
    override val validator: (Boolean?) -> Boolean? = { it }
) : AbstractBooleanInputFieldRaw(name, label, value, isReadonly, isRequired, validator)