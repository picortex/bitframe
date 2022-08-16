package presenters.forms.fields

import presenters.fields.InputFieldWithValue
import presenters.fields.TextInputField
import presenters.forms.Fields
import kotlin.reflect.KProperty

inline fun Fields.text(
    name: String? = null,
    label: String? = name,
    hint: String? = label,
    value: String? = null,
    isReadonly: Boolean = InputFieldWithValue.DEFAULT_IS_READONLY,
    isRequired: Boolean = InputFieldWithValue.DEFAULT_IS_REQUIRED,
    noinline validator: (String?) -> String? = { it }
) = getOrCreate { property ->
    TextInputField(
        name = name ?: property.name,
        label = label ?: property.name,
        hint = hint ?: property.name,
        value = value,
        isReadonly = isReadonly,
        isRequired = isRequired,
        validator = validator,
    )
}

inline fun Fields.text(
    property: KProperty<*>,
    label: String? = property.name,
    hint: String? = label,
    value: String? = null,
    isReadonly: Boolean = InputFieldWithValue.DEFAULT_IS_READONLY,
    isRequired: Boolean = InputFieldWithValue.DEFAULT_IS_REQUIRED,
    noinline validator: (String?) -> String? = { it }
) = text(property.name, label, hint, value, isReadonly, isRequired, validator)