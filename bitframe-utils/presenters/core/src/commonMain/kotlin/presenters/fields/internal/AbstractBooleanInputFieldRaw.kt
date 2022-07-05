@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.fields.internal

import presenters.fields.BooleanInputFieldRaw
import kotlin.js.JsExport

abstract class AbstractBooleanInputFieldRaw(
    override val name: String,
    override val label: String = name,
    value: Boolean? = null,
    override val isReadonly: Boolean = false,
    override val validator: (Boolean?) -> Boolean? = { it }
) : AbstractInputFieldWithValue<Boolean?>(name, label, value, isReadonly, validator), BooleanInputFieldRaw