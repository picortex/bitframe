package presenters.forms.fields

import presenters.fields.TextInputField
import presenters.forms.Fields
import kotlin.reflect.KProperty

inline fun Fields.text(
    name: String? = null,
    label: String? = name,
    hint: String? = label,
    value: String? = null,
    isReadonly: Boolean = false,
    noinline validator: (String?) -> String? = { it }
) = getOrCreate { property ->
    TextInputField(
        name = name ?: property.name,
        label = label ?: property.name,
        hint = hint ?: property.name,
        value = value,
        isReadonly = isReadonly,
        validator = validator,
    )
}

inline fun Fields.text(
    property: KProperty<*>,
    label: String? = property.name,
    hint: String? = label,
    value: String? = null,
    isReadonly: Boolean = false,
    noinline validator: (String?) -> String? = { it }
) = text(property.name, label, hint, value, isReadonly, validator)