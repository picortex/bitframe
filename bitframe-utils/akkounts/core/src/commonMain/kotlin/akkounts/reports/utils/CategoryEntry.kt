@file:JsExport

package akkounts.reports.utils

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
data class CategoryEntry(
    val items: List<StatementEntryItem>
) {
    @JsName("_ignore_fromArray")
    constructor(vararg items: StatementEntryItem) : this(items.toInteroperableList())

    val total: Int by lazy { items.total() }
}