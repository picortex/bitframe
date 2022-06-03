@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.table

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.emptyList
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlin.js.JsExport

sealed class Column<in D> {
    abstract val name: String

    data class Select<in D>(
        override val name: String,
    ) : Column<D>()

    data class Data<in D>(
        override val name: String,
        val accessor: (Row<D>) -> String = { "" }
    ) : Column<D>()

    data class Action<in D>(
        override val name: String,
        val actions: List<RowAction<D>> = emptyList()
    ) : Column<D>()
}