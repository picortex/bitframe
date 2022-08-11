package presenters.forms.fields

import presenters.fields.DropDownInputField
import presenters.fields.TextInputField
import presenters.forms.Fields
import kotlin.reflect.KProperty

inline fun Fields.selector(
    name: String? = null,
    label: String? = name,
    vararg options: DropDownInputField.Option,
) = getOrCreate { property ->
    DropDownInputField(
        name = name ?: property.name,
        label = label ?: property.name,
        options = *options
    )
}

inline fun Fields.selector(
    property: KProperty<*>,
    label: String? = property.name,
    vararg options: DropDownInputField.Option,
) = selector(property.name, label, *options)