@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.fields

import kash.Currency
import kash.Money
import kotlinx.collections.interoperable.toInteroperableList
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.reflect.KProperty

data class MoneyInputField(
    override val name: String,
    val label: String = name,
    val hint: String = label,
    val selectCurrency: Boolean = false,
    val currency: Currency? = null,
    val value: String? = null
) : InputField {

    @JsName("from_property")
    constructor(
        name: KProperty<*>,
        label: String = name.name,
        hint: String = name.name,
        selectCurrency: Boolean = false,
        currency: Currency? = null,
        value: String? = null
    ) : this(name.name, label, hint, selectCurrency, currency, value)

    val currencies by lazy {
        DropDownInputField(
            name = "$name-currency",
            label = "Currency",
            options = Currency.values.map {
                DropDownInputField.Option(
                    label = it.name,
                    value = it.name,
                    selected = it == currency
                )
            }.toInteroperableList()
        )
    }

    val amount by lazy {
        NumberInputField(
            name = "$name-value",
            label = "Value",
            hint = hint,
            value = value
        )
    }
}