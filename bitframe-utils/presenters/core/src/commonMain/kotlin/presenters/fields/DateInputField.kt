@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.fields

import presenters.fields.internal.AbstractTextInputFieldRaw
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.reflect.KProperty

@JsExport
class DateInputField(
    override val name: String,
    override val label: String = name,
    override val hint: String = label,
    value: String? = null,
    override val isReadonly: Boolean = InputFieldWithValue.DEFAULT_IS_READONLY,
    override val isRequired: Boolean = InputFieldWithValue.DEFAULT_IS_REQUIRED,
    override val validator: (String?) -> String?
) : AbstractTextInputFieldRaw(name, label, hint, value, isReadonly, isRequired, validator)