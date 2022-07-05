@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.fields

import presenters.fields.internal.AbstractTextInputFieldRaw
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.reflect.KProperty

data class NumberInputField(
    override val name: String,
    override val label: String = name,
    override val hint: String = label,
    override var value: String? = null,
    override val isReadonly: Boolean = false,
    override val validator: (String?) -> String? = { it }
) : AbstractTextInputFieldRaw(name, label, hint, value, isReadonly, validator) {
    @JsName("_ignore_fromProperty")
    constructor(
        name: KProperty<*>,
        label: String = name.name,
        hint: String = label,
        value: String? = null
    ) : this(name.name, label, hint, value)
}