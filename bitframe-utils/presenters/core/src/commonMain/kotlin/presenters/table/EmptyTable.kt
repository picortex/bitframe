@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.table

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.emptyList
import live.MutableLive
import kotlin.js.JsExport

class EmptyTable<D>(
    columns: List<Column<D>> = emptyList(),
    actions: List<TableAction<D>> = emptyList()
) : Table<D>(columns, emptyList(), actions) {
    override fun equals(other: Any?): Boolean = when (other) {
        is Table<*> -> columns == other.columns
        else -> false
    }

    override fun toString(): String {
        return "EmptyTable(cols=${columns.size})"
    }

    override fun hashCode(): Int = toString().hashCode()
}