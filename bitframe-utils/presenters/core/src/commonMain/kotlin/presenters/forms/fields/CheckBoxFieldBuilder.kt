package presenters.forms.fields

import presenters.fields.CheckBoxInputField
import presenters.fields.TextInputField
import presenters.forms.Fields
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty

inline fun Fields.checkBox(
    name: String? = null,
    label: String? = name,
    value: Boolean? = null,
    isReadonly: Boolean = false,
    noinline validator: (Boolean?) -> Boolean? = { it }
) = getOrCreate { property ->
    CheckBoxInputField(
        name = name ?: property.name,
        label = label ?: property.name,
        value = value,
        isReadonly = isReadonly,
        validator = validator,
    )
}

inline fun Fields.checkBox(
    property: KProperty<*>,
    label: String? = property.name,
    value: Boolean? = null,
    isReadonly: Boolean = false,
    noinline validator: (Boolean?) -> Boolean? = { it }
) = checkBox(property.name, label, value, isReadonly, validator)