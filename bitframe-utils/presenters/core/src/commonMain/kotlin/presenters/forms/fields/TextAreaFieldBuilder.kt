package presenters.forms.fields

import presenters.fields.TextAreaField
import presenters.fields.TextInputField
import presenters.forms.Fields
import kotlin.reflect.KProperty

inline fun Fields.textArea(
    name: String? = null,
    label: String? = name,
    hint: String? = label,
    value: String? = null,
    isReadonly: Boolean = false,
    noinline validator: (String?) -> String? = { it }
) = getOrCreate { property ->
    TextAreaField(
        name = name ?: property.name,
        label = label ?: property.name,
        hint = hint ?: property.name,
        value = value,
        isReadonly = isReadonly,
        validator = validator,
    )
}

inline fun Fields.textArea(
    property: KProperty<*>,
    label: String? = property.name,
    hint: String? = label,
    value: String? = null,
    isReadonly: Boolean = false,
    noinline validator: (String?) -> String? = { it }
) = textArea(property.name, label, hint, value, isReadonly, validator)