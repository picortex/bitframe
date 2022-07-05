package presenters.forms.fields

import presenters.fields.*
import presenters.forms.Fields
import kotlin.reflect.KProperty

inline fun Fields.email(
    name: String? = null,
    label: String? = name,
    hint: String? = label,
    value: String? = null,
    isReadonly: Boolean = false,
    noinline validator: (String?) -> String? = { it }
) = getOrCreate { property ->
    EmailInputField(
        name = name ?: property.name,
        label = label ?: property.name,
        hint = hint ?: property.name,
        value = value,
        isReadonly = isReadonly,
        validator = validator,
    )
}

inline fun Fields.email(
    property: KProperty<*>,
    label: String? = property.name, hint: String? = label,
    value: String? = null,
    isReadonly: Boolean = false,
    noinline validator: (String?) -> String? = { it }
) = email(property.name, label, hint, value, isReadonly, validator)