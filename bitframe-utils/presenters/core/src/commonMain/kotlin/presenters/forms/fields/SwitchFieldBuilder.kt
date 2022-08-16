package presenters.forms.fields

import presenters.fields.InputFieldWithValue
import presenters.fields.SwitchInputField
import presenters.forms.Fields
import kotlin.reflect.KProperty

inline fun Fields.switch(
    name: String? = null,
    label: String? = name,
    value: Boolean? = null,
    isReadonly: Boolean = InputFieldWithValue.DEFAULT_IS_READONLY,
    isRequired: Boolean = InputFieldWithValue.DEFAULT_IS_REQUIRED,
    noinline validator: (Boolean?) -> Boolean? = { it }
) = getOrCreate { property ->
    SwitchInputField(
        name = name ?: property.name,
        label = label ?: property.name,
        value = value,
        isReadonly = isReadonly,
        isRequired = isRequired,
        validator = validator,
    )
}

inline fun Fields.switch(
    property: KProperty<*>,
    label: String? = property.name,
    value: Boolean? = null,
    isReadonly: Boolean = InputFieldWithValue.DEFAULT_IS_READONLY,
    isRequired: Boolean = InputFieldWithValue.DEFAULT_IS_REQUIRED,
    noinline validator: (Boolean?) -> Boolean? = { it }
) = switch(property.name, label, value, isReadonly, isRequired, validator)