package presenters.forms.fields

import presenters.fields.*
import presenters.forms.Fields

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