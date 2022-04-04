@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.fields

import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.reflect.KProperty

data class PhoneInputField(
    override val name: String,
    override val label: String = name,
    override val hint: String = "",
    override var value: String? = null,
    override val isReadonly: Boolean = false
) : TextInputRawField {
    @JsName("_ignore_fromProperty")
    constructor(
        name: KProperty<*>,
        label: String = name.name,
        hint: String = "",
        value: String? = null,
        isReadonly: Boolean = false
    ) : this(name.name, label, hint, value, isReadonly)
}