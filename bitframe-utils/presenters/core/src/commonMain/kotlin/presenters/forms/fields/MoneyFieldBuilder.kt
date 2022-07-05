package presenters.forms.fields

import kash.Currency
import presenters.fields.*
import presenters.forms.Fields

inline fun Fields.money(
    name: String? = null,
    label: String? = name,
    hint: String? = label,
    selectCurrency: Boolean = false,
    currency: Currency? = null,
    value: String? = null,
    isReadonly: Boolean = false,
    noinline validator: (String?) -> String? = { it }
) = getOrCreate { property ->
    MoneyInputField(
        name = name ?: property.name,
        label = label ?: property.name,
        hint = hint ?: property.name,
        selectCurrency = selectCurrency,
        currency = currency,
        value = value,
        isReadonly = isReadonly,
        validator = validator,
    )
}