@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.table

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.emptyList
import live.MutableLive
import presenters.actions.SimpleAction
import kotlin.js.JsExport

class EmptyTable<D>(
    val message: String,
    val details: String,
    val populateActions: List<SimpleAction>,
    columns: List<Column<D>> = emptyList(),
    actions: List<TableAction<D>> = emptyList()
) : Table<D>(columns, emptyList(), actions) {
    override fun toString() = "EmptyTable(cols=${columns.size})"
}