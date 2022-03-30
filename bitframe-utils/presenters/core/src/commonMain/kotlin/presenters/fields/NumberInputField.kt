@file:JsExport

package presenters.fields

import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.reflect.KProperty

data class NumberInputField(
    override val name: String,
    override val label: String = name,
    override val hint: String = label,
    override var value: Double? = null
) : NumberInputRawField {
    @JsName("_ignore_fromProperty")
    constructor(
        name: KProperty<*>,
        label: String = name.name,
        hint: String = name.name,
        value: Double? = null
    ) : this(name.name, label, hint, value)
}