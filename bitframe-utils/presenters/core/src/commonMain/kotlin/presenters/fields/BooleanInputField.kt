@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.fields

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty0

@Serializable
data class BooleanInputField(
    override val name: String,
    val label: String = name,
    var value: Boolean? = null,
    val validator: (Boolean?) -> (Boolean?) = { it }
) : InputField {
    @JsName("_ignore_fromProperty")
    constructor(
        name: KProperty<*>,
        label: String = name.name,
        value: Boolean? = null
    ) : this(name.name, label, value)
}