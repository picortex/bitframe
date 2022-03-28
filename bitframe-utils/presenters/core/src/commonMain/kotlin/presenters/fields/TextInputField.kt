@file:JsExport

package presenters.fields

import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.reflect.KProperty

data class TextInputField(
    override val name: String,
    override val label: String = name,
    override val hint: String = label,
    override var value: String? = null
) : RawTextInputField {
    @JsName("_ignore_fromProperty")
    constructor(
        name: KProperty<*>,
        label: String = name.name,
        hint: String = name.name,
        value: String? = null
    ) : this(name.name, label, hint, value)
}