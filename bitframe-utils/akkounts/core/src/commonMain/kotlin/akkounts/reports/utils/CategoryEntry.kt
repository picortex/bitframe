@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package akkounts.reports.utils

import kash.Currency
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
data class CategoryEntry(
    val name: String,
    val currency: Currency,
    val items: List<StatementEntryItem>
) {
    @JsName("_ignore_fromArray")
    constructor(name: String, currency: Currency, vararg items: StatementEntryItem) : this(name, currency, items.toInteroperableList())

    val total by lazy { items.total(currency) }
}