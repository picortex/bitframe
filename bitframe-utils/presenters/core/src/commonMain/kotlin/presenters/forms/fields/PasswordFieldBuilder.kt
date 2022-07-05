package presenters.forms.fields

import presenters.fields.PasswordInputField
import presenters.fields.PhoneInputField
import presenters.fields.TextAreaField
import presenters.fields.TextInputField
import presenters.forms.Fields

inline fun Fields.password(
    name: String? = null,
    label: String? = name,
    hint: String? = label,
    value: String? = null,
    isReadonly: Boolean = false,
    noinline validator: (String?) -> String? = { it }
) = getOrCreate { property ->
    PasswordInputField(
        name = name ?: property.name,
        label = label ?: property.name,
        hint = hint ?: property.name,
        value = value,
        isReadonly = isReadonly,
        validator = validator,
    )
}