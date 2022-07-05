package presenters.forms.fields

import presenters.fields.RadioInputField
import presenters.forms.Fields
import kotlin.reflect.KProperty

inline fun Fields.radio(
    name: String? = null,
    label: String? = name,
    value: Boolean? = null,
    isReadonly: Boolean = false,
    noinline validator: (Boolean?) -> Boolean? = { it }
) = getOrCreate { property ->
    RadioInputField(
        name = name ?: property.name,
        label = label ?: property.name,
        value = value,
        isReadonly = isReadonly,
        validator = validator,
    )
}

inline fun Fields.radio(
    property: KProperty<*>,
    label: String? = property.name,
    value: Boolean? = null,
    isReadonly: Boolean = false,
    noinline validator: (Boolean?) -> Boolean? = { it }
) = switch(property.name, label, value, isReadonly, validator)