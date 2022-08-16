@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.fields.internal

import presenters.fields.BooleanInputFieldRaw
import presenters.fields.InputFieldWithValue
import presenters.fields.InputFieldWithValue.Companion.DEFAULT_IS_READONLY
import presenters.fields.InputFieldWithValue.Companion.DEFAULT_IS_REQUIRED
import kotlin.js.JsExport

abstract class AbstractBooleanInputFieldRaw(
    override val name: String,
    override val label: String = name,
    value: Boolean? = null,
    override val isReadonly: Boolean = DEFAULT_IS_READONLY,
    override val isRequired: Boolean = DEFAULT_IS_REQUIRED,
    override val validator: (Boolean?) -> Boolean? = { it }
) : AbstractInputFieldWithValue<Boolean?>(name, label, value, isReadonly, isRequired, validator), BooleanInputFieldRaw